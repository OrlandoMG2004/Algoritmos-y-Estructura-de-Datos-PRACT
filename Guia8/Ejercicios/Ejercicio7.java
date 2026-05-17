// Ejercicio 7: insercion y eliminacion con casos de rotacion
public class Ejercicio7 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 7 - Insercion y eliminacion con rotaciones");
        AVL<Integer> avl = new AVL<Integer>("Rotaciones mixtas");
        Integer[] insertar = {30, 20, 10, 40, 50, 25, 27, 5, 4, 60, 55};
        Integer[] eliminar = {4, 10, 20, 50};

        for (int i = 0; i < insertar.length; i++) {
            avl.insert(insertar[i]);
        }
        System.out.println("Despues de insertar:");
        avl.printTree();

        for (int i = 0; i < eliminar.length; i++) {
            avl.delete(eliminar[i]);
            System.out.println("\nDespues de eliminar " + eliminar[i]);
            avl.printTree();
        }
        avl.printRotationLog();
    }
}
