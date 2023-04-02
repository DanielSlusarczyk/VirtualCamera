package app.configuration;

public interface Configuration {
    int WINDOW_WIDTH = 640;
    int WINDOW_HEIGHT = 480;

    double HALF_WINDOW_WIDTH = WINDOW_WIDTH / 2;
    double HALF_WINDOW_HEIGHT = WINDOW_HEIGHT / 2;
    double ASPECT_RATIO = WINDOW_WIDTH/WINDOW_HEIGHT;
}
