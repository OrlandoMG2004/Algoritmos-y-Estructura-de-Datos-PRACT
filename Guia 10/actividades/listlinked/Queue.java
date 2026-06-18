package actividades.listlinked;

// Cola (FIFO), la usamos sobre todo para el BFS
public class Queue<E> {
    private Node<E> front;
    private Node<E> rear;
    private int size;

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }

    public void enqueue(E data) {
        Node<E> nuevo = new Node<>(data);
        if (rear == null) {
            front = nuevo;
            rear = nuevo;
        } else {
            rear.setNext(nuevo);
            rear = nuevo;
        }
        size++;
    }

    public E dequeue() {
        if (front == null) return null;
        E data = front.getData();
        front = front.getNext();
        if (front == null) rear = null;
        size--;
        return data;
    }
}
