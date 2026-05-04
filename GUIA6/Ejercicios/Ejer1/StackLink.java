package GUIA6.Ejercicios.Ejer1;

class Node<E> {
    E data;
    Node<E> next;

    Node(E d) {
        data = d;
    }
}

public class StackLink<E> {

    private Node<E> top;

    public void push(E x) {
        Node<E> n = new Node<>(x);
        n.next = top;
        top = n;
    }

    public E pop() throws ExceptionIsEmpty {
        if (top == null)
            throw new ExceptionIsEmpty("Pila vacía");

        E d = top.data;
        top = top.next;
        return d;
    }

    public E top() throws ExceptionIsEmpty {
        if (top == null)
            throw new ExceptionIsEmpty("Pila vacía");

        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}