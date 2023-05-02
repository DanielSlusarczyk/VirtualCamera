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
        return FILL_RGB != null ? getPolygons() : getLines();
    }

    public List<Shape> getLines() {
        List<Shape> toDraw = new ArrayList<>();

        sides.stream().filter(side -> side.isVisible()).flatMap(Triangle::getEdgeStream).forEach(edge -> toDraw.add(edge.mapToLine(view)));

        return toDraw;
    }

    public List<Shape> getPolygons() {
        List<Shape> toDraw = new ArrayList<>();

        sides.stream().filter(side -> side.isVisible()).forEach(triangle -> {
            Polygon polygon = triangle.mapToPolygon(view);
        
            // Phong reflecion model
            double phong = triangle.getPhongScalar();

            polygon.setFill(Color.rgb((int)(FILL_RGB.getX() * phong), (int)(FILL_RGB.getY() * phong), (int)(FILL_RGB.getZ() * phong), FILL_RGB.getW()));
            polygon.setStroke(polygon.getFill());

            toDraw.add(polygon);
        });

        return toDraw;
    }

    public Figure rotateOX(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOX(p, angle));
        Movement.rotatePointOX(reference, angle);

        return this;
    }

    public Figure rotateOY(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOY(p, angle));
        Movement.rotatePointOY(reference, angle);

        return this;
    }

    public Figure rotateOZ(double angle) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.rotatePointOZ(p, angle));
        Movement.rotatePointOZ(reference, angle);

        return this;
    }

    public Figure moveX(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, x, 0.0, 0.0));
        Movement.move(reference, x, 0.0, 0.0);

        return this;
    }

    public Figure moveY(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, 0.0, x, 0.0));
        Movement.move(reference, 0.0, x, 0.0);

        return this;
    }

    public Figure moveZ(double x) {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> Movement.move(p, 0.0, 0.0, x));
        Movement.move(reference, 0.0, 0.0, x);

        return this;
    }

    public void description() {
        sides.stream().flatMap(Side::getPointsStream).forEach(p -> System.out.println(p));
    }
}
