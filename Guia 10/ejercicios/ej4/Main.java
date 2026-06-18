package ejercicios.ej4;

// Pruebas del ej4: ver si un grafo dirigido es isomorfo, plano, conexo y
// auto-complementario. Se prueba con GraphLink y tambien con GraphListEdge.
public class Main {
    public static void main(String[] args) {

        // G1 = un ciclo dirigido 1->2->3->4->1
        GraphLink<String> g1 = new GraphLink<>();
        g1.insertVertex("1");
        g1.insertVertex("2");
        g1.insertVertex("3");
        g1.insertVertex("4");
        g1.insertEdge("1", "2");
        g1.insertEdge("2", "3");
        g1.insertEdge("3", "4");
        g1.insertEdge("4", "1");

        System.out.println("=== G1 (ciclo dirigido) ===");
        System.out.print(g1);
        System.out.println("isConexo? " + g1.isConexo());
        System.out.println("isPlano?  " + g1.isPlano());

        // G2 es otro ciclo (A->B->C->D->A), deberia salir isomorfo a G1
        GraphLink<String> g2 = new GraphLink<>();
        g2.insertVertex("A");
        g2.insertVertex("B");
        g2.insertVertex("C");
        g2.insertVertex("D");
        g2.insertEdge("A", "B");
        g2.insertEdge("B", "C");
        g2.insertEdge("C", "D");
        g2.insertEdge("D", "A");

        // G3 es una estrella (1->2,1->3,1->4), esta tiene otra forma asi que NO deberia ser isomorfo
        GraphLink<String> g3 = new GraphLink<>();
        g3.insertVertex("1");
        g3.insertVertex("2");
        g3.insertVertex("3");
        g3.insertVertex("4");
        g3.insertEdge("1", "2");
        g3.insertEdge("1", "3");
        g3.insertEdge("1", "4");

        System.out.println("\n=== Isomorfismo ===");
        System.out.println("G1 isomorfo a G2 (ciclo vs ciclo)? " + g1.isIsomorfo(g2));
        System.out.println("G1 isomorfo a G3 (ciclo vs estrella)? " + g1.isIsomorfo(g3));

        // torneo transitivo sobre {1,2,3}: 1->2, 1->3, 2->3 (este es auto-complementario)
        GraphLink<String> gt = new GraphLink<>();
        gt.insertVertex("1");
        gt.insertVertex("2");
        gt.insertVertex("3");
        gt.insertEdge("1", "2");
        gt.insertEdge("1", "3");
        gt.insertEdge("2", "3");

        System.out.println("\n=== Auto-complementario ===");
        System.out.println("Grafo GT (torneo transitivo):");
        System.out.print(gt);
        System.out.println("Su complemento:");
        System.out.print(gt.buildComplement());
        System.out.println("GT es auto-complementario? " + gt.isAutoComplementario());
        System.out.println("G1 (ciclo) es auto-complementario? " + g1.isAutoComplementario());

        // K5 (todos conectados con todos) no es plano
        GraphLink<String> k5 = new GraphLink<>();
        String[] vs = {"a", "b", "c", "d", "e"};
        for (String v : vs) k5.insertVertex(v);
        for (int i = 0; i < vs.length; i++)
            for (int j = i + 1; j < vs.length; j++)
                k5.insertEdge(vs[i], vs[j]); // grafo completo (subyacente K5)

        System.out.println("\n=== Planaridad ===");
        System.out.println("G1 (ciclo de 4) es plano? " + g1.isPlano());
        System.out.println("K5 es plano? " + k5.isPlano());

        // ahora el mismo G1 pero con la otra representacion (lista de aristas)
        GraphListEdge<String> ge = new GraphListEdge<>();
        ge.insertVertex("1");
        ge.insertVertex("2");
        ge.insertVertex("3");
        ge.insertVertex("4");
        ge.insertEdge("1", "2");
        ge.insertEdge("2", "3");
        ge.insertEdge("3", "4");
        ge.insertEdge("4", "1");

        System.out.println("\n=== G1 con GraphListEdge (lista de aristas) ===");
        System.out.print(ge);
        System.out.println("Numero de vertices: " + ge.numVertices());
        System.out.println("Numero de aristas : " + ge.numEdges());
        System.out.println("isConexo? " + ge.isConexo());
    }
}
