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
        List<Vertex> longest = graph.getLongestPath("a");
        System.out.println("longest = " + longest);
    }
}
