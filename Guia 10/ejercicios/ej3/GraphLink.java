package ejercicios.ej3;

import actividades.graph.Vertex;
import actividades.graph.Edge;
import actividades.graph.AdjList;
import actividades.listlinked.ListLinked;

// Implementacion del TAD Graph (interfaz) con listas de adyacencia.
// No dirigido => las aristas se guardan en los dos sentidos.
// Vertex, Edge y AdjList vienen del package actividades.graph (no se repiten aca)
public class GraphLink<E> implements Graph<E> {
    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    @Override
    public void insertVertex(E v) {
        if (findVertex(v) != null) return; // no repetimos vertices
        graph.addLast(new AdjList<>(new Vertex<>(v)));
    }

    @Override
    public void insertEdge(E v, E z) {
        AdjList<E> a1 = findVertex(v);
        AdjList<E> a2 = findVertex(z);
        if (a1 == null || a2 == null) return;
        if (searchEdge(v, z)) return; // si ya esta no la duplicamos
        a1.getEdges().addLast(new Edge<>(a2.getVertex()));
        a2.getEdges().addLast(new Edge<>(a1.getVertex()));
    }

    // borrar un vertice es lo mas trabajoso: hay que sacar tambien todas las aristas que llegan a el
    @Override
    public void removeVertex(E v) {
        if (findVertex(v) == null) return;

        // primero limpiamos las aristas que apuntan a v en el resto de listas
        for (int i = 0; i < graph.size(); i++) {
            ListLinked<Edge<E>> edges = graph.get(i).getEdges();
            int j = 0;
            while (j < edges.size()) {
                if (edges.get(j).getDestination().getData().equals(v)) {
                    edges.removeAt(j); // no subo j porque la lista se corre
                } else {
                    j++;
                }
            }
        }

        // y ahora si sacamos el vertice
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(v)) {
                graph.removeAt(i);
                break;
            }
        }
    }

    @Override
    public void removeEdge(E v, E z) {
        AdjList<E> a1 = findVertex(v);
        AdjList<E> a2 = findVertex(z);
        if (a1 == null || a2 == null) return;
        // como es no dirigido hay que quitar las dos
        removeEdgeFrom(a1.getEdges(), z);
        removeEdgeFrom(a2.getEdges(), v);
    }

    private void removeEdgeFrom(ListLinked<Edge<E>> edges, E destino) {
        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(j).getDestination().getData().equals(destino)) {
                edges.removeAt(j);
                return;
            }
        }
    }

    @Override
    public boolean searchVertex(E v) {
        return findVertex(v) != null;
    }

    @Override
    public boolean searchEdge(E v, E z) {
        AdjList<E> a1 = findVertex(v);
        if (a1 == null) return false;
        ListLinked<Edge<E>> edges = a1.getEdges();
        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(j).getDestination().getData().equals(z))
                return true;
        }
        return false;
    }

    // devuelve los vecinos de v
    @Override
    public ListLinked<E> adjacentVertices(E v) {
        ListLinked<E> result = new ListLinked<>();
        AdjList<E> a1 = findVertex(v);
        if (a1 == null) return result;
        ListLinked<Edge<E>> edges = a1.getEdges();
        for (int j = 0; j < edges.size(); j++) {
            result.addLast(edges.get(j).getDestination().getData());
        }
        return result;
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
