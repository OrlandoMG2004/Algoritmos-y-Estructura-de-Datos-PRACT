package GUIA6.Ejercicios.Ejer4;

class Element<E> {
    E data;
    int value;
    Element<E> next;

    Element(E d, int v) {
        data = d;
        value = v;
    }
}

class OrderedList<E> {
    Element<E> head;

    void insert(E d, int v) {
        Element<E> n = new Element<>(d, v);

        if (head == null || v < head.value) {
            n.next = head;
            head = n;
            return;
        }

        Element<E> aux = head;
        while (aux.next != null && aux.next.value < v) {
            aux = aux.next;
        }

        n.next = aux.next;
        aux.next = n;
    }

    E removeFirst() {
        if (head == null) return null;
        E d = head.data;
        head = head.next;
        return d;
    }

    boolean isEmpty() {
        return head == null;
    }
}

public class PriorityQueueHybrid<E> {

    private OrderedList<E>[] queues;
    private int levels;

    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        queues = new OrderedList[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new OrderedList<>();
        }
    }

    public void enqueue(E x, int p, int v) {
        queues[p].insert(x, v);
    }

    public E dequeue() {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].removeFirst();
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