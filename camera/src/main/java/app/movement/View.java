package app.movement;

import org.ejml.simple.SimpleMatrix;

import app.configuration.Configuration;
import app.geometry.Point;

public class View implements Configuration {
    private double zFar = 100;
    private double zNear = 0.1;
    private double fovY = 20;
    private SimpleMatrix projectionMatrix = defineProjectionMatrix();
    
    public Point centerPoint(Point point){
        point.setX(point.getX() + HALF_WINDOW_WIDTH);
        point.setY(point.getY() + HALF_WINDOW_HEIGHT);

        return point;
    }
    
    public Point projectPoint(Point point) {
        point.setMatrix(projectionMatrix.mult(point.getMatrix()));
        
        return point;
    }

    private SimpleMatrix defineProjectionMatrix() {
        double f = 1.0 / Math.tan(Math.toRadians(fovY / 2.0));
        double q = zFar / (zFar - zNear);
        
        double[][] values = new double[][] {
            { f / ASPECT_RATIO, 0, 0, 0 },
            { 0, f, 0, 0 },
            { 0, 0, q, -q * zNear },
            { 0, 0, 1, 0 }
        };

        return new SimpleMatrix(values);
    }
}
