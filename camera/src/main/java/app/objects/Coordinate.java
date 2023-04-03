package app.objects;

import app.geometry.Point;
import javafx.scene.paint.Color;

public class Coordinate extends Figure {

    public Coordinate(){
        Point[] points = new Point[4];
        points[0] = new Point(0.0, 0.0, 0.0);  // .
        points[1] = new Point(10.0, 0.0, 0.0); // X
        points[2] = new Point(0.0, 10.0, 0.0); // Y
        points[3] = new Point(0.0, 0.0, 10.0); // Z

        boolean[][] edges = new boolean[points.length][points.length];
        edges[0][1] = edges[0][2] = edges[0][3] = true;

        Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};

        this.setEdges(edges);
        this.setPoints(points);
        this.setColors(colors);
    }
    
}
