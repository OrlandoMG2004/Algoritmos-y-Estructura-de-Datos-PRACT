package Ejercicios;

public class Ejercicio3 {

    // insertar al final trabajando directo con nodos, sin usar LinkedList
    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        Node<T> newNode = new Node<>(valor);

        if (head == null) return newNode;

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        // construir lista manualmente con nodos
        Node<Integer> head = null;
        head = insertarAlFinal(head, 10);
        head = insertarAlFinal(head, 20);
        head = insertarAlFinal(head, 30);
        head = insertarAlFinal(head, 40);

        // imprimir recorriendo nodos
        System.out.println("Lista resultante:");
        Node<Integer> current = head;
        while (current != null) {
            System.out.println("  " + current.value);
            current = current.next;
        }

        // insertar en lista vacia
        Node<String> headVacio = null;
        headVacio = insertarAlFinal(headVacio, "primero");
        System.out.println("Lista desde null: " + headVacio.value);
    }
}