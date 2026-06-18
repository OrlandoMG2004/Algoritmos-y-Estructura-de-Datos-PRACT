package actividades.graph;

import actividades.listlinked.ListLinked;
import actividades.listlinked.Queue;
import actividades.listlinked.Stack;

// Grafo no dirigido con listas de adyacencia (puede ser ponderado).
// Es la clase de la Actividad 3, y aqui mismo le agregamos los metodos del Ej1
// (insertEdgeWeight, shortPath, isConexo, Dijkstra) y unos extras para el Ej2.
public class GraphLink<E> {
    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (findVertex(data) != null) return; // si ya existe no lo metemos de nuevo
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    // devuelve la posicion del vertice en la lista (o -1)
    private int indexOf(E data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data))
                return i;
        }
        return -1;
    }

    public void insertEdge(E origin, E destination) {
        // arista normal = peso 1
        insertEdgeWeight(origin, destination, 1);
    }

    // ----- Ejercicio 1 -----

    // inserta arista v-z con peso w. Como es no dirigido se agrega en los dos sentidos
    public void insertEdgeWeight(E v, E z, int w) {
        AdjList<E> v1 = findVertex(v);
        AdjList<E> v2 = findVertex(z);
        if (v1 == null || v2 == null) return;
        v1.getEdges().addLast(new Edge<>(v2.getVertex(), w));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), w));
    }

    // ruta mas corta de v a z contando aristas (BFS). Si no hay camino devuelve lista vacia
    public ListLinked<E> shortPath(E v, E z) {
        ListLinked<E> path = new ListLinked<>();
        int start = indexOf(v);
        int end = indexOf(z);
        if (start == -1 || end == -1) return path;

        int n = graph.size();
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) prev[i] = -1;

        Queue<Integer> cola = new Queue<>();
        visited[start] = true;
        cola.enqueue(start);

        while (!cola.isEmpty()) {
            int u = cola.dequeue();
            if (u == end) break;
            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                int vIdx = indexOf(adj.getEdges().get(j).getDestination().getData());
                if (!visited[vIdx]) {
                    visited[vIdx] = true;
                    prev[vIdx] = u;
                    cola.enqueue(vIdx);
                }
            }
        }

        if (!visited[end]) return path; // no se llego

        // armamos la ruta al reves (de z a v) con una pila y luego la damos vuelta
        Stack<E> pila = new Stack<>();
        int cur = end;
        while (cur != -1) {
            pila.push(graph.get(cur).getVertex().getData());
            cur = prev[cur];
        }
        while (!pila.isEmpty()) path.addLast(pila.pop());
        return path;
    }

    // true si desde el primer vertice se puede llegar a todos
    public boolean isConexo() {
        int n = graph.size();
        if (n == 0) return true;

        boolean[] visited = new boolean[n];
        Queue<Integer> cola = new Queue<>();
        visited[0] = true;
        cola.enqueue(0);
        int alcanzados = 1;

        while (!cola.isEmpty()) {
            int u = cola.dequeue();
            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                int vIdx = indexOf(adj.getEdges().get(j).getDestination().getData());
                if (!visited[vIdx]) {
                    visited[vIdx] = true;
                    alcanzados++;
                    cola.enqueue(vIdx);
                }
            }
        }
        return alcanzados == n;
    }

    // Dijkstra: devuelve una pila con la ruta mas corta por peso, la cima es v (el inicio)
    public Stack<E> Dijkstra(E v, E w) {
        Stack<E> result = new Stack<>();
        int start = indexOf(v);
        int end = indexOf(w);
        if (start == -1 || end == -1) return result;

        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[start] = 0;

        for (int count = 0; count < n; count++) {
            // buscamos el no visitado mas cercano
            int u = -1;
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < best) {
                    best = dist[i];
                    u = i;
                }
            }
            if (u == -1) break; // lo que queda ya no se alcanza
            visited[u] = true;

            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> e = adj.getEdges().get(j);
                int vIdx = indexOf(e.getDestination().getData());
                if (dist[u] != Integer.MAX_VALUE) {
                    int nd = dist[u] + e.getWeight();
                    if (!visited[vIdx] && nd < dist[vIdx]) {
                        dist[vIdx] = nd;
                        prev[vIdx] = u;
                    }
                }
            }
        }

        if (dist[end] == Integer.MAX_VALUE) return result; // no hay camino

        int cur = end;
        while (cur != -1) { // apilamos de w hacia v, asi la cima queda en v
            result.push(graph.get(cur).getVertex().getData());
            cur = prev[cur];
        }
        return result;
    }

    public int dijkstraCost(E v, E w) {
        // mismo Dijkstra pero solo nos interesa el costo total (-1 si no hay camino)
        int start = indexOf(v);
        int end = indexOf(w);
        if (start == -1 || end == -1) return -1;

        int n = graph.size();
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1, best = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++)
                if (!visited[i] && dist[i] < best) { best = dist[i]; u = i; }
            if (u == -1) break;
            visited[u] = true;
            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> e = adj.getEdges().get(j);
                int vIdx = indexOf(e.getDestination().getData());
                if (dist[u] != Integer.MAX_VALUE) {
                    int nd = dist[u] + e.getWeight();
                    if (!visited[vIdx] && nd < dist[vIdx]) dist[vIdx] = nd;
                }
            }
        }
        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }

    // ----- cosas usadas en el Ej2 (red de ciudades) -----

    public int numVertices() {
        return graph.size();
    }

    public ListLinked<E> getVertices() {
        // junta los datos de todos los vertices en una lista
        ListLinked<E> lista = new ListLinked<>();
        for (int i = 0; i < graph.size(); i++) {
            lista.addLast(graph.get(i).getVertex().getData());
        }
        return lista;
    }

    public void showConnections() {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> e = adj.getEdges().get(j);
                int destIdx = indexOf(e.getDestination().getData());
                if (i < destIdx) { // con esto cada arista se imprime una sola vez
                    System.out.println("  " + adj.getVertex() + " <-> "
                            + e.getDestination().getData() + "  :  " + e.getWeight());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            sb.append(adj.getVertex()).append(" -> ");
            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
