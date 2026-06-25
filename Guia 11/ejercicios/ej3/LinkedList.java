package ejercicios.ej3;

// Lista enlazada propia del estudiante (no se usa java.util.LinkedList).
public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    // agrega al final
    public void add(E data) {
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

    public E get(int index) {
        if (index < 0 || index >= size) return null;
        Node<E> aux = head;
        for (int i = 0; i < index; i++) aux = aux.getNext();
        return aux.getData();
    }

    // elimina la primera aparicion de data (usa equals)
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
        StringBuilder sb = new StringBuilder();
        Node<E> aux = head;
        while (aux != null) {
            sb.append(aux.getData());
            if (aux.getNext() != null) sb.append(" -> ");
            aux = aux.getNext();
        }
        return sb.toString();
    }
}
