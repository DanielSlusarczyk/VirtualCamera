package app.transform;

import java.util.ArrayList;
import java.util.List;

import app.geometry2D.Edge;
import app.geometry2D.Point;
import app.geometry2D.Side;
import app.geometry2D.Triangle;

public class Operation {
    public static List<Triangle> triangule(Side input) {
        List<Edge> edges = input.getEdges();
        List<Triangle> triangulation = new ArrayList<>();

        if (edges.size() == 3) {
            triangulation.add(
                    new Triangle()
                            .add(input.getPoint(0))
                            .add(input.getPoint(1))
                            .add(input.getPoint(2)));

            return triangulation;
        }

        triangulation.add(
                new Triangle()
                        .add(edges.get(0).getA())
                        .add(edges.get(1).getA())
                        .add(edges.get(2).getA()));

        triangulation.add(
                new Triangle()
                        .add(edges.get(2).getA())
                        .add(edges.get(3).getA())
                        .add(edges.get(0).getA()));

        return triangulation;
    }

    public static Point crossProduct(Point a, Point b) {
        Point crossProduct = new Point(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX());

        crossProduct.setW(0);

        return crossProduct;
    }

    public static Point scale(Point a){
        // Norma Frobeniusa dla jednego wiersza jest równa drugiej normie.

        return new Point(a.getMatrix().scale(1/a.getMatrix().transpose().normF()));
    }
}
