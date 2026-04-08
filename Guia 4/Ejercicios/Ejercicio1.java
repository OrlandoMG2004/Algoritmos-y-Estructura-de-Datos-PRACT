import java.io.BufferedInputStream;
import java.io.IOException;

public class Ejercicio1 {

    static int n;
    static int[] arr;
    static int objetivo;

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();

        n = in.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        objetivo = in.nextInt();

        boolean resultado = backtrack(0, 0, false);
        System.out.println(resultado ? "true" : "false");
    }

    static boolean backtrack(int idx, int sumaActual, boolean anteriorElegidoYPar) {
        if (idx == n) {
            return sumaActual == objetivo;
        }

        int valor = arr[idx];
        boolean esMultiploDe3 = (valor % 3 == 0);
        boolean esPar = (valor % 2 == 0);

        if (esMultiploDe3) {
            if (esPar && anteriorElegidoYPar) {
                return false;
            }
            return backtrack(idx + 1, sumaActual + valor, esPar);
        }

        if (backtrack(idx + 1, sumaActual, false)) {
            return true;
        }

        if (!(esPar && anteriorElegidoYPar)) {
            if (backtrack(idx + 1, sumaActual + valor, esPar)) {
                return true;
            }
        }

        return false;
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

            int signo = 1;
            if (c == '-') {
                signo = -1;
                c = read();
            }

            int num = 0;
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
            return num * signo;
        }
    }
}