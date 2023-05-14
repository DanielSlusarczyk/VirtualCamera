package app.geometry2D;

import java.util.stream.Stream;

import app.control.View;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private Point A;
    private Point B;

    public Stream<Point> getStream() {
        return Stream.of(A, B);
    }

    @Override
    public String toString() {
        return "[Edge][ " + A + " -> " + B + " ]";
    }

    public Point getReversedVector() {
        Point vector = getVector();

        vector.setMatrix(vector.getMatrix().negative());

        return vector;
    }

    public Point getVector() {
        Point vector = new Point(B.getMatrix().minus(A.getMatrix()));

        return vector;
    }

    public Line mapToLine(View view) {
        Point PA = view.projectPoint(A);
        Point PB = view.projectPoint(B);
        
        Line line = new Line(PA.getX(), PA.getY(), PB.getX(), PB.getY());
        line.setStroke(Color.WHITE);
        return line;
    }

    public Point getCenter() {
        return new Point(
                (A.getX() + B.getX()) / 2,
                (A.getY() + B.getY()) / 2,
                (A.getZ() + B.getZ()) / 2);
    }
}