package dev.alexanastasyev.nirbackend.util.graph;

public class Edge<T> {

    private final Vertex<T> vertexFrom;
    private final Vertex<T> vertexTo;
    private final double distance;

    public Edge(Vertex<T> vertexFrom, Vertex<T> vertexTo, double distance) {
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;
        this.distance = distance;
    }

    public Vertex<T> getVertexFrom() {
        return vertexFrom;
    }

    public Vertex<T> getVertexTo() {
        return vertexTo;
    }

    public double getDistance() {
        return distance;
    }
}
