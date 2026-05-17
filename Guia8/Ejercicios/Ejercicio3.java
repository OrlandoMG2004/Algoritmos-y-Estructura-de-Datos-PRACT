// Ejercicio 3: eliminacion en un arbol AVL
public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 3 - Eliminacion en AVL");
        AVL<Integer> avl = new AVL<Integer>("Eliminacion");
        Integer[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 65, 90, 5};
        Integer[] eliminar = {5, 25, 30, 80, 70};

        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }
        System.out.println("Arbol inicial:");
        avl.printTree();

        for (int i = 0; i < eliminar.length; i++) {
            avl.delete(eliminar[i]);
            System.out.println("\nDespues de eliminar " + eliminar[i]);
            avl.printTree();
        }

        System.out.println("\nTabla solicitada");
        System.out.println("Clave | Caso BST | Sucesor | Nodo X | Rotacion");
        avl.printDeleteLog();
    }
}
