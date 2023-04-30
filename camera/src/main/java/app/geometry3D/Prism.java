package app.geometry3D;

import java.util.ArrayList;
import java.util.List;

import app.control.View;
import app.geometry2D.Point;
import app.geometry2D.Polygon;

public class Prism extends Figure {

    //     H__________ G(Y)
    //     /|        /| 
    //    / |       / |
    // E /__|_____F/  |
    //  |  D|_____|___| C
    //  |  /      |  /
    //  | /       | /
    //  |/________|/
    // A(X)        B
    
    public Prism(Point X, Point Y, View view){
        this.view = view;
        
        Point A = new Point(X.getX(), X.getY(), X.getZ());
        Point B = new Point(Y.getX(), X.getY(), X.getZ());
        Point C = new Point(Y.getX(), Y.getY(), X.getZ());
        Point D = new Point(X.getX(), Y.getY(), X.getZ());
        Point E = new Point(X.getX(), X.getY(), Y.getZ());
        Point F = new Point(Y.getX(), X.getY(), Y.getZ());
        Point G = new Point(Y.getX(), Y.getY(), Y.getZ());
        Point H = new Point(X.getX(), Y.getY(), Y.getZ()); 

        List<Polygon> sides = new ArrayList<>(){{
            add(new Polygon(4).add(A).add(B).add(C).add(D));
            add(new Polygon(4).add(A).add(B).add(F).add(E));
            add(new Polygon(4).add(B).add(C).add(G).add(F));
            add(new Polygon(4).add(E).add(F).add(G).add(H));
            add(new Polygon(4).add(D).add(C).add(G).add(H));
            add(new Polygon(4).add(A).add(D).add(H).add(E));
        }};
        
        this.setSides(sides);
        
        double x = (Y.getX() + X.getX())/2;
        double y = (Y.getY() + X.getY())/2;
        double z = (Y.getZ() + X.getZ())/2;

        this.setReference(new Point(x, y, z));
    }
}