package GUIA6.Ejercicios.Ejer2;

public class Main {
    public static void main(String[] args) {

        ColaArreglo c = new ColaArreglo(5);

        c.enqueue(101);
        c.enqueue(102);
        c.enqueue(103);
        c.enqueue(104);
        c.enqueue(105);

        c.enqueue(106);

        System.out.println("Atendiendo cliente: " + c.dequeue());
        System.out.println("Atendiendo cliente: " + c.dequeue());

        System.out.println("Cliente en frente: " + c.front());

        c.enqueue(106);
        c.enqueue(107);

        while (!c.isEmpty()) {
            System.out.println("Atendiendo cliente: " + c.dequeue());
        }

        c.dequeue();
    }
}