// Actividad 2: eliminacion de claves desde el arbol AVL de la figura 8.10
public class Actividad2 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 2 - ELIMINACIONES DESDE FIGURA 8.10");
        AVLTree<Integer> avl = AVLTree.figure810();
        Integer[] eliminar = {12, 33, 46, 59, 45, 56};

        System.out.println("Arbol inicial:");
        avl.printTree();

        for (int i = 0; i < eliminar.length; i++) {
            System.out.println("\nEliminando " + eliminar[i]);
            avl.delete(eliminar[i]);
            avl.printTree();
        }

        System.out.println("\nTabla de eliminaciones");
        System.out.println("K | Caso BST | Sucesor | Desbalance | Nodo X | Rotacion | Nodo Y | Otra rotacion");
        avl.printDeleteTable();
    }
}
