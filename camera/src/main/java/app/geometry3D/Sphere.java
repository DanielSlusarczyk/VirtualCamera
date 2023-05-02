package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Side;
import app.geometry2D.Triangle;

public class Sphere extends Figure {
    
    //        , - ~ ~ ~ - ,
    //    , '          /    ' ,
    //  ,             /         ,
    // ,             / r         ,
    //,             /             ,
    //,            .              ,
    //,          center           ,
    // ,                         ,
    //  ,                       ,
    //    ,                  , '
    //      ' - , _ _ _ ,  '
    //
    // denisty - number of parallels and meridians

    public Sphere(Point center, double r, int density, View view) {
        this.view = view;

        double alphaINC = Math.PI / density;
        double betaINC = 2 * Math.PI / density;

        Point[][] map = new Point[density - 1][density];

        // Polar -> Cartesian coordinate system
        for (int i = 1; i < density; i++) {
            for (int j = 0; j < density; j++) {
                double x = r * Math.cos(i * alphaINC - Math.PI / 2) * Math.cos(j * betaINC) + center.getX();
                double y = r * Math.cos(i * alphaINC - Math.PI / 2) * Math.sin(j * betaINC) + center.getY();
                double z = r * Math.sin(i * alphaINC - Math.PI / 2) + center.getZ();

                map[i - 1][j] = new Point(x, y, z);
            }
        }
        List<Side> sides = new ArrayList<>();

        // Trapezoid defined by two pararrels and two meridians
        for (int i = 0; i < density - 2; i++) {
            for (int j = 0; j < density; j++) {
                int rightIndex = (j + 1) % density;

                Point A = map[i][j];
                Point B = map[i][rightIndex];
                Point C = map[i + 1][rightIndex];
                Point D = map[i + 1][j];

                sides.add(new Side(4).add(A).add(B).add(C).add(D));
            }
        }

        // Upper pole
        double x = r * Math.cos(Math.PI / 2) * Math.cos(0) + center.getX();
        double y = r * Math.cos(Math.PI / 2) * Math.sin(0) + center.getY();
        double z = r * Math.sin(Math.PI / 2) + center.getZ();

        Point positivePole = new Point(x, y, z);
        for (int i = 0; i < density; i++) {
            int rightIndex = (i + 1) % density;

            Point A = map[density - 2][i];
            Point B = map[density - 2][rightIndex];
            Point C = positivePole;

            sides.add(new Triangle().add(A).add(B).add(C));
        }

        // Bottom pole
        x = r * Math.cos(-Math.PI / 2) * Math.cos(0) + center.getX();
        y = r * Math.cos(-Math.PI / 2) * Math.sin(0) + center.getY();
        z = r * Math.sin(-Math.PI / 2) + center.getZ();

        Point negativePole = new Point(x, y, z);
        for (int i = 0; i < density; i++) {
            int rightIndex = (i + 1) % density;

            Point A = map[0][i];
            Point B = map[0][rightIndex];
            Point C = negativePole;

            sides.add(new Triangle().add(A).add(B).add(C));
        }

        this.setSides(sides);
        this.setReference(center);
    }
}
