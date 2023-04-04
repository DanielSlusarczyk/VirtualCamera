package app;

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

    Map<KeyCode, Boolean> pressedButtons = new HashMap<>() {
        {
            put(FORWARD_MOVE, false);
            put(LEFT_MOVE, false);
            put(BACKWARD_MOVE, false);
            put(RIGHT_MOVE, false);
            put(LEFT_ROTATION, false);
            put(RIGHT_ROTATION, false);
            put(UP_ROTATION, false);
            put(DOWN_ROTATION, false);
        }
    };

    private void addObjects() {
        objects.add(new Prism());

        initObjects();
    }

    private void initObjects() {
        objects.forEach(o -> {
            mainPane.getChildren().addAll(o.moveZ(100).rotateOZ(90).getLines());
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
                        if(button == FORWARD_MOVE){
                            objects.forEach(o -> o.moveZ(-MOVE_INC));
                        } else if (button == BACKWARD_MOVE){
                            objects.forEach(o -> o.moveZ(MOVE_INC));
                        } else if (button == LEFT_MOVE){
                            objects.forEach(o -> o.moveX(MOVE_INC));
                        } else if (button == RIGHT_MOVE){
                            objects.forEach(o -> o.moveX(-MOVE_INC));
                        } else if (button == UP_ROTATION){
                            objects.forEach(o -> o.rotateOX(-ANGLE_INC));
                        } else if (button == DOWN_ROTATION){
                            objects.forEach(o -> o.rotateOX(ANGLE_INC));
                        } else if (button == LEFT_ROTATION){
                            objects.forEach(o -> o.rotateOY(ANGLE_INC));
                        } else if (button == RIGHT_ROTATION){
                            objects.forEach(o -> o.rotateOY(-ANGLE_INC));
                        }
                    }
                });

                mainPane.getChildren().clear();
                objects.forEach(o -> mainPane.getChildren().addAll(o.getLines()));
            }
        };

        timer.start();
    }

    public static void run(String[] args) {
        launch();
    }
}