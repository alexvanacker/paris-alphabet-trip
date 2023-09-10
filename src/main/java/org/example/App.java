package org.example;

import org.example.model.Graph;
import org.example.model.Vertex;

import java.io.IOException;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws IOException {
        final DataReader reader = new DataReader();
        final Graph graph = reader.readData();

        // Find paths
        final String letter = "t";
        final int minSize = 0;
        final List<List<Vertex>> longests;
        if (minSize > 0) {
            longests = graph.getPaths(letter, minSize);
        } else {
            longests = graph.getLongestPath(letter);
        }
        System.out.printf("Longest paths for letter %s, with max size %d%n", letter, longests.get(0).size());
        longests.forEach(System.out::println);
    }
}
