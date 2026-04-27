package Ejercicios;

public class Node<T> {

    /** Valor almacenado en el nodo. */
    T value;

    /** Referencia al siguiente nodo de la lista. */
    Node<T> next;

    /**
     * Constructor que inicializa el nodo con un valor dado.
     *
     * @param value Valor a almacenar.
     */
    public Node(T value) {
        this.value = value;
        this.next  = null;
    }
}