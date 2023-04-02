package app;

import app.configuration.Configuration;
import app.geometry.Edge;
import app.objects.Prism;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



public class Main extends Application implements Configuration {

    @Override
    public void start(Stage stage) {
        
        Pane pane = new Pane();

        Prism prism = new Prism();
        for (Edge l : prism.rotateOY(30).rotateOX(45).project().getEdges()){

            Line line = new Line(l.getA().getX(), l.getA().getY(), l.getB().getX(), l.getB().getY());

            pane.getChildren().add(line);
        }

        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}