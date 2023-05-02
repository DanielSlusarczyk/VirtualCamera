package app.config;

import app.geometry2D.Point;

public interface Configuration {
    double WINDOW_WIDTH = 640;
    double WINDOW_HEIGHT = 640;

    double MOVE_INC = 1.0;
    double ANGLE_INC = 1.0;
    double ZOOM_INC = 1.0;

    Point LIGHT = new Point(20,20, 100, 0);

    double K_a = 0.25;
    double K_s = 0.75;
    double K_d = 0.25;
    double alpha = 50;

    // FILL_RGB = null - only grid
    Point FILL_RGB = new Point(48.0, 137.0, 227.0, 1);
}
