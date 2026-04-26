public class LinkedList<T> {

    protected Node<T> first;

    public LinkedList() {
        first = null;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public int length() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void destroyList() {
        first = null;
    }

    // insertar al inicio
    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = first;
        first = newNode;
    }

    // insertar al final
    public void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (first == null) {
            first = newNode;
            return;
        }
        Node<T> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // buscar elemento, usa equals para comparar
    public boolean search(T x) {
        Node<T> current = first;
        while (current != null) {
            if (current.value.equals(x)) return true;
            current = current.next;
        }
        return false;
    }

    // eliminar nodo por valor
    public boolean removeNode(T x) {
        if (first == null) return false;

        // caso: es el primer nodo
        if (first.value.equals(x)) {
            first = first.next;
            return true;
        }

        Node<T> prev = first;
        Node<T> current = first.next;
        while (current != null) {
            if (current.value.equals(x)) {
                prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public void print() {
        if (first == null) {
            System.out.println("  [Lista vacía]");
            return;
        }
        Node<T> current = first;
        while (current != null) {
            System.out.println("  " + current.value);
            current = current.next;
        }
    }

    // invertir la lista en sitio
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = first;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        first = prev;
    }

    public Node<T> getFirst() {
        return first;
    }
}