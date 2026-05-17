// Actividad 1: insercion de claves en un arbol AVL
public class Actividad1 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 1 - INSERCIONES AVL");
        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] claves = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};

        for (int i = 0; i < claves.length; i++) {
            System.out.println("\nInsertando " + claves[i]);
            avl.insert(claves[i]);
            avl.printTree();
        }

        System.out.println("\nTabla de reestructuraciones");
        System.out.println("N | Insercion | Nodo X | Tipo | Rotacion | Nodo Y");
        avl.printRotationTable();
        System.out.println("\nInorden final: " + avl.inOrder());
        System.out.println("Altura final: " + avl.height());
    }
}
