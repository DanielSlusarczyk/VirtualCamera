package app.objects;

import app.geometry.Edge;
import app.geometry.Point;

public class Prism extends Figure {
    //     F__________ B
    //     /|        /| 
    //    / |       / |
    // G /__|_____H/  |
    //  |  E|_____|___| D
    //  |  /      |  /
    //  | /       | /
    //  |/________|/
    // A           C
    
    public Prism(Point A, Point B){
        new Point(A.getX(), A.getY(), A.getZ());
        new Point(B.getX(), B.getY(), B.getZ());
        Point C = new Point(B.getX(), A.getY(), A.getZ());
        Point D = new Point(B.getX(), B.getY(), A.getZ());
        Point E = new Point(A.getX(), B.getY(), A.getZ());
        
        Point F = new Point(A.getX(), B.getY(), B.getZ());
        Point G = new Point(A.getX(), A.getY(), B.getZ());
        Point H = new Point(B.getX(), A.getY(), B.getZ());

        addVertex(A);
        addVertex(B);
        addVertex(C);
        addVertex(D);
        addVertex(E);
        addVertex(F);
        addVertex(G);
        addVertex(H);

        addEdge(new Edge(new Point(A.getX(), A.getY(), A.getZ()), new Point(B.getX(), A.getY(), A.getZ())));
        addEdge(new Edge(new Point(B.getX(), A.getY(), A.getZ()), new Point(B.getX(), B.getY(), A.getZ())));
        addEdge(new Edge(new Point(B.getX(), B.getY(), A.getZ()), new Point(A.getX(), B.getY(), A.getZ())));
        addEdge(new Edge(new Point(A.getX(), B.getY(), A.getZ()), new Point(A.getX(), A.getY(), A.getZ())));
        addEdge(new Edge(new Point(A.getX(), A.getY(), B.getZ()), new Point(B.getX(), A.getY(), B.getZ())));
        addEdge(new Edge(new Point(B.getX(), A.getY(), B.getZ()), new Point(B.getX(), B.getY(), B.getZ())));
        addEdge(new Edge(new Point(B.getX(), B.getY(), B.getZ()), new Point(A.getX(), B.getY(), B.getZ())));
        addEdge(new Edge(new Point(A.getX(), B.getY(), B.getZ()), new Point(A.getX(), A.getY(), B.getZ())));
        addEdge(new Edge(new Point(A.getX(), A.getY(), A.getZ()), new Point(A.getX(), A.getY(), B.getZ())));
        addEdge(new Edge(new Point(B.getX(), A.getY(), A.getZ()), new Point(B.getX(), A.getY(), B.getZ())));
        addEdge(new Edge(new Point(B.getX(), B.getY(), A.getZ()), new Point(B.getX(), B.getY(), B.getZ())));
        addEdge(new Edge(new Point(A.getX(), B.getY(), A.getZ()), new Point(A.getX(), B.getY(), B.getZ())));
    }

    public Prism(){
        this(new Point(-10, -10, -10), new Point(10, 10, 10));
    }
}
