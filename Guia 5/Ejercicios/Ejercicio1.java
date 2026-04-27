package Ejercicios;

public class Ejercicio1 {

    // buscar elemento en la lista sin usar el metodo search interno
    public static <T> boolean buscarElemento(LinkedList<T> lista, T valor) {
        Node<T> current = lista.getFirst();
        while (current != null) {
            if (current.value.equals(valor)) return true;
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList<String> lista = new LinkedList<>();
        lista.insertLast("tarea A");
        lista.insertLast("tarea B");
        lista.insertLast("tarea C");

        System.out.println("Buscar 'tarea B': " + buscarElemento(lista, "tarea B"));
        System.out.println("Buscar 'tarea Z': " + buscarElemento(lista, "tarea Z"));

        // probar con enteros
        LinkedList<Integer> numeros = new LinkedList<>();
        numeros.insertLast(10);
        numeros.insertLast(20);
        numeros.insertLast(30);

        System.out.println("Buscar 20: " + buscarElemento(numeros, 20));
        System.out.println("Buscar 99: " + buscarElemento(numeros, 99));
    }
}