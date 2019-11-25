package com.techTest.kiwiSolution;


import java.util.HashMap;


public class Graph {

    // All the connecting vertices from one vertex.
    private HashMap<String, Vertex> pathList;

    public Graph() {
        pathList = new HashMap<>();
    }

    public Graph(HashMap<String, Vertex> routeTable) {
        this.pathList = routeTable;
    }

    public Graph(FileParser fileParser) throws Exception {
        this(fileParser.getPathList());
    }


    public HashMap<String, Vertex> getPathList() {
        return pathList;
    }

    // Checks if the Vertex exists in the Graph.
    
    public boolean contains(Vertex vertex) {
        return getPathList().containsKey(vertex.name);
    }
}
