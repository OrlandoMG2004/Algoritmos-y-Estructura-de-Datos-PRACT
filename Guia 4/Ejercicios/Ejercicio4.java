import java.io.BufferedInputStream;
import java.io.IOException;

public class Ejercicio4 {

    static int n;
    static int m;
    static int[][] laberinto;
    static int[][] solucion;

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();

        n = in.nextInt();
        m = in.nextInt();

        laberinto = new int[n][m];
        solucion = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                laberinto[i][j] = in.nextInt();
            }
        }

        boolean existeCamino = resolver(0, 0);

        System.out.println(existeCamino ? "true" : "false");

        if (existeCamino) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j > 0) System.out.print(" ");
                    System.out.print(solucion[i][j]);
                }
                System.out.println();
            }
        }
    }

    static boolean resolver(int fila, int col) {
        if (!esValido(fila, col)) {
            return false;
        }

        if (laberinto[fila][col] == 1 || solucion[fila][col] == 1) {
            return false;
        }

        solucion[fila][col] = 1;

        if (fila == n - 1 && col == m - 1) {
            return true;
        }

        if (resolver(fila - 1, col)) return true; // arriba
        if (resolver(fila + 1, col)) return true; // abajo
        if (resolver(fila, col - 1)) return true; // izquierda
        if (resolver(fila, col + 1)) return true; // derecha

        solucion[fila][col] = 0; // backtracking
        return false;
    }

    static boolean esValido(int fila, int col) {
        return fila >= 0 && fila < n && col >= 0 && col < m;
    }

    static class FastInput {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int num = 0;
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }

            return num * sign;
        }
    }
}