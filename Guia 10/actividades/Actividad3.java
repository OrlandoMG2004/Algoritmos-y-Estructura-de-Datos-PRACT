package actividades;

import actividades.graph.GraphLink;

// Actividad 3: prueba basica del TAD Graph (grafo no dirigido con listas enlazadas).
// Es practicamente el TestGraph del enunciado.
public class Actividad3 {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        System.out.println("=== Grafo no dirigido (Actividad 3) ===");
        System.out.print(g);
        // el (1) que sale al lado es el peso por defecto de cada arista
    }
}
