package app.control;

import org.ejml.simple.SimpleMatrix;

import app.config.Configuration;
import app.geometry.Point;

public class View implements Configuration {
    private double far = 100;
    private double near = 0.1;
    private SimpleMatrix projectionMatrix;
    
    public Point centerPoint(Point point){
        point.setX(point.getX() + HALF_WINDOW_WIDTH);
        point.setY(point.getY() + HALF_WINDOW_HEIGHT);

        return point;
    }
    
    public Point projectPoint(Point point, double fov) {
        this.projectionMatrix = defineProjectionMatrix(fov);
        point.setMatrix(projectionMatrix.mult(point.getMatrix()));
        

        point.setX(point.getX()/point.getW() * HALF_WINDOW_WIDTH);
        point.setY(point.getY()/point.getW() * HALF_WINDOW_HEIGHT);
        point.setZ(point.getZ()/point.getW());
        
        return point;
    }

    private SimpleMatrix defineProjectionMatrix(double fov) {
        double tanHalfFOV = Math.tan(Math.toRadians(fov / 2.0));
        double zRange = far - near;
        //System.out.println(fov);
        
        double[][] values = {
            {1.0 / (tanHalfFOV * ASPECT_RATIO), 0.0, 0.0, 0.0},
            {0.0, 1.0 / tanHalfFOV, 0.0, 0.0},
            {0.0, 0.0, far / zRange, - far * near / zRange},
            {0.0, 0.0, 1.0, 0.0}
    };

        return new SimpleMatrix(values);
    }
}
