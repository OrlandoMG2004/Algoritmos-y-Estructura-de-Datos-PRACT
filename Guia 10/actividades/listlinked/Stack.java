package actividades.listlinked;

// Pila (LIFO). La usamos para devolver la ruta del Dijkstra
public class Stack<E> {
    private Node<E> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() { return top == null; }
    public int size() { return size; }

    public void push(E data) {
        top = new Node<>(data, top);
        size++;
    }

    public E pop() {
        if (top == null) return null;
        E data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public E peek() {
        if (top == null) return null;
        return top.getData();
    }

    @Override
    public String toString() {
        // se imprime desde la cima
        StringBuilder sb = new StringBuilder("[");
        Node<E> aux = top;
        while (aux != null) {
            sb.append(aux.getData());
            if (aux.getNext() != null) sb.append(", ");
            aux = aux.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
