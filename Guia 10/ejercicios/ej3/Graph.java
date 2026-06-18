package ejercicios.ej3;

import actividades.listlinked.ListLinked;

// TAD Graph: la interfaz con las operaciones que debe tener el grafo.
// E es el tipo de dato que guardan los vertices.
public interface Graph<E> {
    void insertVertex(E v);
    void insertEdge(E v, E z);
    void removeVertex(E v);
    void removeEdge(E v, E z);
    boolean searchVertex(E v);
    boolean searchEdge(E v, E z);
    ListLinked<E> adjacentVertices(E v);
}
