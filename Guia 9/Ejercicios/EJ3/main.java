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

        System.out.println("ÁRBOL B INICIAL:");
        System.out.println(tree);

        int[] eliminar = {
                25, 10, 50, 70, 27, 5, 75
        };

        for (int dato : eliminar) {
            System.out.println("\nEliminando: " + dato);
            tree.remove(dato);
            System.out.println(tree);
        }
    }
}
