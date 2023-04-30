package app;

import app.control.Controller;
import app.geometry2D.Point;
import app.geometry2D.Triangle;

public class Launcher {
    public static void main(String[] args) {
        Controller.run(args);

        //Triangle t = new Triangle();
        //t.add(
        //    new Point(9.51, -36.18, 143.09)
        //).add(
        //    new Point(11.75, -36.18, 150)
        //).add(
        //    new Point(5.00, -39.02, 146.37)
        //);
//
        //t.norm(new Point(0, -20, 150));
//
        //System.out.println(t.getNormalVector());
//
        //System.out.println(t.isVisible());
    }
}
