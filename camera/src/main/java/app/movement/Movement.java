package app.movement;

import org.ejml.simple.SimpleMatrix;

import app.configuration.Configuration;
import app.geometry.Point;

public class Movement implements Configuration{

    public static void rotatePointOX(Point point, double angle) {
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        SimpleMatrix rotationMatrix = new SimpleMatrix(new double[][] {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, cos, -sin, 0.0},
            {0.0, sin, cos, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        });
        SimpleMatrix resultMatrix = rotationMatrix.mult(point.getMatrix());
        
        point.setMatrix(resultMatrix);
    }

    public static void rotatePointOY(Point point, double angle) {
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        SimpleMatrix rotationMatrix = new SimpleMatrix(new double[][] {
            {cos, 0.0, sin, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {-sin, 0.0, cos, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        });
        SimpleMatrix resultMatrix = rotationMatrix.mult(point.getMatrix());
        
        point.setMatrix(resultMatrix);
    }


}
