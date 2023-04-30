package app.config;

import app.geometry.Point;

public interface Configuration {
    double WINDOW_WIDTH = 640;
    double WINDOW_HEIGHT = 640;

    double MOVE_INC = 1.0;
    double ANGLE_INC = 1.0;
    double ZOOM_INC = 1.0;

    Point VIEW_POINT = new Point(0, 0, 0);
}
