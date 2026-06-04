public class Main {
    public static void main(String[] args) {

        BTree<Integer> tree = new BTree<>(4);

        int[] datos = {
                50, 20, 70, 10, 30, 60, 80,
                25, 27, 26, 65, 75, 85, 5
        };

        for (int dato : datos) {
            tree.insert(dato);
        }

        System.out.println("ÁRBOL B:");
        System.out.println(tree);

        System.out.println("PRUEBAS SEARCH:");
        tree.search(5);     // hoja extremo inicial
        tree.search(85);    // hoja extremo final
        tree.search(50);    // raíz o nodo interno
        tree.search(100);   // no encontrado
    }
}
