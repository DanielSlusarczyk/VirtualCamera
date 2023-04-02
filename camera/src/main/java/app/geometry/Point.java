package app.geometry;

import org.ejml.simple.SimpleMatrix;

import lombok.Getter;
import lombok.Setter;


public class Point {
    @Getter @Setter
    private SimpleMatrix matrix;

    public Point(double x, double y, double z) {
        this.matrix = new SimpleMatrix(new double[][] { { x }, { y }, { z }, { 1 } });
    }

    public Point(SimpleMatrix matrix){
        this.matrix = matrix;
    }

    public double getX() {
        return matrix.get(0, 0);
    }

    public double getY() {
        return matrix.get(1, 0);
    }

    public double getZ() {
        return matrix.get(2, 0);
    }
}
