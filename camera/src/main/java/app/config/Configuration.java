package app.config;

import javafx.scene.input.KeyCode;

public interface Configuration {
    double WINDOW_WIDTH = 640;
    double WINDOW_HEIGHT = 640;
    double MOVE_INC = 1.0;
    double ANGLE_INC = 1.0;

    final KeyCode LEFT_MOVE = KeyCode.A;
    KeyCode RIGHT_MOVE = KeyCode.D;
    KeyCode FORWARD_MOVE = KeyCode.W;
    KeyCode BACKWARD_MOVE = KeyCode.S;
    KeyCode LEFT_ROTATION = KeyCode.LEFT;
    KeyCode RIGHT_ROTATION = KeyCode.RIGHT;
    KeyCode UP_ROTATION = KeyCode.UP;
    KeyCode DOWN_ROTATION = KeyCode.DOWN;

    double HALF_WINDOW_WIDTH = WINDOW_WIDTH / 2;
    double HALF_WINDOW_HEIGHT = WINDOW_HEIGHT / 2;
    double ASPECT_RATIO = WINDOW_WIDTH/WINDOW_HEIGHT;
}
