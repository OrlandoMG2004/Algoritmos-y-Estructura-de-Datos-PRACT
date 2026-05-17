// Casos de prueba: minimo 8 inserciones con rotaciones AVL de todos los tipos
public class CasosPruebaAVL {
    public static void main(String[] args) {
        System.out.println("CASOS DE PRUEBA AVL - INSERCIONES Y ELIMINACIONES");
        probarInserciones();
        probarEliminaciones();
    }

    private static void probarInserciones() {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] datos = {
            90, 80, 70,
            100, 110,
            60, 65,
            120, 115,
            50, 40, 30,
            130, 140, 150,
            55, 57,
            125, 123
        };

        System.out.println("\nPruebas de insercion en el mismo arbol:");
        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }

        avl.printTree();
        System.out.println("\nTabla de rotaciones de insercion:");
        System.out.println("N | Insercion | Nodo X | Tipo | Rotacion | Nodo Y");
        avl.printRotationTable();
        System.out.println("\nEvidencia esperada: hay al menos 2 RSR, 2 RSL, 2 RDR y 2 RDL.");
    }

    private static void probarEliminaciones() {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 65, 90, 5};
        Integer[] eliminar = {5, 25, 30, 80, 70};

        System.out.println("\nPruebas de eliminacion:");
        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }

        System.out.println("Arbol antes de eliminar:");
        avl.printTree();

        for (int i = 0; i < eliminar.length; i++) {
            System.out.println("\nEliminando " + eliminar[i]);
            avl.delete(eliminar[i]);
            avl.printTree();
        }

        System.out.println("\nClave | Caso BST | Sucesor | Desbalance | Nodo X | Rotacion | Nodo Y | Otra rotacion");
        avl.printDeleteTable();
    }
}
