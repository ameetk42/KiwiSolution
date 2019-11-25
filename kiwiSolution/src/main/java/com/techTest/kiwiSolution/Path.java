package com.techTest.kiwiSolution;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Ameet K
 */
public class Path implements Iterable<Vertex> {

    LinkedList<Vertex> vertexs;
    int distance;

    public Path() {
        vertexs = new LinkedList<>();
        distance = 0;
    }

    public int getDistance() {
        return distance;
    }


    public boolean addVertex(Vertex vertex) {
        if (!vertexs.isEmpty()) {

            //checks if next vertex is valid 
            if (!vertexs.getLast().getConnecVertices().containsKey(vertex)) {
                return false;
            }

            distance += vertexs.getLast().getConnecVertices().get(vertex);
        }

        vertexs.add(vertex);
        return true;
    }

    public Iterable<Vertex> getVertices() {
        return vertexs;
    }

  
    private String getId(Iterable<Vertex> vertexs) {
        StringJoiner sj = new StringJoiner("-");

        for (Vertex n : vertexs) {
            sj.add(n.name);
        }

        return sj.toString();
    }
  
    public Vertex getLast()
    {
        return vertexs.getLast();
    }

    public int stops()
    {
        return vertexs.size();
    }

    
    
    @Override
    public Iterator<Vertex> iterator() {
        return vertexs.iterator();
    }
    @Override
    public int hashCode() {
        return getId(getVertices()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Path) || obj == null) {
            return false;
        }

        Path other = (Path) obj;

        return Objects.equals(this.getId(this.getVertices()), getId(other.getVertices()));
    }



}
