package app.geometry2D;

import app.transform.Operation;
import lombok.Getter;

public class Triangle extends Side {
    @Getter
    private Point normalVector;

    public Triangle() {
        super(3);
    }

    public Triangle add(Point point) {
        super.add(point);

        return this;
    }

    public boolean isVisible() {
        return normalVector.getMatrix().dot(getPoint(0).getMatrix()) < 0;
    }

    public void setOrientation(Point reference) {
        Point ab_vector = edges.get(0).getVector();
        Point ac_vector = edges.get(2).getReversedVector();

        normalVector = Operation.crossProduct(ab_vector, ac_vector);
        normalVector.setW(normalVector.getMatrix().transpose().mult(getPoint(0).getMatrix()).negative().elementSum());

        Point testPoint = new Point(getPoint(0).getMatrix().plus(normalVector.getMatrix()), 0);

        if (value(reference, normalVector) * value(testPoint, normalVector) > 0) {
            Point A = getPoint(0);
            Point B = getPoint(1);
            Point C = getPoint(2);

            edges.clear();
            this.add(A).add(C).add(B);

            normalVector.setMatrix(normalVector.getMatrix().negative());
        }

        normalVector.setW(0);
    }

    public double getPhongScalar() {
        Point center = getCenter();
        Point N = Operation.scale(normalVector);
        Point V = Operation.scale(new Point(center.getMatrix().negative()));
        // R = (2*N.L)*N - L
        Point R = Operation.scale(
                new Point(N.getMatrix().scale(2 * LIGHT.getMatrix().dot(N.getMatrix())).minus(LIGHT.getMatrix())));
        Point L = Operation.scale(new Point(LIGHT.getMatrix().minus(center.getMatrix())));

        double ambient = K_a;
        double diffuse = K_d * N.getMatrix().dot(L.getMatrix());
        double specular = K_s * Math.pow(Math.max(0.0, R.getMatrix().dot(V.getMatrix())), alpha);

        return Math.max(Math.min(1.0, ambient + diffuse + specular), 0.0);
    }

    private double value(Point point, Point plane) {
        return point.getX() * plane.getX() +
                point.getY() * plane.getY() +
                point.getZ() * plane.getZ() +
                plane.getW();
    }

    public Point getCenter() {
        return new Point(
                getPoint(0).getMatrix().plus(getPoint(1).getMatrix()).plus(getPoint(2).getMatrix()).scale(1.0 / 3.0),
                0);
    }
}