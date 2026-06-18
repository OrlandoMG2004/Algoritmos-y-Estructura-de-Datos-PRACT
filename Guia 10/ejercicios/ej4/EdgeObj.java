package ejercicios.ej4;

import actividades.graph.Vertex;

// arista para la representacion por lista de aristas: guarda origen, destino y peso
public class EdgeObj<E> {
    private Vertex<E> origin;
    private Vertex<E> destination;
    private int weight;

    public EdgeObj(Vertex<E> origin, Vertex<E> destination) {
        this(origin, destination, 1);
    }

    public EdgeObj(Vertex<E> origin, Vertex<E> destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getOrigin() { return origin; }
    public Vertex<E> getDestination() { return destination; }
    public int getWeight() { return weight; }

    @Override
    public String toString() {
        return "(" + origin + " -> " + destination + ")";
    }
}
