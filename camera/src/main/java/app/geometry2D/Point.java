package app.geometry2D;

import org.ejml.simple.SimpleMatrix;

import lombok.Getter;
import lombok.Setter;

public class Point {
    @Getter
    @Setter
    private SimpleMatrix matrix;

    public Point(double x, double y, double z) {
        this.matrix = new SimpleMatrix(new double[][] { { x }, { y }, { z }, { 1.0 } });
    }

    public Point(SimpleMatrix matrix) {
        this.matrix = matrix;
    }
    
    public Point(SimpleMatrix matrix, double w) {
        this.matrix = matrix;
        setW(w);
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

    public double getW(){
        return matrix.get(3,0);
    }

    public void setX(double x) {
        matrix.set(0, 0, x);
    }

    public void setY(double y) {
        matrix.set(1, 0, y);
    }

    public void setZ(double z) {
        matrix.set(2, 0, z);
    }

    public void setW(double w) {
        matrix.set(3, 0, w);
    }

    @Override
    public String toString(){
        return "[Point][" + getX() + " " + getY() + " " + getZ() +  "] (" + getW() + ")";
    }
}
