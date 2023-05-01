package app.geometry2D;

import app.config.Configuration;
import app.transform.Operation;
import lombok.Getter;

public class Triangle extends Side implements Configuration {
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
        setNormalVector();
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

    private void setNormalVector(){
        Point ab_vector = edges.get(0).getVector();
        Point ac_vector = edges.get(2).getReversedVector();

        normalVector = Operation.crossProduct(ab_vector, ac_vector);
    }

    private double value(Point point, Point plane){
        return 
            point.getX() * plane.getX() +
            point.getY() * plane.getY() +
            point.getZ() * plane.getZ() +
            plane.getW();
    }
}