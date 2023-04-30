package app.geometry;

import app.config.Configuration;
import app.transform.Operation;
import lombok.Getter;

public class Triangle extends Polygon implements Configuration {
    @Getter
    private Point normalVector;

    public Triangle() {
        super(3);
    }

    public Triangle add(Point point){
        super.add(point);

        return this;
    }

    public boolean isVisible() {
        Point viewVector = new Point(VIEW_POINT.getMatrix().minus(getPoint(0).getMatrix()));

        return normalVector.getMatrix().dot(viewVector.getMatrix()) > 0;
    }

    public void norm(Point reference) {
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

            plane.setMatrix(plane.getMatrix().negative());
        }

        plane.setW(0);
        normalVector = plane;
    }

    public Edge normalVectorToPrint(){
        Point center = new Point(
            getPoint(0).getMatrix().plus(getPoint(1).getMatrix()).plus(getPoint(2).getMatrix())
        );
        System.out.println(getPoint(0));
        System.out.println(getPoint(1));
        System.out.println(getPoint(2));
        System.out.println("SUMA: " + getPoint(0).getMatrix().plus(getPoint(1).getMatrix()).plus(getPoint(2).getMatrix()).scale(1/3));


        System.out.println(center);

        Point vector = new Point(center.getMatrix().plus(normalVector.getMatrix()));

        return new Edge(center, vector);
    }
}