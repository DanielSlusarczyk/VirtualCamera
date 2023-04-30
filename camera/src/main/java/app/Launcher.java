package app;

import app.control.Controller;
import app.geometry.Point;
import app.geometry.Triangle;

public class Launcher {
    public static void main(String[] args) {
        Controller.run(args);

        //Triangle t = new Triangle();
        //t.add(
        //    new Point(1, 1, 3)
        //).add(
        //    new Point(5, 10, 6)
        //).add(
        //    new Point(-2, 4, 2)
        //);
//
        //t.norm(new Point(0, 0, 10));
//
        //System.out.println(t.getNormalVector());
//
        //System.out.println(t.isVisible());
    }
}
