package app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.config.Configuration;
import app.geometry2D.Point;
import app.geometry3D.Figure;
import app.geometry3D.Icosphere;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller extends Application implements Configuration {
    private Pane mainPane = new Pane();
    private Pane objectsPane = new Pane();
    private Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);

    private List<Figure> objects = new ArrayList<>();
    private View view = new View();
    private long timeSum = 0;
    private long frames = 0;

    Map<KeyCode, Boolean> pressedButtons = new HashMap<>();

    private void addObjects() {
        // objects.add(new Prism(new Point(-25.0, 0.0, 0.0), new Point(-5.0, 20.0, 20.0),view));
        // objects.add(new Prism(new Point(5.0, 0.0, 0.0), new Point(25.0, 20.0, 50.0),view));
        // objects.add(new Sphere(new Point(50, 0, 0), 20, 40, view));
        objects.add(new Icosphere(new Point(0, 0, 0), 30, 4, view));

        initObjects();
        initPanes();
    }

    private void initObjects() {
        objects.forEach(o -> {
            objectsPane.getChildren().addAll(o.rotateOX(90).moveZ(150).getDrawable());
        });

        Movement.rotatePointOX(LIGHT, 90);
        Movement.move(LIGHT, 0.0, 0.0, 150.0);

        scene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), true);
        });

        scene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), false);
        });
    }

    private void initPanes() {
        mainPane.setStyle("-fx-background-color: black;");

        if (FILL_RGB != null) {
            Pane slidersPane = new Pane();
            VBox vBox = new VBox();

            vBox.getChildren().add(initSlider("Ambient (K_a)", 0, 1, init_K_a, 0.01, new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                    objects.forEach(ob -> ob.getSides().forEach(t -> t.setK_a(newValue.doubleValue())));
                    objectsPane.requestFocus();
                }
            }));
            vBox.getChildren().add(initSlider("Diffuse (K_d)", 0, 1, init_K_d, 0.01, new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                    objects.forEach(ob -> ob.getSides().forEach(t -> t.setK_d(newValue.doubleValue())));
                    objectsPane.requestFocus();
                }
            }));
            vBox.getChildren().add(initSlider("Specular (K_s)", 0, 1, init_K_s, 0.01, new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                    objects.forEach(ob -> ob.getSides().forEach(t -> t.setK_s(newValue.doubleValue())));
                    objectsPane.requestFocus();
                }
            }));
            vBox.getChildren().add(initSlider("Shininess (alpha)", 1, 100, init_alpha, 1, new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                    objects.forEach(ob -> ob.getSides().forEach(t -> t.setAlpha(newValue.doubleValue())));
                    objectsPane.requestFocus();
                }
            }));

            slidersPane.getChildren().add(vBox);
            mainPane.getChildren().addAll(objectsPane, slidersPane);

        } else {
            mainPane.getChildren().addAll(objectsPane);
        }
    }

    @Override
    public void start(Stage stage) {
        addObjects();
        System.out.println("[INFO] Objects placed");

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                long time = System.currentTimeMillis();

                handleButton();

                objectsPane.getChildren().clear();
                objects.forEach(o -> objectsPane.getChildren().addAll(o.getDrawable()));

                timeInfo(time);
            }
        };
        System.out.println("[INFO] Timer start...");
        timer.start();
    }

    private void handleButton() {
        pressedButtons.forEach((button, pressed) -> {
            if (pressed) {
                switch (button) {
                    case A:
                        objects.forEach(o -> o.moveX(MOVE_INC));
                        Movement.move(LIGHT, MOVE_INC, 0.0, 0.0);
                        break;
                    case D:
                        objects.forEach(o -> o.moveX(-MOVE_INC));
                        Movement.move(LIGHT, -MOVE_INC, 0.0, 0.0);
                        break;
                    case S:
                        objects.forEach(o -> o.moveZ(MOVE_INC));
                        Movement.move(LIGHT, 0.0, 0.0, MOVE_INC);
                        break;
                    case W:
                        objects.forEach(o -> o.moveZ(-MOVE_INC));
                        Movement.move(LIGHT, 0.0, 0.0, -MOVE_INC);
                        break;
                    case SPACE:
                        objects.forEach(o -> o.moveY(MOVE_INC));
                        Movement.move(LIGHT, 0.0, MOVE_INC, 0.0);
                        break;
                    case SHIFT:
                        objects.forEach(o -> o.moveY(-MOVE_INC));
                        Movement.move(LIGHT, 0.0, -MOVE_INC, 0.0);
                        break;
                    case UP:
                        objects.forEach(o -> o.rotateOX(-ANGLE_INC));
                        Movement.rotatePointOX(LIGHT, -ANGLE_INC);
                        break;
                    case DOWN:
                        objects.forEach(o -> o.rotateOX(ANGLE_INC));
                        Movement.rotatePointOX(LIGHT, ANGLE_INC);
                        break;
                    case LEFT:
                        objects.forEach(o -> o.rotateOY(ANGLE_INC));
                        Movement.rotatePointOY(LIGHT, ANGLE_INC);
                        break;
                    case RIGHT:
                        objects.forEach(o -> o.rotateOY(-ANGLE_INC));
                        Movement.rotatePointOY(LIGHT, -ANGLE_INC);
                        break;
                    case Q:
                        objects.forEach(o -> o.rotateOZ(ANGLE_INC));
                        Movement.rotatePointOZ(LIGHT, ANGLE_INC);
                        break;
                    case E:
                        objects.forEach(o -> o.rotateOZ(-ANGLE_INC));
                        Movement.rotatePointOZ(LIGHT, -ANGLE_INC);
                        break;
                    case Z:
                        view.changeFov(ZOOM_INC);
                        break;
                    case X:
                        view.changeFov(-ZOOM_INC);
                        break;
                    default:
                        System.out.println("Button " + button + " is unhandled");
                }
            }
        });
    }

    private void timeInfo(Long time) {
        if (AVG_TIME_PER_FRAME) {
            timeSum += (System.currentTimeMillis() - time);
            frames++;
            if (frames % 100 == 0) {
                System.out.println("Avg time of last 100 frames: " + timeSum / frames);
                timeSum = 0;
                frames = 0;
            }
        }
    }

    public HBox initSlider(String label, int min, int max, double value, double inc,
            ChangeListener<Number> changeListener) {
        HBox hBox = new HBox();

        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(value);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(inc);
        slider.setFocusTraversable(false);
        slider.valueProperty().addListener(changeListener);

        Label description = new Label(label);
        description.setPrefWidth(90);
        description.setTextFill(Color.WHITE);
        hBox.getChildren().add(0, description);
        hBox.getChildren().add(1, slider);
        hBox.setSpacing(10);

        return hBox;
    }

    public static void run(String[] args) {
        launch();
    }
}