package Ejercicios;

public class Ejercicio5 {

    // compara dos listas, mismos elementos en el mismo orden
    public static <T> boolean sonIguales(LinkedList<T> lista1, LinkedList<T> lista2) {
        Node<T> n1 = lista1.getFirst();
        Node<T> n2 = lista2.getFirst();

        while (n1 != null && n2 != null) {
            if (!n1.value.equals(n2.value)) return false;
            n1 = n1.next;
            n2 = n2.next;
        }

        // si una termino antes que la otra, no son iguales
        return n1 == null && n2 == null;
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista1 = new LinkedList<>();
        lista1.insertLast(1);
        lista1.insertLast(2);
        lista1.insertLast(3);

        LinkedList<Integer> lista2 = new LinkedList<>();
        lista2.insertLast(1);
        lista2.insertLast(2);
        lista2.insertLast(3);

        LinkedList<Integer> lista3 = new LinkedList<>();
        lista3.insertLast(1);
        lista3.insertLast(2);
        lista3.insertLast(99);

        LinkedList<Integer> lista4 = new LinkedList<>();
        lista4.insertLast(1);
        lista4.insertLast(2);

        System.out.println("lista1 == lista2 (iguales):          " + sonIguales(lista1, lista2));
        System.out.println("lista1 == lista3 (distinto al final): " + sonIguales(lista1, lista3));
        System.out.println("lista1 == lista4 (distinta longitud): " + sonIguales(lista1, lista4));
    }
}