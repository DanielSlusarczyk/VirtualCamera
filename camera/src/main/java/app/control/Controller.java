package app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.config.Configuration;
import app.geometry.Figure;
import app.geometry.Prism;
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
    private double fov = 60.0;

    Map<KeyCode, Boolean> pressedButtons = new HashMap<>();

    private void addObjects() {
        objects.add(new Prism());

        initObjects();
    }

    private void initObjects() {
        objects.forEach(o -> {
            mainPane.getChildren().addAll(o.moveZ(100).rotateOZ(90).getLines(fov));
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

                pressedButtons.forEach((button, pressed) -> {
                    if (pressed) {
                        switch(button){
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
                                modifyFov(1.0);
                                break;
                            case X:
                                modifyFov(-1.0);
                                break;
                            default:
                                System.out.println("Button " + button + " is unhandled");
                        }
                    }
                });

                mainPane.getChildren().clear();
                objects.forEach(o -> mainPane.getChildren().addAll(o.getLines(fov)));
            }
        };

        timer.start();
    }

    private void modifyFov(double value){
        this.fov += value;
        this.fov = this.fov >= 180.0 ? 180.0 : this.fov;
        this.fov = this.fov <= 0.0 ? 0.0 : this.fov;
    }

    public static void run(String[] args) {
        launch();
    }
}