package app.config;

public interface Configuration {
    double WINDOW_WIDTH = 640;
    double WINDOW_HEIGHT = 640;
    double MOVE_INC = 1.0;
    double ANGLE_INC = 1.0;   

    double HALF_WINDOW_WIDTH = WINDOW_WIDTH / 2;
    double HALF_WINDOW_HEIGHT = WINDOW_HEIGHT / 2;
    double ASPECT_RATIO = WINDOW_WIDTH/WINDOW_HEIGHT;
}
