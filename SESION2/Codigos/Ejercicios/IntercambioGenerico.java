package Ejercicios;

public class IntercambioGenerico {

    // Método genérico para intercambiar dos elementos de un arreglo
    public static <T> void swap(T[] arreglo, int i, int j) {
        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }

        if (i < 0 || j < 0 || i >= arreglo.length || j >= arreglo.length) {
            throw new IndexOutOfBoundsException("Índices fuera de rango");
        }

        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    // Método para mostrar arreglos
    public static <T> void mostrarArreglo(T[] arreglo) {
        for (T elemento : arreglo) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Ejemplo con String
        String[] letras = {"A", "B", "C", "D"};
        System.out.println("Arreglo String original:");
        mostrarArreglo(letras);

        swap(letras, 1, 3);

        System.out.println("Arreglo String después del intercambio:");
        mostrarArreglo(letras);

        // Ejemplo con Integer
        Integer[] numeros = {10, 20, 30, 40};
        System.out.println("Arreglo Integer original:");
        mostrarArreglo(numeros);

        swap(numeros, 0, 2);

        System.out.println("Arreglo Integer después del intercambio:");
        mostrarArreglo(numeros);
    }
}