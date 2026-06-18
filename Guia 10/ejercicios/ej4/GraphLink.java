package ejercicios.ej4;

import actividades.graph.Vertex;
import actividades.graph.Edge;
import actividades.graph.AdjList;
import actividades.listlinked.ListLinked;
import actividades.listlinked.Queue;

// Grafo DIRIGIDO con lista de adyacencia. Aca van los analisis del ej4:
// isConexo, isIsomorfo, isPlano y isAutoComplementario.
// Reusa Vertex, Edge y AdjList del package actividades.graph
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

    private int indexOf(E data) {
        for (int i = 0; i < graph.size(); i++)
            if (graph.get(i).getVertex().getData().equals(data))
                return i;
        return -1;
    }

    @Override
    public void insertVertex(E v) {
        if (findVertex(v) != null) return;
        graph.addLast(new AdjList<>(new Vertex<>(v)));
    }

    @Override
    public void insertEdge(E v, E z) {
        // ojo: dirigido, solo se agrega v -> z (no el regreso)
        AdjList<E> a1 = findVertex(v);
        AdjList<E> a2 = findVertex(z);
        if (a1 == null || a2 == null) return;
        if (searchEdge(v, z)) return;
        a1.getEdges().addLast(new Edge<>(a2.getVertex()));
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
        for (int j = 0; j < edges.size(); j++)
            if (edges.get(j).getDestination().getData().equals(z))
                return true;
        return false;
    }

    @Override
    public int numVertices() {
        return graph.size();
    }

    // pasa el grafo a matriz de adyacencia booleana, A[i][j]=true si hay arista i->j
    public boolean[][] toMatrix() {
        int n = graph.size();
        boolean[][] A = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            ListLinked<Edge<E>> edges = graph.get(i).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                int destIdx = indexOf(edges.get(j).getDestination().getData());
                if (destIdx != -1) A[i][destIdx] = true;
            }
        }
        return A;
    }

    // conexo de forma debil: tratamos las aristas como si no tuvieran direccion y hacemos BFS
    @Override
    public boolean isConexo() {
        int n = graph.size();
        if (n == 0) return true;
        boolean[][] A = toMatrix();
        boolean[] visited = new boolean[n];
        Queue<Integer> cola = new Queue<>();
        visited[0] = true;
        cola.enqueue(0);
        int count = 1;
        while (!cola.isEmpty()) {
            int u = cola.dequeue();
            for (int v = 0; v < n; v++) {
                if ((A[u][v] || A[v][u]) && !visited[v]) {
                    visited[v] = true;
                    count++;
                    cola.enqueue(v);
                }
            }
        }
        return count == n;
    }

    // isPlano: usamos la condicion de Euler E <= 3V-6 (para V>=3).
    // Es solo condicion NECESARIA: si no se cumple seguro no es plano; si se cumple, podria serlo.
    public boolean isPlano() {
        int n = graph.size();
        if (n < 3) return true;
        boolean[][] A = toMatrix();
        int m = 0; // aristas del grafo subyacente sin direccion
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (A[i][j] || A[j][i]) m++;
        return m <= 3 * n - 6;
    }

    // dos grafos son isomorfos si existe una forma de emparejar sus vertices que respete las aristas
    public boolean isIsomorfo(GraphLink<E> other) {
        int n = this.numVertices();
        if (n != other.numVertices()) return false;

        boolean[][] A = this.toMatrix();
        boolean[][] B = other.toMatrix();

        if (countEdges(A) != countEdges(B)) return false;
        if (!sameDegreeSequence(A, B)) return false; // descarte rapido por grados

        int[] perm = new int[n];
        boolean[] used = new boolean[n];
        return match(A, B, perm, used, 0, n);
    }

    // backtracking: va asignando perm[i] (vertice i de A -> un vertice de B) revisando
    // que las aristas con los ya asignados coincidan
    private boolean match(boolean[][] A, boolean[][] B, int[] perm, boolean[] used, int i, int n) {
        if (i == n) return true;
        for (int v = 0; v < n; v++) {
            if (used[v]) continue;
            if (A[i][i] != B[v][v]) continue; // los lazos tambien deben calzar
            perm[i] = v;
            boolean ok = true;
            for (int j = 0; j < i && ok; j++) {
                if (A[i][j] != B[v][perm[j]]) ok = false;
                if (A[j][i] != B[perm[j]][v]) ok = false;
            }
            if (ok) {
                used[v] = true;
                if (match(A, B, perm, used, i + 1, n)) return true;
                used[v] = false;
            }
        }
        return false;
    }

    private int countEdges(boolean[][] A) {
        int c = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A.length; j++)
                if (A[i][j]) c++;
        return c;
    }

    // compara la secuencia de grados (salida,entrada). Si difieren, ni vale la pena probar permutaciones
    private boolean sameDegreeSequence(boolean[][] A, boolean[][] B) {
        int n = A.length;
        int[] da = new int[n];
        int[] db = new int[n];
        for (int i = 0; i < n; i++) {
            int outA = 0, inA = 0, outB = 0, inB = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j]) outA++;
                if (A[j][i]) inA++;
                if (B[i][j]) outB++;
                if (B[j][i]) inB++;
            }
            da[i] = outA * 1000 + inA; // metemos el par (out,in) en un solo numero
            db[i] = outB * 1000 + inB;
        }
        insertionSort(da);
        insertionSort(db);
        for (int i = 0; i < n; i++)
            if (da[i] != db[i]) return false;
        return true;
    }

    private void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // auto-complementario: el complemento sale isomorfo al grafo original
    public boolean isAutoComplementario() {
        int n = numVertices();
        boolean[][] A = toMatrix();
        if (countEdges(A) * 2 != n * (n - 1)) return false; // tiene que tener n(n-1)/2 aristas
        GraphLink<E> comp = buildComplement();
        return this.isIsomorfo(comp);
    }

    // complemento: mismos vertices, y ponemos las aristas (i,j) que NO estaban (i!=j)
    public GraphLink<E> buildComplement() {
        GraphLink<E> c = new GraphLink<>();
        int n = numVertices();
        for (int i = 0; i < n; i++)
            c.insertVertex(graph.get(i).getVertex().getData());
        boolean[][] A = toMatrix();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && !A[i][j])
                    c.insertEdge(graph.get(i).getVertex().getData(),
                                 graph.get(j).getVertex().getData());
        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            sb.append(adj.getVertex()).append(" -> ");
            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j).getDestination().getData()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
