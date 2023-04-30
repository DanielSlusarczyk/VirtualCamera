package app.geometry2D;

import java.util.stream.Stream;

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
    public String toString(){
        return "[Edge][ " + A + " -> " + B + " ]";
    }

    public Point getReversedVector(){
        Point vector = getVector();

        vector.setMatrix(vector.getMatrix().negative());

        return vector;
    }

    public Point getVector(){
        Point vector = new Point (B.getMatrix().minus(A.getMatrix()));

        return vector;
    }
}