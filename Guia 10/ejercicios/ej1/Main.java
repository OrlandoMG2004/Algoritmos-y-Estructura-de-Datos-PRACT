package ejercicios.ej1;

import actividades.graph.GraphLink;
import actividades.listlinked.Stack;

// Prueba de los metodos que le agregamos a GraphLink en el ej1:
// insertEdgeWeight, shortPath, isConexo y Dijkstra
public class Main {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();

        // los vertices
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        // y las aristas con su peso
        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 1);
        g.insertEdgeWeight("C", "B", 2);
        g.insertEdgeWeight("B", "D", 5);
        g.insertEdgeWeight("C", "D", 8);
        g.insertEdgeWeight("D", "E", 3);
        g.insertEdgeWeight("B", "E", 10);

        System.out.println("=== Grafo (lista de adyacencia, formato destino(peso)) ===");
        System.out.print(g);

        System.out.println("=== isConexo() ===");
        System.out.println("Conexo? " + g.isConexo());

        System.out.println("\n=== shortPath(A, E)  [ruta mas corta por numero de aristas, BFS] ===");
        System.out.println("Ruta: " + g.shortPath("A", "E"));

        System.out.println("\n=== Dijkstra(A, E)  [ruta mas corta por peso] ===");
        Stack<String> ruta = g.Dijkstra("A", "E");
        System.out.println("Pila (cima = inicio): " + ruta);
        System.out.print("Camino: ");
        StringBuilder camino = new StringBuilder();
        while (!ruta.isEmpty()) {
            camino.append(ruta.pop());
            if (!ruta.isEmpty()) camino.append(" -> ");
        }
        System.out.println(camino);
        System.out.println("Costo total: " + g.dijkstraCost("A", "E"));

        // si agregamos un vertice suelto (F) ya deja de ser conexo
        System.out.println("\n=== Se agrega el vertice aislado F ===");
        g.insertVertex("F");
        System.out.println("Conexo ahora? " + g.isConexo());
    }
}
