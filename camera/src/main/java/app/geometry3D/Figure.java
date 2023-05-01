package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.config.Configuration;
import app.control.Movement;
import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Side;
import app.geometry2D.Triangle;
import app.transform.Operation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import lombok.Getter;

public class Figure implements Configuration {
    @Getter
    private List<Triangle> sides = new ArrayList<>();
    protected View view;
    protected Point reference;

    public void setSides(List<Side> sides) {
        sides.forEach(side -> this.sides.addAll(Operation.triangule(side)));
    }

    public void setReference(Point point) {
        this.reference = point;
        this.sides.forEach(side -> side.setOrientation(reference));
    }

    public List<Shape> getDrawable() {
        return SET_FILL ? getPolygons() : getLines();
    }

    public List<Shape> getLines() {
        List<Shape> toDraw = new ArrayList<>();

        sides.stream().filter(side -> side.isVisible()).flatMap(Triangle::getEdgeStream).forEach(edge -> {
            Point A = view.projectPoint(edge.getA());
            Point B = view.projectPoint(edge.getB());

            Line line = new Line(A.getX(), A.getY(), B.getX(), B.getY());
            toDraw.add(line);
        });

        return toDraw;
    }

    public List<Shape> getPolygons() {
        List<Shape> toDraw = new ArrayList<>();

        sides.stream().filter(side -> side.isVisible()).forEach(triangle -> {
            Point A = view.projectPoint(triangle.getPoint(0));
            Point B = view.projectPoint(triangle.getPoint(1));
            Point C = view.projectPoint(triangle.getPoint(2));

            Polygon polygon = new Polygon(new double[] {
                    A.getX(), A.getY(),
                    B.getX(), B.getY(),
                    C.getX(), C.getY()
            });
        
            double phong = triangle.getPhongScalar();

            polygon.setFill(Color.rgb((int)(Color_R * phong), (int)(Color_G * phong), (int)(Color_B * phong)));
            polygon.setStroke(polygon.getFill());

            toDraw.add(polygon);
        });

        return toDraw;
    }

    public Figure rotateOX(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOX(p, angle));
        Movement.rotatePointOX(reference, angle);
        Movement.rotatePointOX(LIGHT, angle);

        return this;
    }

    public Figure rotateOY(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOY(p, angle));
        Movement.rotatePointOY(reference, angle);
        Movement.rotatePointOY(LIGHT, angle);

        return this;
    }

    public Figure rotateOZ(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOZ(p, angle));
        Movement.rotatePointOZ(reference, angle);
        Movement.rotatePointOZ(LIGHT, angle);

        return this;
    }

    public Figure moveX(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, x, 0.0, 0.0));
        Movement.move(reference, x, 0.0, 0.0);
        Movement.move(LIGHT, x, 0.0, 0.0);

        return this;
    }

    public Figure moveY(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, 0.0, x, 0.0));
        Movement.move(reference, 0.0, x, 0.0);
        Movement.move(LIGHT, 0.0, x, 0.0);

        return this;
    }

    public Figure moveZ(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, 0.0, 0.0, x));
        Movement.move(reference, 0.0, 0.0, x);
        Movement.move(LIGHT, 0.0, 0.0, x);

        return this;
    }

    public void description() {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> System.out.println(p));
    }
}
