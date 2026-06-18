package ejercicios.ej4;

// Interfaz del grafo dirigido del ej4.
// La implementan tanto GraphLink como GraphListEdge.
public interface Graph<E> {
    void insertVertex(E v);
    void insertEdge(E v, E z);      // arista dirigida v -> z
    boolean searchVertex(E v);
    boolean searchEdge(E v, E z);
    int numVertices();
    boolean isConexo();             // conexo (debilmente, grafo subyacente)
}
