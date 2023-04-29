package app.transform;

import java.util.ArrayList;
import java.util.List;

import app.geometry.Edge;
import app.geometry.Polygon;

public class Triangulator {
    public static List<Polygon>triangule(Polygon input){
        List<Edge> edges = input.getEdges();

        if(edges.size() != 4){
            System.out.println("Only 4 edges");
        }

        List<Polygon> tringulation = new ArrayList<>();

        tringulation.add(
            new Polygon(3)
                .add(edges.get(0).getA())
                .add(edges.get(1).getA())
                .add(edges.get(2).getA()));

        tringulation.add(
            new Polygon(3)
                .add(edges.get(2).getA())
                .add(edges.get(3).getA())
                .add(edges.get(0).getA()));


        return tringulation;
    }
}
