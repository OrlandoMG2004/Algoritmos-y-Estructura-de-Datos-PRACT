// Actividad 3.2: redefinicion del metodo de insercion en AVLTree
public class Actividad3_2 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 3.2 - INSERCION AVL REDEFINIDA");
        System.out.println("La insercion empieza como BST y luego actualiza bf al regresar hacia la raiz.");

        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] datos = {50, 30, 20, 10, 40, 35};

        for (int i = 0; i < datos.length; i++) {
            System.out.println("\nInsertando " + datos[i]);
            avl.insert(datos[i]);
            avl.printTree();
        }

        System.out.println("\nRotaciones detectadas durante la insercion:");
        System.out.println("N | Insercion | Nodo X | Tipo | Rotacion | Nodo Y");
        avl.printRotationTable();
        System.out.println("Inorden final: " + avl.inOrder());
    }
}
