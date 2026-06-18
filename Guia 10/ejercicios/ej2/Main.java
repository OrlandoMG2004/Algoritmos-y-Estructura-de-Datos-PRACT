package ejercicios.ej2;

import actividades.graph.GraphLink;
import actividades.listlinked.ListLinked;
import actividades.listlinked.Stack;

// Red de ciudades con grafos (ejercicio 2).
// El enunciado pedia usar JGraphT pero como no podemos usar librerias externas
// lo hacemos con nuestro propio GraphLink y Dijkstra a mano.
public class Main {
    public static void main(String[] args) {
        GraphLink<String> red = new GraphLink<>();

        // las ciudades
        red.insertVertex("Arequipa");
        red.insertVertex("Cusco");
        red.insertVertex("Puno");
        red.insertVertex("Tacna");
        red.insertVertex("Moquegua");

        // las carreteras con su distancia en km
        red.insertEdgeWeight("Arequipa", "Cusco", 510);
        red.insertEdgeWeight("Arequipa", "Moquegua", 230);
        red.insertEdgeWeight("Moquegua", "Tacna", 160);
        red.insertEdgeWeight("Cusco", "Puno", 390);
        red.insertEdgeWeight("Puno", "Tacna", 420);

        // listamos las ciudades
        System.out.println("=== Lista de ciudades (" + red.numVertices() + ") ===");
        ListLinked<String> ciudades = red.getVertices();
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println("  - " + ciudades.get(i));
        }

        // todas las carreteras
        System.out.println("\n=== Carreteras registradas (km) ===");
        red.showConnections();

        System.out.println("\n=== Conexiones por ciudad (destino(km)) ===");
        System.out.print(red);

        // y ahora el camino mas corto entre dos ciudades con Dijkstra
        String origen = "Arequipa";
        String destino = "Tacna";
        System.out.println("=== Camino mas corto: " + origen + " -> " + destino + " (Dijkstra) ===");

        Stack<String> ruta = red.Dijkstra(origen, destino);
        if (ruta.isEmpty()) {
            System.out.println("No existe ruta entre " + origen + " y " + destino);
        } else {
            StringBuilder camino = new StringBuilder();
            while (!ruta.isEmpty()) {
                camino.append(ruta.pop());
                if (!ruta.isEmpty()) camino.append(" -> ");
            }
            System.out.println("Ruta optima : " + camino);
            System.out.println("Costo total : " + red.dijkstraCost(origen, destino) + " km");
        }
    }
}
