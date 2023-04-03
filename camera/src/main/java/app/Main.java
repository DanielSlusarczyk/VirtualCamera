package app;

import app.configuration.Configuration;
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
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setOnKeyPressed(event -> {
            pane.getChildren().clear();
            switch(event.getCode()) {
                case A:
                    prism.rotateOX(1.0);
                    break;
                case D:
                    prism.rotateOX(-1.0);
                    break;
                default:
                    break;
            }
            pane.getChildren().addAll(prism.getLines());
            stage.setScene(scene);
        });
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}