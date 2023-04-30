package app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.config.Configuration;
import app.geometry2D.Point;
import app.geometry3D.Figure;
import app.geometry3D.Prism;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller extends Application implements Configuration {
    private Pane mainPane = new Pane();
    private Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    private List<Figure> objects = new ArrayList<>();
    private View view = new View();

    Map<KeyCode, Boolean> pressedButtons = new HashMap<>();

    private void addObjects() {
        objects.add(new Prism(new Point(-25.0, 0.0, 0.0), new Point(-5.0, 20.0, 20.0), view));
        objects.add(new Prism(new Point(5.0, 0.0, 0.0), new Point(25.0, 20.0, 50.0), view));

        initObjects();
    }

    private void initObjects() {
        objects.forEach(o -> {
            mainPane.getChildren().addAll(o.rotateOX(90).moveZ(150).getLines());
        });

        scene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), true);
        });

        scene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), false);
        });
    }

    @Override
    public void start(Stage stage) {
        addObjects();

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                handleButton();

                mainPane.getChildren().clear();
                objects.forEach(o -> mainPane.getChildren().addAll(o.getLines()));
            }
        };

        timer.start();
    }

    private void handleButton() {
        pressedButtons.forEach((button, pressed) -> {
            if (pressed) {
                switch (button) {
                    case W:
                        objects.forEach(o -> o.moveZ(-MOVE_INC));
                        break;
                    case S:
                        objects.forEach(o -> o.moveZ(MOVE_INC));
                        break;
                    case A:
                        objects.forEach(o -> o.moveX(MOVE_INC));
                        break;
                    case D:
                        objects.forEach(o -> o.moveX(-MOVE_INC));
                        break;
                    case SPACE:
                        objects.forEach(o -> o.moveY(MOVE_INC));
                        break;
                    case SHIFT:
                        objects.forEach(o -> o.moveY(-MOVE_INC));
                        break;
                    case UP:
                        objects.forEach(o -> o.rotateOX(-ANGLE_INC));
                        break;
                    case DOWN:
                        objects.forEach(o -> o.rotateOX(ANGLE_INC));
                        break;
                    case LEFT:
                        objects.forEach(o -> o.rotateOY(ANGLE_INC));
                        break;
                    case RIGHT:
                        objects.forEach(o -> o.rotateOY(-ANGLE_INC));
                        break;
                    case Q:
                        objects.forEach(o -> o.rotateOZ(ANGLE_INC));
                        break;
                    case E:
                        objects.forEach(o -> o.rotateOZ(-ANGLE_INC));
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

    public static void run(String[] args) {
        launch();
    }
}