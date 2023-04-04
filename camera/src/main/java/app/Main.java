package app;

import java.util.ArrayList;
import java.util.List;

import app.config.Configuration;
import app.geometry.Figure;
import app.geometry.Prism;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements Configuration {
    private Pane mainPane = new Pane();
    private Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    private List<Figure> objects = new ArrayList<>();

    private void addObjects(){
        objects.add(new Prism());
        
        initObjects();
    }

    private void initObjects(){
        objects.forEach(o -> {
            mainPane.getChildren().addAll(o.moveZ(100).rotateOZ(90).getLines());
        });
    }

    @Override
    public void start(Stage stage) {
        addObjects();

        scene.setOnKeyPressed(event -> {
            mainPane.getChildren().clear();
            switch(event.getCode()) {
                case A:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.moveX(MOVE_INC).getLines()));
                    break;
                case D:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.moveX(-MOVE_INC).getLines()));
                    break;
                case S:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.moveZ(MOVE_INC).getLines()));
                    break;
                case W:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.moveZ(-MOVE_INC).getLines()));
                    break;
                case UP:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.rotateOX(-ANGLE_INC).getLines()));
                    break;
                case DOWN:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.rotateOX(ANGLE_INC).getLines()));
                    break;
                case RIGHT:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.rotateOY(-ANGLE_INC).getLines()));
                    break;
                case LEFT:
                    objects.forEach(o -> mainPane.getChildren().addAll(o.rotateOY(ANGLE_INC).getLines()));
                    break;
                default:
                    System.out.println("Key " + event.getCode() + " is unhandled");
            }
        });
    
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}