package Ejercicios;

public class Ejercicio6 {

    // une dos listas en una nueva sin modificar las originales
    public static <T> LinkedList<T> concatenarListas(LinkedList<T> lista1, LinkedList<T> lista2) {
        LinkedList<T> nueva = new LinkedList<>();

        Node<T> current = lista1.getFirst();
        while (current != null) {
            nueva.insertLast(current.value);
            current = current.next;
        }

        current = lista2.getFirst();
        while (current != null) {
            nueva.insertLast(current.value);
            current = current.next;
        }

        return nueva;
    }

    public static void main(String[] args) {
        LinkedList<String> lista1 = new LinkedList<>();
        lista1.insertLast("A");
        lista1.insertLast("B");
        lista1.insertLast("C");

        LinkedList<String> lista2 = new LinkedList<>();
        lista2.insertLast("D");
        lista2.insertLast("E");

        LinkedList<String> resultado = concatenarListas(lista1, lista2);

        System.out.println("Lista 1:");
        lista1.print();

        System.out.println("Lista 2:");
        lista2.print();

        System.out.println("Concatenada:");
        resultado.print();

        // verificar que las originales no cambiaron
        System.out.println("lista1 sigue con " + lista1.length() + " elementos");
        System.out.println("lista2 sigue con " + lista2.length() + " elementos");
    }
}