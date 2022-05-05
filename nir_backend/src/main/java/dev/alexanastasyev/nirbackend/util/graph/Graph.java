package dev.alexanastasyev.nirbackend.util.graph;

import dev.alexanastasyev.nirbackend.exception.VertexAlreadyExistsException;
import dev.alexanastasyev.nirbackend.exception.VertexNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph<T> {

    private final List<Vertex<T>> vertices;
    private final List<Edge<T>> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Graph(List<Vertex<T>> vertices, List<Edge<T>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void addVertex(Vertex<T> vertex) {
        if (!vertexExists(vertex)) {
            this.vertices.add(vertex);
        } else {
            throw new VertexAlreadyExistsException();
        }
    }

    public Vertex<T> addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(this.vertices.size(), value);
        this.vertices.add(vertex);
        return vertex;
    }

    public void addEdge(Edge<T> edge) {
        if (vertexExists(edge.getVertexFrom()) && vertexExists(edge.getVertexTo())) {
            this.edges.add(edge);
        } else {
            throw new VertexNotFoundException();
        }
    }

    public Edge<T> addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo, double distance) {
        if (vertexExists(vertexFrom) && vertexExists(vertexTo)) {
            Edge<T> edge = new Edge<>(vertexFrom, vertexTo, distance);
            this.edges.add(edge);
            return edge;
        } else {
            throw new VertexNotFoundException();
        }
    }

    public Edge<T> addEdge(int indexFrom, int indexTo, double distance) {
        Vertex<T> vertexFrom = findVertexByIndex(indexFrom).orElseThrow(VertexNotFoundException::new);
        Vertex<T> vertexTo = findVertexByIndex(indexTo).orElseThrow(VertexNotFoundException::new);
        return addEdge(vertexFrom, vertexTo, distance);
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public boolean removeEdge(Edge<T> edge) {
        return this.edges.remove(edge);
    }

    private boolean vertexExists(Vertex<T> vertex) {
        return this.vertices.parallelStream().anyMatch(existingVertex -> existingVertex.getValue().equals(vertex.getValue()));
    }

    private Optional<Vertex<T>> findVertexByIndex(int index) {
        return this.vertices.parallelStream()
                .filter(vertex -> vertex.getIndex() == index)
                .findAny();
    }
}
