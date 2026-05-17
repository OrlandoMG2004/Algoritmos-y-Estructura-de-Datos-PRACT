// Ejercicio 1: GestorTicketsAVL
public class Ejercicio1 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 1 - GestorTicketsAVL");
        AVL<Integer> tickets = new AVL<Integer>("Tickets");
        Integer[] insertar = {30, 10, 20, 40, 50, 25};
        Integer[] eliminar = {10, 40, 30};

        for (int i = 0; i < insertar.length; i++) {
            tickets.insert(insertar[i]);
            System.out.println("\nInsertado ticket " + insertar[i]);
            tickets.printTree();
        }

        System.out.println("\nBuscar 20: " + tickets.contains(20));
        System.out.println("Buscar 60: " + tickets.contains(60));
        System.out.println("Inorden: " + tickets.inOrder());

        for (int i = 0; i < eliminar.length; i++) {
            tickets.delete(eliminar[i]);
            System.out.println("\nEliminado ticket " + eliminar[i]);
            tickets.printTree();
        }
        tickets.printRotationLog();
    }
}
