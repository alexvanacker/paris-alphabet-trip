package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataReaderTest {

    private final double[] coordinates = new double[]{2.4, 2.34};
    private final Geometry fakeGeometry = new Geometry("point", coordinates);

    @Test
    void intersectionsAlphabeticallyAreInGraph() {
        IntersectionProperty property = new IntersectionProperty("Rue Alexis / Rue Beatrice", "context", 75, 75);
        Intersection oneIntersection = new Intersection("type", fakeGeometry, property);

        DataReader reader = new DataReader();
        Intersection[] intersections = new Intersection[1];
        intersections[0] = oneIntersection;
        Graph graph = reader.buildGraph(intersections);

        assertEquals(2, graph.getAdjVertices().size());
        assertTrue(graph.getAdjVertices().containsKey(new Vertex("Rue Alexis")));
        assertEquals(1, graph.getNeighbors("Rue Alexis").size());
        assertEquals(0, graph.getNeighbors("Rue Beatrice").size());
    }

    @Test
    void shouldNotBeInGraph() {
        var property = new IntersectionProperty("Place Michel Debré / Rue du Dragon", "context", 75, 75);
        Intersection oneIntersection = new Intersection("type", fakeGeometry, property);

        DataReader reader = new DataReader();
        Intersection[] intersections = new Intersection[1];
        intersections[0] = oneIntersection;
        Graph graph = reader.buildGraph(intersections);

        assertEquals(0, graph.getAdjVertices().size());
    }
}