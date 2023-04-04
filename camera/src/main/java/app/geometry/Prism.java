package app.geometry;

public class Prism extends Figure {

    //     7__________ 6(Y)
    //     /|        /| 
    //    / |       / |
    // 4 /__|_____5/  |
    //  |  3|_____|___| 2
    //  |  /      |  /
    //  | /       | /
    //  |/________|/
    // 0(X)        1
    
    public Prism(Point X, Point Y){
        Point[] points = new Point[8];
        points[0] = new Point(X.getX(), X.getY(), X.getZ()); // 0
        points[1] = new Point(Y.getX(), X.getY(), X.getZ()); // 1
        points[2] = new Point(Y.getX(), Y.getY(), X.getZ()); // 2
        points[3] = new Point(X.getX(), Y.getY(), X.getZ()); // 3
        points[4] = new Point(X.getX(), X.getY(), Y.getZ()); // 4
        points[5] = new Point(Y.getX(), X.getY(), Y.getZ()); // 5
        points[6] = new Point(Y.getX(), Y.getY(), Y.getZ()); // 6
        points[7] = new Point(X.getX(), Y.getY(), Y.getZ()); // 7 

        boolean[][] edges = new boolean[points.length][points.length];
        edges[0][1] = edges[1][2] = edges[2][3] = edges[3][0] = true;
        edges[4][5] = edges[5][6] = edges[6][7] = edges[7][4] = true;
        edges[0][4] = edges[1][5] = edges[2][6] = edges[3][7] = true;

        this.setEdges(edges);
        this.setPoints(points);
    }
}
