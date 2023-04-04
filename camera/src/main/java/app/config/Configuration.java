package app.config;

import javafx.scene.input.KeyCode;

public interface Configuration {
    double WINDOW_WIDTH = 640;
    double WINDOW_HEIGHT = 640;
    double MOVE_INC = 1.0;
    double ANGLE_INC = 1.0;

    

    final KeyCode LEFT_MOVE = KeyCode.A;
    final KeyCode RIGHT_MOVE = KeyCode.D;
    final KeyCode FORWARD_MOVE = KeyCode.W;
    final KeyCode BACKWARD_MOVE = KeyCode.S;
    final KeyCode LEFT_ROTATION = KeyCode.LEFT;
    final KeyCode RIGHT_ROTATION = KeyCode.RIGHT;
    final KeyCode UP_ROTATION = KeyCode.UP;
    final KeyCode DOWN_ROTATION = KeyCode.DOWN;
    final KeyCode RIGHT_TILT = KeyCode.Q;
    final KeyCode LEFT_TILT = KeyCode.E;
    final KeyCode ZOOM_IN = KeyCode.Z;
    final KeyCode ZOOM_OUT = KeyCode.X;

    double HALF_WINDOW_WIDTH = WINDOW_WIDTH / 2;
    double HALF_WINDOW_HEIGHT = WINDOW_HEIGHT / 2;
    double ASPECT_RATIO = WINDOW_WIDTH/WINDOW_HEIGHT;
}
