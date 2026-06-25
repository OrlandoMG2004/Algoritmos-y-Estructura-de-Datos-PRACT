package ejercicios.ej2;

// Tabla hash cerrada de tamano 7 con h(x) = x % 7.
// Se insertan los mismos valores con sondeo lineal y con sondeo cuadratico
// para comparar cuantas posiciones se exploran ante las colisiones.
public class Main {
    static final int M = 7;

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("================ SONDEO LINEAL ================");
        sondeoLineal(valores);

        System.out.println("\n============== SONDEO CUADRATICO ==============");
        sondeoCuadratico(valores);

        System.out.println("\n=== Conclusion ===");
        System.out.println("El sondeo lineal genera agrupamiento primario: las claves que");
        System.out.println("colisionan se acumulan en posiciones contiguas, asi cada nueva");
        System.out.println("insercion explora mas celdas seguidas. El sondeo cuadratico");
        System.out.println("dispersa los reintentos (1, 4, 9, ...) y rompe ese agrupamiento,");
        System.out.println("por lo que suele hacer menos saltos ante colisiones consecutivas.");
    }

    static void sondeoLineal(int[] valores) {
        int[] tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        for (int v : valores) {
            int base = v % M;
            int pos = base;
            int exploradas = 1;
            while (tabla[pos] != -1) {
                pos = (pos + 1) % M;
                exploradas++;
            }
            tabla[pos] = v;
            System.out.println("\nInsertar " + v + " (h=" + base + ") -> indice " + pos +
                    " | celdas exploradas: " + exploradas);
            mostrar(tabla);
        }
    }

    static void sondeoCuadratico(int[] valores) {
        int[] tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        for (int v : valores) {
            int base = v % M;
            int pos = base;
            int i = 0;
            int exploradas = 1;
            // f(i) = i^2 : posicion = (base + i^2) % M
            while (tabla[pos] != -1) {
                i++;
                pos = (base + i * i) % M;
                exploradas++;
            }
            tabla[pos] = v;
            System.out.println("\nInsertar " + v + " (h=" + base + ") -> indice " + pos +
                    " | celdas exploradas: " + exploradas);
            mostrar(tabla);
        }
    }

    static void mostrar(int[] tabla) {
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < tabla.length; i++) {
            sb.append("[").append(i).append(":")
              .append(tabla[i] == -1 ? "_" : tabla[i]).append("] ");
        }
        System.out.println(sb.toString().trim());
    }
}
