package Ejercicios;

// lista enlazada ordenada que hereda de LinkedList
// insertOrden mantiene el orden ascendente en todo momento
class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    public void insertOrden(T x) {
        Node<T> newNode = new Node<>(x);

        // lista vacia o el nuevo es menor que el primero
        if (first == null || x.compareTo(first.value) <= 0) {
            newNode.next = first;
            first = newNode;
            return;
        }

        // buscar la posicion correcta
        Node<T> current = first;
        while (current.next != null && x.compareTo(current.next.value) > 0) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
}

public class Ejercicio7 {

    public static void main(String[] args) {
        // probar con enteros
        SortedLinkedList<Integer> listaInt = new SortedLinkedList<>();
        listaInt.insertOrden(5);
        listaInt.insertOrden(2);
        listaInt.insertOrden(8);
        listaInt.insertOrden(1);
        listaInt.insertOrden(4);

        System.out.println("Lista ordenada de enteros:");
        listaInt.print();

        // probar con tareas ordenadas por prioridad
        SortedLinkedList<Tarea> listaTareas = new SortedLinkedList<>();
        listaTareas.insertOrden(new Tarea("Documentar API",    3, "pendiente"));
        listaTareas.insertOrden(new Tarea("Deploy produccion", 1, "pendiente"));
        listaTareas.insertOrden(new Tarea("Code review",       2, "pendiente"));
        listaTareas.insertOrden(new Tarea("Corregir bug #42",  1, "completada"));
        listaTareas.insertOrden(new Tarea("Diseñar BD",        2, "pendiente"));

        System.out.println("\nTareas ordenadas por prioridad:");
        listaTareas.print();
    }
}