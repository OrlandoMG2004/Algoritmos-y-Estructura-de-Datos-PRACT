public class GestorDeTareas<T> {

    private final LinkedList<T> lista;

    public GestorDeTareas() {
        this.lista = new LinkedList<>();
    }

    public void agregarTarea(T tarea) {
        lista.insertLast(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        return lista.removeNode(tarea);
    }

    public boolean contieneTarea(T tarea) {
        return lista.search(tarea);
    }

    public void imprimirTareas() {
        lista.print();
    }

    public int contarTareas() {
        return lista.length();
    }

    public void invertirTareas() {
        lista.reverse();
    }

    // devuelve la tarea con menor valor de prioridad (1 = mas urgente)
    @SuppressWarnings("unchecked")
    public T obtenerTareaMasPrioritaria() {
        if (lista.isEmptyList()) return null;

        Node<T> current = lista.getFirst();
        T masPrioritaria = current.value;
        current = current.next;

        while (current != null) {
            Comparable<T> comp = (Comparable<T>) current.value;
            if (comp.compareTo(masPrioritaria) < 0) {
                masPrioritaria = current.value;
            }
            current = current.next;
        }
        return masPrioritaria;
    }

    public LinkedList<T> getLista() {
        return lista;
    }
}