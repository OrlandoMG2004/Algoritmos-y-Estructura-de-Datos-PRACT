package GUIA6.Ejercicios.Ejer3;

public class Main {
    public static void main(String[] args) {

        PriorityQueueMulti<String> pq = new PriorityQueueMulti<>(3);

        pq.enqueue("Aasdasda", 0);
        pq.enqueue("Bqweqwe", 2);
        pq.enqueue("Czxczxc", 1);
        pq.enqueue("Ddfgdfg", 2);

        while (!pq.isEmpty()) {
            System.out.println(pq.dequeue());
        }
    }
}