package app.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.configuration.Configuration;
import app.geometry.Point;
import app.movement.Movement;
import app.movement.View;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

public class Figure implements Configuration{
    @Getter @Setter
    private Point[] points;
    @Getter @Setter
    private boolean[][] edges;
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
                    toDraw.add(new Line(p[i].getX(), p[i].getY(), p[j].getX(), p[j].getY()));
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

    public void description(){
        Arrays.stream(this.getPoints()).forEach(p -> System.out.println(p));
    }
}
