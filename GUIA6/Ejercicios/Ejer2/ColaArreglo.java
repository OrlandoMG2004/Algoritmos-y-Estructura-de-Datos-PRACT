package GUIA6.Ejercicios.Ejer2;

public class ColaArreglo {

    private int[] arr;
    private int front, rear, size;

    public ColaArreglo(int n) {
        arr = new int[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        int d = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return d;
    }

    public int front() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        return arr[front];
    }
}