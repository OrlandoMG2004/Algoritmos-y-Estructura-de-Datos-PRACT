package Ejercicios;

import java.util.Random;

// nodo doble con punteros next y prev
class NodeDoble<T> {
    T value;
    NodeDoble<T> next;
    NodeDoble<T> prev;

    public NodeDoble(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class Cancion {
    private String titulo;
    private String artista;
    private int duracionSeg;

    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }

    public int getDuracionSeg() { return duracionSeg; }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSeg + "s)";
    }
}

class ColaReproduccion<T> {
    private NodeDoble<T> head;
    private NodeDoble<T> tail;
    private NodeDoble<T> actual;

    public ColaReproduccion() {
        head = null;
        tail = null;
        actual = null;
    }

    // agrega cancion al final
    public void agregarCancion(T cancion) {
        NodeDoble<T> newNode = new NodeDoble<>(cancion);
        if (head == null) {
            head = newNode;
            tail = newNode;
            actual = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    // avanza al siguiente y lo retorna
    public T reproducirSiguiente() {
        if (actual == null || actual.next == null) {
            System.out.println("  No hay siguiente cancion");
            return null;
        }
        actual = actual.next;
        return actual.value;
    }

    // retrocede al anterior y lo retorna, gracias al puntero prev es O(1)
    public T reproducirAnterior() {
        if (actual == null || actual.prev == null) {
            System.out.println("  No hay cancion anterior");
            return null;
        }
        actual = actual.prev;
        return actual.value;
    }

    // mezcla los nodos usando Fisher-Yates sobre un arreglo auxiliar
    @SuppressWarnings("unchecked")
    public void mezclar() {
        int n = 0;
        NodeDoble<T> current = head;
        while (current != null) { n++; current = current.next; }
        if (n <= 1) return;

        // guardar valores en arreglo
        Object[] valores = new Object[n];
        current = head;
        for (int i = 0; i < n; i++) {
            valores[i] = current.value;
            current = current.next;
        }

        // Fisher-Yates shuffle
        Random rand = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Object temp = valores[i];
            valores[i] = valores[j];
            valores[j] = temp;
        }

        // reasignar valores mezclados a los nodos existentes
        current = head;
        for (int i = 0; i < n; i++) {
            current.value = (T) valores[i];
            current = current.next;
        }

        actual = head;
    }

    // imprime la cola indicando cual es la actual
    public void mostrarCola() {
        NodeDoble<T> current = head;
        int i = 1;
        while (current != null) {
            String marker = (current == actual) ? "► " : "  ";
            System.out.println(marker + i + ". " + current.value);
            current = current.next;
            i++;
        }
    }

    // suma duraciones, solo funciona si T es Cancion
    @SuppressWarnings("unchecked")
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> current = head;
        while (current != null) {
            total += ((Cancion) current.value).getDuracionSeg();
            current = current.next;
        }
        return total;
    }

    public T getActual() {
        return actual != null ? actual.value : null;
    }
}

public class Ejercicio8 {

    public static void main(String[] args) {
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        // agregar 6 canciones
        cola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen",      354));
        cola.agregarCancion(new Cancion("Blinding Lights",   "The Weeknd", 200));
        cola.agregarCancion(new Cancion("Shape of You",      "Ed Sheeran", 234));
        cola.agregarCancion(new Cancion("Hotel California",  "Eagles",     391));
        cola.agregarCancion(new Cancion("Levitating",        "Dua Lipa",   203));
        cola.agregarCancion(new Cancion("Starboy",           "The Weeknd", 230));

        System.out.println("=== Cola de Reproduccion Inicial ===");
        cola.mostrarCola();

        // avanzar 3 canciones
        System.out.println("\n=== Avanzando 3 canciones ===");
        for (int i = 0; i < 3; i++) {
            Cancion siguiente = (Cancion) cola.reproducirSiguiente();
            if (siguiente != null) System.out.println("  ► Reproduciendo: " + siguiente);
        }

        // retroceder 1
        System.out.println("\n=== Retrocediendo 1 cancion ===");
        Cancion anterior = (Cancion) cola.reproducirAnterior();
        if (anterior != null) System.out.println("  ◄ Ahora en: " + anterior);

        // mezclar
        System.out.println("\n=== Mezclando... ===");
        cola.mezclar();
        cola.mostrarCola();

        // duracion total en mm:ss
        int total = cola.duracionTotal();
        System.out.println("\nDuracion total: " + (total / 60) + ":" + String.format("%02d", total % 60));
    }
}