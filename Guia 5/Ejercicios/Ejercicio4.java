package Ejercicios;

public class Ejercicio4 {

    // contar nodos recorriendo desde la cabeza
    public static <T> int contarNodos(Node<T> head) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        // lista con 4 nodos
        Node<String> head = new Node<>("A");
        head.next = new Node<>("B");
        head.next.next = new Node<>("C");
        head.next.next.next = new Node<>("D");

        System.out.println("Nodos en la lista: " + contarNodos(head));

        // lista vacia
        System.out.println("Nodos en lista null: " + contarNodos(null));

        // lista de un solo nodo
        Node<Integer> solo = new Node<>(99);
        System.out.println("Nodos en lista de uno: " + contarNodos(solo));
    }
}