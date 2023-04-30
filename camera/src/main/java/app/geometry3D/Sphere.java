package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Polygon;
import app.geometry2D.Triangle;

public class Sphere extends Figure {

    public Sphere(Point center, double r, int factor, View view) {
        this.view = view;

        double alphaINC = Math.PI / factor;
        double betaINC = 2 * Math.PI / factor;

        Point[][] map = new Point[factor - 1][factor];

        for (int i = 1; i < factor; i++) {
            for (int j = 0; j < factor; j++) {
                double x = r * Math.cos(i * alphaINC - Math.PI / 2) * Math.cos(j * betaINC) + center.getX();
                double y = r * Math.cos(i * alphaINC - Math.PI / 2) * Math.sin(j * betaINC) + center.getY();
                double z = r * Math.sin(i * alphaINC - Math.PI / 2) + center.getZ();

                map[i - 1][j] = new Point(x, y, z);
            }
        }
        List<Polygon> sides = new ArrayList<>();

        for (int i = 0; i < factor - 2; i++) {
            for (int j = 0; j < factor; j++) {
                int rightIndex = j + 1 >= map[i].length ? 0 : j + 1;
                int upperIndex = i + 1 >= map.length ? 0 : i + 1;

                Point A = map[i][j];
                Point B = map[i][rightIndex];
                Point C = map[upperIndex][j];
                Point D = map[upperIndex][rightIndex];

                sides.add(new Polygon(4).add(A).add(B).add(C).add(D));
            }
        }

        // POSITIVE POLE
        double x = r * Math.cos(Math.PI / 2) * Math.cos(0) + center.getX();
        double y = r * Math.cos(Math.PI / 2) * Math.sin(0) + center.getY();
        double z = r * Math.sin(Math.PI / 2) + center.getZ();

        Point positivePole = new Point(x, y, z);
        for (int i = 0; i < factor - 1; i++) {
            int rightIndex = i + 1 >= map[factor - 2].length ? 0 : i + 1;

            Point A = map[factor - 2][i];
            Point B = map[factor - 2][rightIndex];
            Point C = positivePole;

            sides.add(new Triangle().add(A).add(B).add(C));
        }

        // NEGATIVE POLE
        x = r * Math.cos(-Math.PI / 2) * Math.cos(0) + center.getX();
        y = r * Math.cos(-Math.PI / 2) * Math.sin(0) + center.getY();
        z = r * Math.sin(-Math.PI / 2) + center.getZ();

        Point negativePole = new Point(x, y, z);
        for (int i = 0; i < factor - 1; i++) {
            int rightIndex = i + 1 >= map[0].length ? 0 : i + 1;

            Point A = map[0][i];
            Point B = map[0][rightIndex];
            Point C = negativePole;

            sides.add(new Triangle().add(A).add(B).add(C));
        }

        this.setSides(sides);
        this.setReference(center);
    }
}
