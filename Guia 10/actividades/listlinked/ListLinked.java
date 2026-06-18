package actividades.listlinked;

// Nuestra lista enlazada, la usamos en vez de ArrayList/LinkedList de java.util
public class ListLinked<E> {
    private Node<E> head;
    private int size;

    public ListLinked() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    // agrega al final
    public void addLast(E data) {
        Node<E> nuevo = new Node<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            Node<E> aux = head;
            while (aux.getNext() != null) aux = aux.getNext();
            aux.setNext(nuevo);
        }
        size++;
    }

    // agrega al inicio
    public void addFirst(E data) {
        head = new Node<>(data, head);
        size++;
    }

    public E get(int index) {
        // devuelve el dato en esa posicion, o null si se pasa
        if (index < 0 || index >= size) return null;
        Node<E> aux = head;
        for (int i = 0; i < index; i++) aux = aux.getNext();
        return aux.getData();
    }

    // elimina por posicion (true si pudo)
    public boolean removeAt(int index) {
        if (index < 0 || index >= size) return false;
        if (index == 0) {
            head = head.getNext();
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.getNext();
            prev.setNext(prev.getNext().getNext());
        }
        size--;
        return true;
    }

    // elimina la primera aparicion de data (comparando con equals)
    public boolean remove(E data) {
        Node<E> aux = head;
        Node<E> prev = null;
        while (aux != null) {
            if (aux.getData().equals(data)) {
                if (prev == null) head = aux.getNext();
                else prev.setNext(aux.getNext());
                size--;
                return true;
            }
            prev = aux;
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> aux = head;
        while (aux != null) {
            sb.append(aux.getData());
            if (aux.getNext() != null) sb.append(", ");
            aux = aux.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
