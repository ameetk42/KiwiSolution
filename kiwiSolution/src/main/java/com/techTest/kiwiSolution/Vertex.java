package com.techTest.kiwiSolution;


import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Ameet K
 */
public class Vertex {

    //All the connecting vertices
    HashMap<Vertex, Integer> connVertices;

    String name;


    public Vertex(String name) {
        this.connVertices = new HashMap<>();
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        Vertex other = (Vertex) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        if (this.name == null) return 0;
        return this.name.hashCode();
    }

    @Override
    public String toString() {

        StringJoiner sj = new StringJoiner(",");

        for (Vertex n : connVertices.keySet()){
            sj.add(n.name);
        }

        return "Vertex{" +
                "connVertices=" + sj.toString() +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Vertex, Integer> getConnecVertices() {
        return connVertices;
    }

    public void addConnecVertices(Vertex neighbour, int weight) {
        getConnecVertices().putIfAbsent(neighbour, weight);
    }
}
