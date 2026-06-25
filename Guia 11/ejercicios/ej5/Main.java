package ejercicios.ej5;

// Tabla hash cerrada con sondeo lineal que mide el factor de carga
// y hace rehashing cuando supera 0.75.
public class Main {
    static int M = 7;
    static int n = 0;
    static int[] tabla;

    static final double UMBRAL = 0.75;

    public static void main(String[] args) {
        tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        int[] valores = {2, 9, 16, 23, 4, 11};

        for (int v : valores) {
            insertar(v);
            double carga = (double) n / M;
            System.out.printf("Insertado %2d -> factor de carga = %d/%d = %.3f%n", v, n, M, carga);

            if (carga > UMBRAL) {
                System.out.println("\n  >> El factor de carga supero 0.75");
                System.out.println("  === Tabla ANTES del rehashing (M=" + M + ") ===");
                mostrar();
                rehash(17);
                System.out.println("  === Tabla DESPUES del rehashing (M=" + M + ") ===");
                mostrar();
                double nuevaCarga = (double) n / M;
                System.out.printf("  Nuevo factor de carga = %d/%d = %.3f%n", n, M, nuevaCarga);
            }
        }

        System.out.println("\n=== Por que cambian las posiciones ===");
        System.out.println("El indice depende de h(x) = x % M. Al cambiar M de 7 a 17, el residuo");
        System.out.println("de cada clave cambia, por lo que casi todos los elementos terminan");
        System.out.println("en otra posicion. Por eso el rehashing recoloca (reinsertar) todo.");
    }

    static void insertar(int key) {
        int pos = key % M;
        while (tabla[pos] != -1) {
            pos = (pos + 1) % M;
        }
        tabla[pos] = key;
        n++;
    }

    static void rehash(int nuevoTam) {
        int[] viejos = tabla;
        int viejoM = M;

        M = nuevoTam;
        tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;
        n = 0;

        // reinsertar todos los elementos existentes en la nueva tabla
        for (int i = 0; i < viejoM; i++) {
            if (viejos[i] != -1) insertar(viejos[i]);
        }
    }

    static void mostrar() {
        for (int i = 0; i < M; i++) {
            System.out.println("    [" + i + "] -> " + (tabla[i] == -1 ? "vacio" : tabla[i]));
        }
    }
}
