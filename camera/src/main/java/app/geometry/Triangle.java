package app.geometry;

import app.config.Configuration;
import app.transform.Operation;

public class Triangle extends Polygon implements Configuration {
    public Triangle() {
        super(3);
    }

    public Point normalVector(Point reference) {
        Point ab_vector = edges.get(0).getVector();
        Point ac_vector = edges.get(2).getReversedVector();

        Point plane = Operation.crossProduct(ab_vector, ac_vector);
        plane.setW(plane.getMatrix().transpose().mult(getPoint(0).getMatrix()).negative().elementSum());

        Point testPoint = new Point(getPoint(0).getMatrix().plus(plane.getMatrix()), 0);

        if (reference.getMatrix().transpose().mult(plane.getMatrix()).get(0)
                * testPoint.getMatrix().transpose().mult(plane.getMatrix()).get(0) > 0) {
            Point A = getPoint(0);
            Point B = getPoint(1);
            Point C = getPoint(2);

            edges.clear();
            this.add(A).add(C).add(B);

            return normalVector(reference);
        }

        plane.setW(0);
        return plane;
    }
}