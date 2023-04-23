package app.geometry;

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
}