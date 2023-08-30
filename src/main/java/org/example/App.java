package org.example;

import org.example.model.Graph;
import org.example.model.Vertex;

import java.io.IOException;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws IOException {
        DataReader reader = new DataReader();
        Graph graph = reader.readData();

        // Find paths
        String letter = "a";
        List<List<Vertex>> longests = graph.getLongestPath(letter);
        System.out.printf("Longest paths for letter %s, with max size %d%n", letter, longests.get(0).size());
        longests.forEach(System.out::println);
    }
}
