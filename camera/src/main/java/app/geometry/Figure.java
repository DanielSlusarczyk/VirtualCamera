package app.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.config.Configuration;
import app.control.Movement;
import app.control.View;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

public class Figure implements Configuration{
    @Getter @Setter
    private Point[] points;
    @Getter @Setter
    private boolean[][] edges;
    @Getter @Setter
    private Color [] colors;
    private View view = new View();

    private Point[] copyPoints(){
        Point[] copyPoints = new Point[points.length];
        for(int i = 0; i < copyPoints.length; i++){
            copyPoints[i] = new Point(points[i].getX(), points[i].getY(), points[i].getZ());
        }
        return copyPoints;
    }

    public Figure project(){
        Figure projected = new Figure();
        projected.setEdges(this.getEdges());
        projected.setPoints(this.copyPoints());

        Arrays.stream(projected.getPoints()).forEach(p -> {
            view.projectPoint(p);
            view.centerPoint(p);
        });

        return projected;
    }

    public List<Line> getLines(){
        List<Line> toDraw = new ArrayList<>();
        Point[] p = this.project().getPoints();
        
        for(int i = 0; i < edges.length; i++){
            for(int j = 0; j < edges.length; j++){
                if(edges[i][j]){
                    Line line = new Line(p[i].getX(), p[i].getY(), p[j].getX(), p[j].getY());
                    if(colors != null){
                        line.setStroke(colors[i]);
                    }
                    toDraw.add(line);
                }
            }
        }

        return toDraw;
    }

    public Figure rotateOX(double angle){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.rotatePointOX(p, angle));

        return this;
    }

    public Figure rotateOY(double angle){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.rotatePointOY(p, angle));

        return this;
    }

    public Figure rotateOZ(double angle){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.rotatePointOZ(p, angle));

        return this;
    }

    public Figure moveX(double x){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.move(p, x, 0.0, 0.0));

        return this;
    }

    public Figure moveY(double x){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.move(p, 0.0, x, 0.0));

        return this;
    }

    public Figure moveZ(double x){
        Arrays.stream(this.getPoints()).forEach(p -> Movement.move(p, 0.0, 0.0, x));

        return this;
    }

    public void description(){
        Arrays.stream(this.getPoints()).forEach(p -> System.out.println(p));
    }
}
