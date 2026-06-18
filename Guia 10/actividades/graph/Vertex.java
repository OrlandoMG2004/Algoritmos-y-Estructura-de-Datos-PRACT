package actividades.graph;

// un vertice del grafo (guarda su dato)
public class Vertex<E> {
    private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() { return data; }
    public void setData(E data) { this.data = data; }

    @Override
    public String toString() {
        return data.toString();
    }
}
