package dev.alexanastasyev.nirbackend.util.graph;

public class Vertex<T> {

    private final int index;
    private T value;

    public Vertex(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
