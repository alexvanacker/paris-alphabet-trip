package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.example.model.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {

    private final static String DATA_PATH = "intersections-75.geojson";

    //TODO move to another static class somewhere
    private final ObjectMapper mapper = new ObjectMapper(); // create once, reuse


    public DataReader() {
    }

    public Graph readData() throws IOException {

        FeatureCollection data = mapper.readValue(getClass().getClassLoader().getResourceAsStream(DATA_PATH),
                FeatureCollection.class);

        return buildGraph(data.features());
    }

    public Graph buildGraph(Intersection[] intersections) {
        Graph graph = new Graph();
        for (Intersection i: intersections) {
            // Split name, remove "Rue" and so forth
            String name = i.properties().name();
            List<StreetName> names = Arrays.stream(name.split("/"))
                    .map(String::trim)
                    .map(StreetName::new)
                    .toList();

            final StreetName first = names.get(0);
            final StreetName second = names.get(1);
            // Add to graph if next
            if(first.isBeforeInAlphabetFuzzy(second)) {
                graph.addDirectedEdge(first.getFullName(), second.getFullName());
            }

        }
        return graph;
    }

}
