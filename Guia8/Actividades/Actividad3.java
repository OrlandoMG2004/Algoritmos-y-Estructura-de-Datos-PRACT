// Actividad 3: implementacion general del TAD arbol AVL
public class Actividad3 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 3 - IMPLEMENTACION DEL TAD AVL CON HERENCIA");
        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] pruebas = {
            90, 80, 70,
            100, 110,
            60, 65,
            120, 115,
            50, 40, 30,
            130, 140, 150,
            55, 57,
            125, 123
        };

        for (int i = 0; i < pruebas.length; i++) {
            System.out.println("\nInsertando " + pruebas[i]);
            avl.insert(pruebas[i]);
            avl.printTree();
        }

        System.out.println("\nResumen de rotaciones pedidas para los casos de prueba");
        avl.printRotationTable();
        System.out.println("\nBusquedas de evidencia:");
        System.out.println("Existe 115: " + avl.contains(115));
        System.out.println("Existe 999: " + avl.contains(999));
        System.out.println("Preorden: " + avl.preOrder());
        System.out.println("Por niveles recursivo: " + avl.levelOrderRecursive());
    }
}
