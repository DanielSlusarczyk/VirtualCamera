package app.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.Getter;

public class Polygon {
    @Getter
    protected int nmbOfPoints;
    @Getter
    protected List<Edge> edges = new ArrayList<>();

    public Polygon(int nmbOfPoints) {
        this.nmbOfPoints = nmbOfPoints;
    }

    public Polygon add(Point point) {
        Point newPoint = new Point(point.getMatrix());

        if (edges.size() == 0) {

            // First point
            edges.add(new Edge(newPoint, null));

        } else if (edges.size() == nmbOfPoints - 1) {

            // Last point
            Point previous = new Point(point.getMatrix());
            edges.get(edges.size() - 1).setB(previous);
            
            Point start = new Point(edges.get(0).getA().getMatrix());
            edges.add(new Edge(newPoint, start));

        } else {

            // Middle point
            Point previous = new Point(point.getMatrix());
            edges.get(edges.size() - 1).setB(previous);

            edges.add(new Edge(newPoint, null));

        }

        return this;
    }

    public Point getPoint(int index){
        return edges.get(index).getA();
    }

    public Stream<Edge> getEdgeStream() {
        return edges.stream();
    }

    public Stream<Point> getPointsStream() {
        return edges.stream().flatMap(Edge::getStream);
    }

    public void introduce(){
        edges.forEach(edge -> System.out.println(edge));
    }
}