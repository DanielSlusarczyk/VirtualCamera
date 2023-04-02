package app.objects;

import java.util.ArrayList;
import java.util.List;

import app.configuration.Configuration;
import app.geometry.Edge;
import app.geometry.Point;
import app.movement.Movement;
import app.movement.View;
import lombok.Getter;

public class Figure implements Configuration{
    @Getter
    private List<Point> vertices = new ArrayList<>();
    @Getter
    private List<Edge> edges = new ArrayList<>();
    private View view = new View();

    public void addVertex(Point A){
        vertices.add(A);
    }

    public void addEdge(Edge L){
        edges.add(L);
    }

    private Figure center(){
        Figure centered = new Figure();
        for(Edge line : this.getEdges()){
            Point A = new Point(line.getA().getX() + HALF_WINDOW_WIDTH, line.getA().getY() + HALF_WINDOW_HEIGHT, line.getA().getZ());
            Point B = new Point(line.getB().getX() + HALF_WINDOW_WIDTH, line.getB().getY() + HALF_WINDOW_HEIGHT, line.getB().getZ());

            centered.addEdge(new Edge(A, B));
        }
        return centered;
    }

    public Figure project(){
        Figure projected = new Figure();
        for(Edge line : this.getEdges()) {
            projected.addEdge(new Edge(view.projectPoint(line.getA()), view.projectPoint(line.getB())));
        }
        return projected.center();
    }

    public Figure rotateOX(double angle){
        for(Edge line : this.getEdges()) {
            Movement.rotatePointOX(line.getA(), angle);
            Movement.rotatePointOX(line.getB(), angle);
        }
        return this;
    }

    public Figure rotateOY(double angle){
        for(Edge line : this.getEdges()) {
            Movement.rotatePointOY(line.getA(), angle);
            Movement.rotatePointOY(line.getB(), angle);
        }
        return this;
    }
}
