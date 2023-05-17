package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Side;
import app.geometry2D.Triangle;

public class Icosphere extends Figure {
    private final double phi = (1.0 + Math.sqrt(5.0) * 0.5);
    private final List<Side> mesh = new ArrayList<>();
    private final Point center;
    private final double r;

    public Icosphere(Point center, double r, int denisty, View view) {
        this.view = view;
        this.center = center;
        this.r = r;

        double a = r;
        double b = r / phi;

        Point V1 = scale(new Point(center.getX() + 0, center.getY() + b, center.getZ() - a));
        Point V2 = scale(new Point(center.getX() + b, center.getY() + a, center.getZ() + 0));
        Point V3 = scale(new Point(center.getX() - b, center.getY() + a, center.getZ() + 0));
        Point V4 = scale(new Point(center.getX() + 0, center.getY() + b, center.getZ() + a));
        Point V5 = scale(new Point(center.getX() + 0, center.getY() - b, center.getZ() + a));
        Point V6 = scale(new Point(center.getX() - a, center.getY() + 0, center.getZ() + b));
        Point V7 = scale(new Point(center.getX() + 0, center.getY() - b, center.getZ() - a));
        Point V8 = scale(new Point(center.getX() + a, center.getY() + 0, center.getZ() - b));
        Point V9 = scale(new Point(center.getX() + a, center.getY() + 0, center.getZ() + b));
        Point V10 = scale(new Point(center.getX() - a, center.getY() + 0, center.getZ() - b));
        Point V11 = scale(new Point(center.getX() + b, center.getY() - a, center.getZ() + 0));
        Point V12 = scale(new Point(center.getX() - b, center.getY() - a, center.getZ() + 0));

        List<Triangle> sides = new ArrayList<>() {
            {
                add(new Triangle(FILL_RGB).add(V3).add(V2).add(V1));
                add(new Triangle(FILL_RGB).add(V2).add(V3).add(V4));
                add(new Triangle(FILL_RGB).add(V6).add(V5).add(V4));
                add(new Triangle(FILL_RGB).add(V5).add(V9).add(V4));
                add(new Triangle(FILL_RGB).add(V8).add(V7).add(V1));
                add(new Triangle(FILL_RGB).add(V7).add(V10).add(V1));
                add(new Triangle(FILL_RGB).add(V12).add(V11).add(V5));
                add(new Triangle(FILL_RGB).add(V11).add(V12).add(V7));
                add(new Triangle(FILL_RGB).add(V10).add(V6).add(V3));
                add(new Triangle(FILL_RGB).add(V6).add(V10).add(V12));
                add(new Triangle(FILL_RGB).add(V9).add(V8).add(V2));
                add(new Triangle(FILL_RGB).add(V8).add(V9).add(V11));
                add(new Triangle(FILL_RGB).add(V3).add(V6).add(V4));
                add(new Triangle(FILL_RGB).add(V9).add(V2).add(V4));
                add(new Triangle(FILL_RGB).add(V10).add(V3).add(V1));
                add(new Triangle(FILL_RGB).add(V2).add(V8).add(V1));
                add(new Triangle(FILL_RGB).add(V12).add(V10).add(V7));
                add(new Triangle(FILL_RGB).add(V8).add(V11).add(V7));
                add(new Triangle(FILL_RGB).add(V6).add(V12).add(V5));
                add(new Triangle(FILL_RGB).add(V11).add(V9).add(V5));
            }
        };

        sides.forEach(s -> split(s, denisty));

        this.setSides(mesh);

        this.setReference(center);
    }

    //        V3
    //        /\
    //       /  \
    //      /    \
    //    B/______\C
    //    / \    / \
    //   /   \  /   \
    //  /_____\/_____\
    // V1      A      V2
    private void split(Triangle t, int deepth) {
        if (deepth == 0) {
            mesh.add(t);
        } else {
            Point A = scale(t.getEdge(0).getCenter());
            Point B = scale(t.getEdge(1).getCenter());
            Point C = scale(t.getEdge(2).getCenter());

            Triangle t1 = new Triangle(FILL_RGB).add(t.getPoint(1)).add(A).add(B);
            Triangle t2 = new Triangle(FILL_RGB).add(t.getPoint(0)).add(C).add(A);
            Triangle t3 = new Triangle(FILL_RGB).add(t.getPoint(2)).add(B).add(C);
            Triangle t4 = new Triangle(FILL_RGB).add(A).add(C).add(B);

            split(t1, deepth - 1);
            split(t2, deepth - 1);
            split(t3, deepth - 1);
            split(t4, deepth - 1);
        }
    }

    // Project icohedron to icosphere
    private Point scale(Point p) {
        Point v = new Point(p.getMatrix().minus(center.getMatrix()), 0);
        double length = Math.sqrt(v.getMatrix().elementPower(2).elementSum());

        p.setX(center.getX() + v.getX() * r / length);
        p.setY(center.getY() + v.getY() * r / length);
        p.setZ(center.getZ() + v.getZ() * r / length);

        return p;
    }
}