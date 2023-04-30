package app.geometry;

import java.util.ArrayList;
import java.util.List;

import app.config.Configuration;
import app.control.Movement;
import app.control.View;
import app.transform.Operation;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

public class Figure implements Configuration{
    @Getter
    private List<Polygon> sides = new ArrayList<>();
    protected View view;
    @Setter
    protected Point internal;

    public void setSides(List<Polygon> sides){
        sides.forEach(side -> this.sides.addAll(Operation.triangule(side)));
    }

    public List<Line> getLines(){
        List<Line> toDraw = new ArrayList<>();

        sides.stream().flatMap(Polygon::getEdgeStream).forEach(edge ->{
            Point A = view.projectPoint(edge.getA());
            Point B = view.projectPoint(edge.getB());

            Line line = new Line(A.getX(), A.getY(), B.getX(), B.getY());
            toDraw.add(line);
        });

        return toDraw;
    }

    public Figure rotateOX(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOX(p, angle));

        return this;
    }

    public Figure rotateOY(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOY(p, angle));

        return this;
    }

    public Figure rotateOZ(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOZ(p, angle));

        return this;
    }

    public Figure moveX(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, x, 0.0, 0.0));

        return this;
    }

    public Figure moveY(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, 0.0, x, 0.0));

        return this;
    }

    public Figure moveZ(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, 0.0, 0.0, x));

        return this;
    }

    public void description(){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> System.out.println(p));
    }

}
