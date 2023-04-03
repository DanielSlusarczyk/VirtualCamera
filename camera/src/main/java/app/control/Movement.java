package app.control;

import org.ejml.simple.SimpleMatrix;

import app.config.Configuration;
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

    public static void rotatePointOZ(Point point, double angle) {
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        SimpleMatrix rotationMatrix = new SimpleMatrix(new double[][]{
            {cos, -sin, 0.0, 0.0},
            {sin, cos, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        });
        SimpleMatrix resultMatrix = rotationMatrix.mult(point.getMatrix());
        
        point.setMatrix(resultMatrix);
    }

    public static void move(Point point, double x, double y, double z) {
        
        SimpleMatrix translationMatrix = new SimpleMatrix(new double[][]{
            {1, 0, 0, x},
            {0, 1, 0, y},
            {0, 0, 1, z},
            {0, 0, 0, 1}
        });
        SimpleMatrix resultMatrix = translationMatrix.mult(point.getMatrix());
    
        point.setMatrix(resultMatrix);
    }
}
