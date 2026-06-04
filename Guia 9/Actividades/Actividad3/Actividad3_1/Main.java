// Actividad 3.1 - Prueba de la clase BNode
public class Main {
    public static void main(String[] args) {
        int orden = 4;
        BNode<Integer> nodo = new BNode<>(orden);

        System.out.println("Nodo recien creado -> vacio? " + nodo.nodeEmpty());

        nodo.keys.set(0, 20);
        nodo.keys.set(1, 50);
        nodo.keys.set(2, 70);
        nodo.count = 3;

        System.out.println("Contenido (toString): " + nodo);
        System.out.println("Lleno? (orden-1 = 3)  : " + nodo.nodeFull(orden - 1));

        int[] pos = new int[1];
        System.out.println("Buscar 50 -> " + nodo.searchNode(50, pos) + " en pos " + pos[0]);
        System.out.println("Buscar 60 -> " + nodo.searchNode(60, pos) + " (descender por hijo " + pos[0] + ")");
    }
}
