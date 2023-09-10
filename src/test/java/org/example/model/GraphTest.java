package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void getStartsNominal() {
        Graph graph = new Graph();
        graph.addVertex("Alexandre");
        graph.addVertex("Babel");
        graph.addVertex("Amanda");

        List<Vertex> result = graph.getStarts("a");
        List<Vertex> expected = new ArrayList<>();
        expected.add(new Vertex("Amanda"));
        expected.add(new Vertex("Alexandre"));
        assertEquals(expected, result);
    }

    @Test
    void getLongestNominal() {
        Graph graph = new Graph();
        graph.addVertex("Alexandre");
        graph.addVertex("Babel");
        graph.addVertex("Crazy");
        graph.addVertex("Babylone");
        graph.addDirectedEdge("Alexandre", "Babel");
        graph.addDirectedEdge("Alexandre", "Babylone");
        graph.addDirectedEdge("Babel", "Crazy");

        List<List<Vertex>> result = graph.getLongestPath("a");
        assertEquals(1, result.size());
        List<Vertex> longestPath = result.get(0);
        List<Vertex> expectedPath = List.of(new Vertex("Alexandre"),
                new Vertex("Babel"),
                new Vertex("Crazy"));
        assertEquals(expectedPath, longestPath);
    }

    @Test
    void getPathsNominal() {
        Graph graph = new Graph();
        graph.addVertex("Alexandre");
        graph.addVertex("Babel");
        graph.addVertex("Crazy");
        graph.addVertex("Amanda");
        graph.addVertex("Babylone");
        graph.addDirectedEdge("Alexandre", "Babel");
        graph.addDirectedEdge("Amanda", "Babylone");
        graph.addDirectedEdge("Babel", "Crazy");

        List<List<Vertex>> result = graph.getPaths("a", 2);
        assertEquals(2, result.size());
        List<Vertex> expectedPath1 = List.of(new Vertex("Alexandre"),
                new Vertex("Babel"),
                new Vertex("Crazy"));
        List<Vertex> expectedPath2 = List.of(new Vertex("Amanda"),
                new Vertex("Babylone"));
        List<List<Vertex>> expected = List.of(expectedPath1, expectedPath2);
        assertThat(result).hasSameElementsAs(expected);
    }

    // FIXME this does not work at the moment since we only return the longest path
    // for a given starting Vertex
    @Test
    void getPathsWithMinSizeSameStart() {
        Graph graph = new Graph();
        graph.addVertex("Alexandre");
        graph.addVertex("Babel");
        graph.addVertex("Crazy");
        graph.addVertex("Amanda");
        graph.addVertex("Babylone");
        graph.addDirectedEdge("Alexandre", "Babel");
        graph.addDirectedEdge("Alexandre", "Babylone");
        graph.addDirectedEdge("Babel", "Crazy");

        List<List<Vertex>> result = graph.getPaths("a", 2);
        assertEquals(2, result.size());
        List<Vertex> expectedPath1 = List.of(new Vertex("Alexandre"),
                new Vertex("Babel"),
                new Vertex("Crazy"));
        List<Vertex> expectedPath2 = List.of(new Vertex("Alexandre"),
                new Vertex("Babylone"));
        List<List<Vertex>> expected = List.of(expectedPath1, expectedPath2);
        assertThat(result).hasSameElementsAs(expected);
    }

}