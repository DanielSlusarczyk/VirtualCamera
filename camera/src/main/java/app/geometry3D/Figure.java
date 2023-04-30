package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.config.Configuration;
import app.control.Movement;
import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Polygon;
import app.geometry2D.Triangle;
import app.transform.Operation;
import javafx.scene.shape.Line;
import lombok.Getter;

public class Figure implements Configuration{
    @Getter
    private List<Triangle> sides = new ArrayList<>();
    protected View view;
    protected Point reference;

    public void setSides(List<Polygon> sides){
        sides.forEach(side -> this.sides.addAll(Operation.triangule(side)));
    }

    public void setReference(Point point){
        this.reference = point;
        this.sides.forEach(side -> side.norm(reference));
    }

    public List<Line> getLines(){
        List<Line> toDraw = new ArrayList<>();

        System.out.println("Reference: " + reference);

        sides.stream().filter(side -> side.isVisible()).flatMap(Triangle::getEdgeStream).forEach(edge ->{
            Point A = view.projectPoint(edge.getA());
            Point B = view.projectPoint(edge.getB());

            Line line = new Line(A.getX(), A.getY(), B.getX(), B.getY());
            toDraw.add(line);
        });

        return toDraw;
    }

    public Figure rotateOX(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOX(p, angle));
        Movement.rotatePointOX(reference, angle);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public Figure rotateOY(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOY(p, angle));
        Movement.rotatePointOY(reference, angle);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public Figure rotateOZ(double angle){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.rotatePointOZ(p, angle));
        Movement.rotatePointOZ(reference, angle);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public Figure moveX(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, x, 0.0, 0.0));
        Movement.move(reference, x, 0.0, 0.0);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public Figure moveY(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, 0.0, x, 0.0));
        Movement.move(reference, 0.0, x, 0.0);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public Figure moveZ(double x){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> Movement.move(p, 0.0, 0.0, x));
        Movement.move(reference, 0.0, 0.0, x);
        this.sides.forEach(side -> side.norm(reference));

        return this;
    }

    public void description(){
        sides.stream().flatMap(Polygon::getPointsStream).forEach(p -> System.out.println(p));
    }
}
