package ejercicios.ej4;

import actividades.graph.Vertex;
import actividades.listlinked.ListLinked;
import actividades.listlinked.Queue;

// Otra forma de representar el grafo dirigido: en vez de listas de adyacencia,
// guardamos una lista con todos los vertices y otra con todas las aristas (EdgeObj).
public class GraphListEdge<E> implements Graph<E> {
    private ListLinked<Vertex<E>> secVertex;   // todos los vertices
    private ListLinked<EdgeObj<E>> secEdge;     // todas las aristas

    public GraphListEdge() {
        secVertex = new ListLinked<>();
        secEdge = new ListLinked<>();
    }

    private Vertex<E> findVertex(E data) {
        for (int i = 0; i < secVertex.size(); i++)
            if (secVertex.get(i).getData().equals(data))
                return secVertex.get(i);
        return null;
    }

    private int indexOf(E data) {
        for (int i = 0; i < secVertex.size(); i++)
            if (secVertex.get(i).getData().equals(data))
                return i;
        return -1;
    }

    @Override
    public void insertVertex(E v) {
        if (findVertex(v) != null) return;
        secVertex.addLast(new Vertex<>(v));
    }

    @Override
    public void insertEdge(E v, E z) {
        // dirigida v->z, la metemos a la lista de aristas
        Vertex<E> o = findVertex(v);
        Vertex<E> d = findVertex(z);
        if (o == null || d == null) return;
        if (searchEdge(v, z)) return;
        secEdge.addLast(new EdgeObj<>(o, d));
    }

    @Override
    public boolean searchVertex(E v) {
        return findVertex(v) != null;
    }

    @Override
    public boolean searchEdge(E v, E z) {
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<E> e = secEdge.get(i);
            if (e.getOrigin().getData().equals(v) && e.getDestination().getData().equals(z))
                return true;
        }
        return false;
    }

    @Override
    public int numVertices() {
        return secVertex.size();
    }

    public int numEdges() {
        return secEdge.size();
    }

    // mismo isConexo debil pero recorriendo la lista de aristas en vez de adyacencia
    @Override
    public boolean isConexo() {
        int n = secVertex.size();
        if (n == 0) return true;
        boolean[] visited = new boolean[n];
        Queue<Integer> cola = new Queue<>();
        visited[0] = true;
        cola.enqueue(0);
        int count = 1;
        while (!cola.isEmpty()) {
            int u = cola.dequeue();
            E uData = secVertex.get(u).getData();
            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<E> e = secEdge.get(i);
                int vecino = -1;
                if (e.getOrigin().getData().equals(uData))
                    vecino = indexOf(e.getDestination().getData());
                else if (e.getDestination().getData().equals(uData))
                    vecino = indexOf(e.getOrigin().getData());
                if (vecino != -1 && !visited[vecino]) {
                    visited[vecino] = true;
                    count++;
                    cola.enqueue(vecino);
                }
            }
        }
        return count == n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        for (int i = 0; i < secVertex.size(); i++)
            sb.append(secVertex.get(i)).append(" ");
        sb.append("\nAristas : ");
        for (int i = 0; i < secEdge.size(); i++)
            sb.append(secEdge.get(i)).append(" ");
        sb.append("\n");
        return sb.toString();
    }
}
