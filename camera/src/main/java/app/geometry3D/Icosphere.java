package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Side;
import app.geometry2D.Triangle;

public class Icosphere extends Figure {
    private final double phi = (1.0 + Math.sqrt(5.0) * 0.5);
    private final List<Side> mesh = new ArrayList<>();
    
    public Icosphere(double size, int denisty, View view){
        this.view = view;

        double a = size;
        double b = size / phi;
        double scalar = Math.sqrt(a * a + b *b);

        Point V1 = new Point(0, b, -a);
        Point V2 = new Point(b, a, 0);
        Point V3 = new Point(-b, a, 0);
        Point V4 = new Point(0, b, a);
        Point V5 = new Point(0, -b, a);
        Point V6 = new Point(-a, 0, b);
        Point V7 = new Point(0, -b, -a);
        Point V8 = new Point(a, 0, -b);
        Point V9 = new Point(a, 0, b);
        Point V10 = new Point(-a, 0, -b);
        Point V11 = new Point(b, -a, 0);
        Point V12 = new Point(-b, -a, 0);

        List<Triangle> sides = new ArrayList<>(){{
            add(new Triangle().add(V3).add(V2).add(V1));
            add(new Triangle().add(V2).add(V3).add(V4));
            add(new Triangle().add(V6).add(V5).add(V4));
            add(new Triangle().add(V5).add(V9).add(V4));
            add(new Triangle().add(V8).add(V7).add(V1));
            add(new Triangle().add(V7).add(V10).add(V1));
            add(new Triangle().add(V12).add(V11).add(V5));
            add(new Triangle().add(V11).add(V12).add(V7));
            add(new Triangle().add(V10).add(V6).add(V3));
            add(new Triangle().add(V6).add(V10).add(V12));
            add(new Triangle().add(V9).add(V8).add(V2));
            add(new Triangle().add(V8).add(V9).add(V11));
            add(new Triangle().add(V3).add(V6).add(V4));
            add(new Triangle().add(V9).add(V2).add(V4));
            add(new Triangle().add(V10).add(V3).add(V1));
            add(new Triangle().add(V2).add(V8).add(V1));
            add(new Triangle().add(V12).add(V10).add(V7));
            add(new Triangle().add(V8).add(V11).add(V7));
            add(new Triangle().add(V6).add(V12).add(V5));
            add(new Triangle().add(V11).add(V9).add(V5));
        }};

        sides.forEach(s -> split(s, denisty, scalar));

        this.setSides(mesh);

        this.setReference(new Point(0, 0, 0));
    }

    //        V3
    //        /\
    //       /  \
    //      /    \
    //    B/______\C
    //    / \    / \
    //   /   \  /   \
    //  /_____\/_____\
    // V1      A      V2
    private void split(Triangle t, int deepth, double radius){
        if(deepth == 0){
            mesh.add(t);
        } else {
            Point A = scale(t.getEdge(0).getCenter(), radius);
            Point B = scale(t.getEdge(1).getCenter(), radius);
            Point C = scale(t.getEdge(2).getCenter(), radius);
                
            Triangle t1 = new Triangle().add(t.getPoint(1)).add(A).add(B);
            Triangle t2 = new Triangle().add(t.getPoint(0)).add(C).add(A);
            Triangle t3 = new Triangle().add(t.getPoint(2)).add(B).add(C);
            Triangle t4 = new Triangle().add(A).add(C).add(B);

            split(t1, deepth - 1, radius);
            split(t2, deepth - 1, radius);
            split(t3, deepth - 1, radius);
            split(t4, deepth - 1, radius);
        }
    }

    private Point scale(Point p, double radius){
        double length = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY() + p.getZ() * p.getZ());

        p.setX(p.getX() * radius/length);
        p.setY(p.getY() * radius/length);
        p.setZ(p.getZ() * radius/length);

        return p;
    }
}
