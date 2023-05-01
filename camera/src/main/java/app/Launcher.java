package app;

import app.control.Controller;
import app.geometry2D.Point;
import app.geometry2D.Triangle;

public class Launcher {
    public static void main(String[] args) {
        Controller.run(args);

        Triangle t = new Triangle();
        t.add(
            new Point(5.352331346596346, -29.999999999999996, 133.52721792907337)
        ).add(
            new Point(4.592887607086383, -33.38261212717716, 135.864545423574)
        ).add(
            new Point(11.589682071129124, -29.999999999999996, 137.12835404891246)
        );

        t.norm(new Point(0, -20, 150, 0));

        System.out.println(t.getNormalVector());

        System.out.println(t.isVisible());
    }
}
