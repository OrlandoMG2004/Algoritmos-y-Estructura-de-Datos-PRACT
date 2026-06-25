package ejercicios.ej6;

// Lista enlazada propia para las cadenas del cache de sesiones.
public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

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
}
