package app.geometry2D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import app.config.Configuration;
import app.control.View;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

public class Side implements Configuration {
    @Getter
    protected int nmbOfPoints;
    @Getter
    protected List<Edge> edges = new ArrayList<>();
    @Setter
    protected double K_a = init_K_a;
    @Setter
    protected double K_s = init_K_s;
    @Setter
    protected double K_d = init_K_d;
    @Setter
    protected double alpha = init_alpha;
    @Setter @Getter
    protected Point RGB;

    public Side(int nmbOfPoints, Point RGB) {
        this.nmbOfPoints = nmbOfPoints;
        this.RGB = RGB;
    }

    public Side add(Point point) {
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

    public Point getPoint(int index) {
        return edges.get(index).getA();
    }

    public Edge getEdge(int index) {
        return edges.get(index);
    }

    public Stream<Edge> getEdgeStream() {
        return edges.stream();
    }

    public Stream<Point> getPointsStream() {
        return edges.stream().flatMap(Edge::getStream);
    }

    public void introduce() {
        edges.forEach(edge -> System.out.println(edge));
    }

    public Polygon mapToPolygon(View view) {
        double[] coords = new double[edges.size() * 2];

        for (int i = 0; i < edges.size(); i++) {
            Point P = view.projectPoint(edges.get(i).getA());

            coords[2 * i] = P.getX();
            coords[2 * i + 1] = P.getY();
        }

        return new Polygon(coords);
    }
}