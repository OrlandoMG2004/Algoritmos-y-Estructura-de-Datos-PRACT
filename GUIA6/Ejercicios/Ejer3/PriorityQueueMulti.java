package GUIA6.Ejercicios.Ejer3;

class Node<E> {
    E data;
    Node<E> next;

    Node(E d) {
        data = d;
    }
}

class SimpleQueue<E> {
    Node<E> front, rear;

    void enqueue(E x) {
        Node<E> n = new Node<>(x);
        if (rear == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
    }

    E dequeue() {
        if (front == null) return null;
        E d = front.data;
        front = front.next;
        if (front == null) rear = null;
        return d;
    }

    boolean isEmpty() {
        return front == null;
    }
}

public class PriorityQueueMulti<E> {

    private SimpleQueue<E>[] queues;
    private int levels;

    public PriorityQueueMulti(int levels) {
        this.levels = levels;
        queues = new SimpleQueue[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new SimpleQueue<>();
        }
    }

    public void enqueue(E x, int p) {
        queues[p].enqueue(x);
    }

    public E dequeue() {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
    }
}