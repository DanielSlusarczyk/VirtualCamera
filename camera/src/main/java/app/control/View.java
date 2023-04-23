package app.control;

import org.ejml.simple.SimpleMatrix;

import app.config.Configuration;
import app.geometry.Point;

public class View implements Configuration {
    private double far = 100;
    private double near = 0.1;
    private double fov = 60.0;
    private SimpleMatrix projectionMatrix = defineProjectionMatrix();
    
    private final double HALF_WINDOW_WIDTH = WINDOW_WIDTH / 2;
    private final double HALF_WINDOW_HEIGHT = WINDOW_HEIGHT / 2;
    private final double ASPECT_RATIO = WINDOW_WIDTH/WINDOW_HEIGHT;
    
    public Point projectPoint(Point point) {
        
        Point projected = new Point(projectionMatrix.mult(point.getMatrix()));

        projected.setX(projected.getX()/projected.getW() * HALF_WINDOW_WIDTH);
        projected.setY(projected.getY()/projected.getW() * HALF_WINDOW_HEIGHT);
        projected.setZ(projected.getZ()/projected.getW());
        
        return centerPoint(projected);
    }

    public void changeFov(double value){
        fov = fov + value > 0.0 && fov + value < 180.0 ? fov + value : fov;

        this.projectionMatrix = defineProjectionMatrix();
    }

    private Point centerPoint(Point point){
        point.setX(point.getX() + HALF_WINDOW_WIDTH);
        point.setY(point.getY() + HALF_WINDOW_HEIGHT);

        return point;
    }

    private SimpleMatrix defineProjectionMatrix() {
        double tanHalfFOV = Math.tan(Math.toRadians(fov / 2.0));
        double zRange = far - near;
        
        double[][] values = {
            {1.0 / (tanHalfFOV * ASPECT_RATIO), 0.0, 0.0, 0.0},
            {0.0, 1.0 / tanHalfFOV, 0.0, 0.0},
            {0.0, 0.0, far / zRange, - far * near / zRange},
            {0.0, 0.0, 1.0, 0.0}
    };

        return new SimpleMatrix(values);
    }
}
