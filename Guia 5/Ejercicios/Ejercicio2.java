package Ejercicios;

public class Ejercicio2 {

    // retorna una nueva lista con los elementos en orden inverso, sin tocar la original
    public static <T> LinkedList<T> invertirLista(LinkedList<T> lista) {
        LinkedList<T> nueva = new LinkedList<>();
        Node<T> current = lista.getFirst();
        while (current != null) {
            nueva.insertFirst(current.value);
            current = current.next;
        }
        return nueva;
    }

    public static void main(String[] args) {
        LinkedList<String> original = new LinkedList<>();
        original.insertLast("A");
        original.insertLast("B");
        original.insertLast("C");
        original.insertLast("D");

        LinkedList<String> invertida = invertirLista(original);

        System.out.println("Lista original:");
        original.print();

        System.out.println("Lista invertida:");
        invertida.print();

        // verificar que la original no cambio
        System.out.println("Original sin cambios (primer nodo): " + original.getFirst().value);
    }
}