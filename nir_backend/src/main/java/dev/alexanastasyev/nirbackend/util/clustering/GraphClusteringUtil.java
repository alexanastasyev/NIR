package dev.alexanastasyev.nirbackend.util.clustering;

import dev.alexanastasyev.nirbackend.util.graph.Edge;
import dev.alexanastasyev.nirbackend.util.graph.Graph;
import dev.alexanastasyev.nirbackend.util.graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class GraphClusteringUtil<T> {

    private final Graph<T> graph;

    public GraphClusteringUtil(Graph<T> graph) {
        this.graph = graph;
    }

    public void removeLongEdges(double level) {
        List<Edge<T>> edgesToRemove = new ArrayList<>();

        graph.getEdges().forEach(edge -> {
            if (edge.getDistance() > level) {
                edgesToRemove.add(edge);
            }
        });

        edgesToRemove.forEach(graph::removeEdge);
    }

    public List<Set<T>> getClustersFromGraph() {
        List<Set<Vertex<T>>> clusters = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        graph.getVertices().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                visited.add(vertex);

                AtomicReference<Set<Vertex<T>>> clusterToAdd = new AtomicReference<>(null);
                clusters.forEach(cluster -> cluster.forEach(clusterVertex -> {
                    if (areConnected(clusterVertex, vertex)) {
                        clusterToAdd.set(cluster);
                    }
                }));

                if (clusterToAdd.get() != null) {
                    clusterToAdd.get().add(vertex);
                } else {
                    Set<Vertex<T>> newCluster = new HashSet<>();
                    newCluster.add(vertex);
                    clusters.add(newCluster);
                }
            }
        });

        List<Set<T>> result = new ArrayList<>();
        clusters.forEach(cluster -> result.add(cluster.stream()
                .map(Vertex::getValue)
                .collect(Collectors.toSet())
        ));
        return result;
    }

    private boolean areConnected(Vertex<T> vertex1, Vertex<T> vertex2) {
        return graph.getEdges().parallelStream().anyMatch(edge ->
                (edge.getVertexFrom().getValue().equals(vertex1.getValue())
                        && edge.getVertexTo().getValue().equals(vertex2.getValue()))
                        || (edge.getVertexTo().getValue().equals(vertex1.getValue())
                        && edge.getVertexFrom().getValue().equals(vertex2.getValue()))
        );
    }

}
