package app;

import app.configuration.Configuration;
import app.objects.Coordinate;
import app.objects.Prism;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Main extends Application implements Configuration {

    @Override
    public void start(Stage stage) {
        
        Pane pane = new Pane();
        Prism prism = new Prism();
        Coordinate c = new Coordinate();
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        pane.getChildren().addAll(prism.getLines());

        scene.setOnKeyPressed(event -> {
            pane.getChildren().clear();
            switch(event.getCode()) {
                case W:
                    prism.moveX(1.0);
                    break;
                case A:
                    prism.moveX(-1.0);
                    break;
                case E:
                    prism.moveY(1.0);
                    break;
                case S:
                    prism.moveY(-1.0);
                    break;
                case R:
                    prism.moveZ(1.0);
                    break;
                case D:
                    prism.moveZ(-1.0);
                    break;
                case T:
                    prism.rotateOX(1.0);
                    break;
                case F:
                    prism.rotateOX(-1.0);
                    break;
                case Y:
                    prism.rotateOY(1.0);
                    break;
                case G:
                    prism.rotateOY(-1.0);
                    break;
                case U:
                    prism.rotateOZ(1.0);
                    break;
                case H:
                    prism.rotateOZ(-1.0);
                    break;
                default:
                    break;
            }
            pane.getChildren().addAll(prism.getLines());
            pane.getChildren().addAll(c.getLines());
            stage.setScene(scene);
        });
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}