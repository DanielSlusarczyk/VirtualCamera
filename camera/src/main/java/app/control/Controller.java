package app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.config.Configuration;
import app.geometry2D.Point;
import app.geometry3D.Figure;
import app.geometry3D.Icosphere;
import app.geometry3D.Sphere;
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
    private long timeSum = 0;
    private long frames = 0;

    Map<KeyCode, Boolean> pressedButtons = new HashMap<>();

    private void addObjects() {
        // objects.add(new Prism(new Point(-25.0, 0.0, 0.0), new Point(-5.0, 20.0,
        // 20.0),view));
        // objects.add(new Prism(new Point(5.0, 0.0, 0.0), new Point(25.0, 20.0,
        // 50.0),view));
        //objects.add(new Sphere(new Point(50, 0, 0), 20, 30, view));
        objects.add(new Icosphere(new Point(50, 0, 0), 20, 3, view));

        initObjects();
    }

    private void initObjects() {
        objects.forEach(o -> {
            mainPane.getChildren().addAll(o.rotateOX(90).moveZ(150).getDrawable());
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

                mainPane.getChildren().clear();
                objects.forEach(o -> mainPane.getChildren().addAll(o.getDrawable()));

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

    public static void run(String[] args) {
        launch();
    }
}