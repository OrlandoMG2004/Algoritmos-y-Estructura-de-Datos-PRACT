package ejercicios.ej3;

// Prueba del TAD Graph (interfaz) con la implementacion GraphLink
public class Main {
    public static void main(String[] args) {
        // ojo: la variable es del tipo de la interfaz Graph
        Graph<String> g = new GraphLink<>();

        // metemos los vertices
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        // y las aristas
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "E");
        g.insertEdge("D", "E");

        System.out.println("=== Grafo inicial ===");
        System.out.print(g);

        // probamos las busquedas
        System.out.println("=== searchVertex ===");
        System.out.println("Existe 'C'? " + g.searchVertex("C"));
        System.out.println("Existe 'Z'? " + g.searchVertex("Z"));

        System.out.println("\n=== searchEdge ===");
        System.out.println("Arista A-B? " + g.searchEdge("A", "B"));
        System.out.println("Arista A-D? " + g.searchEdge("A", "D"));

        // los vecinos de un vertice
        System.out.println("\n=== adjacentVertices ===");
        System.out.println("Adyacentes de B: " + g.adjacentVertices("B"));
        System.out.println("Adyacentes de E: " + g.adjacentVertices("E"));

        // borramos una arista
        System.out.println("\n=== removeEdge(B, C) ===");
        g.removeEdge("B", "C");
        System.out.println("Arista B-C? " + g.searchEdge("B", "C"));
        System.out.println("Adyacentes de B: " + g.adjacentVertices("B"));
        System.out.println("Adyacentes de C: " + g.adjacentVertices("C"));

        // y ahora un vertice completo
        System.out.println("\n=== removeVertex(C) ===");
        g.removeVertex("C");
        System.out.println("Existe 'C'? " + g.searchVertex("C"));
        System.out.println("Grafo resultante:");
        System.out.print(g);
    }
}
