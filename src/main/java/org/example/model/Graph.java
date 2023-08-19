package org.example.model;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void addDirectedEdge(String start, String end) {
        Vertex v1 = new Vertex(start);
        Vertex v2 = new Vertex(end);
        addVertex(start);
        addVertex(end);
        adjVertices.get(v1).add(v2);
    }

    public Map<Vertex, List<Vertex>> getAdjVertices() {
        return adjVertices;
    }

    public List<Vertex> getNeighbors(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public List<Vertex> getStarts(String letter) {
        return adjVertices.keySet().stream().map(Vertex::getLabel)
                .map(StreetName::new)
                .filter(sn -> sn.getCleanedNames().stream().anyMatch(s -> s.toLowerCase().startsWith(letter)))
                .map(StreetName::getFullName)
                .map(Vertex::new)
                .collect(Collectors.toList());
    }

    public List<Vertex> getLongestPath(String letter) {
        List<Vertex> longest = new ArrayList<>();
        for(Vertex v: getStarts(letter)) {
            List<Vertex> longestForStart = getLongestPathFromVertex(v, letter.charAt(0));
            if (longestForStart.size() > longest.size()) {
                longest = longestForStart;
            }
            System.out.println(" ============================= ");
        }
        return longest;
    }

    private List<Vertex> getLongestPathFromVertex(Vertex start, char letter) {
        Set<Vertex> visited = new HashSet<>();
        List<Vertex> path = new ArrayList<>();
        return visitVertex(start, letter, visited, 0, path);
    }

    private List<Vertex> visitVertex(Vertex v, char currentLetter, Set<Vertex> visited, int nbTabs, List<Vertex> path) {
        System.out.println("  ".repeat(nbTabs) + v);
        visited.add(v);
        List<Vertex> newPath = new ArrayList<>(path);
        newPath.add(v);
        int prevIndex = Alphabet.letters.indexOf(currentLetter) - 1;
        if (prevIndex < 0) {
            prevIndex = 25;
        }
        char previousLetter = Alphabet.letters.get(prevIndex);
        var currentStreetName = new StreetName(v.getLabel());
        char nextLetter =  Alphabet.letters.get((Alphabet.letters.indexOf(currentLetter) + 1) % 26);
        return adjVertices.get(v).stream().filter(neighbor -> !visited.contains(neighbor))
                .filter(neighbor -> neighborCanBeVisited(currentStreetName, neighbor, currentLetter, previousLetter))
                .map(neighbor -> visitVertex(neighbor, nextLetter, visited, nbTabs + 1, newPath))
                .max(Comparator.comparing(List::size))
                .orElse(newPath);
    }

    // Only those which have a name with the next letter OR the same
    // TODO If the current street name has multiple consecutive letters, then we should check the next ones (i.e. if Rue Blabla Coucou, check "D" as well as "C")
    private boolean neighborCanBeVisited(StreetName currentStreetName, Vertex neighbor, char currentLetter, char previousLetter) {
        return currentStreetName.isBeforeInAlphabet(new StreetName(neighbor.getLabel()), currentLetter)
                || currentStreetName.isBeforeInAlphabet(new StreetName(neighbor.getLabel()), previousLetter);
    }
}
