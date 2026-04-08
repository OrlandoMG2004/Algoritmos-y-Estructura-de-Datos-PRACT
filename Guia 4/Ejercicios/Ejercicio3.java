import java.io.BufferedInputStream;
import java.io.IOException;

public class Ejercicio3 {

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();

        int n = in.nextInt(); // número de embarcaderos

        int[][] tarifa = new int[n][n];

        // Se lee la matriz completa.
        // Puedes poner 0 en diagonal y debajo de la diagonal.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tarifa[i][j] = in.nextInt();
            }
        }

        int[][] costo = new int[n][n];

        for (int i = 0; i < n; i++) {
            costo[i][i] = 0;
        }

        for (int longitud = 2; longitud <= n; longitud++) {
            for (int i = 0; i + longitud - 1 < n; i++) {
                int j = i + longitud - 1;

                costo[i][j] = tarifa[i][j];

                for (int k = i + 1; k < j; k++) {
                    int posible = tarifa[i][k] + costo[k][j];
                    if (posible < costo[i][j]) {
                        costo[i][j] = posible;
                    }
                }
            }
        }

        // costo mínimo desde el embarcadero 0 hasta el n-1
        System.out.println(costo[0][n - 1]);
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