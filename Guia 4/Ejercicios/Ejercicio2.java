import java.io.BufferedInputStream;
import java.io.IOException;

public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();

        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        int resultado = kthLargest(arr, k);
        System.out.println(resultado);
    }

    static int kthLargest(int[] arr, int k) {
        int targetIndex = arr.length - k; // convertir a k-ésimo mayor
        return quickSelect(arr, 0, arr.length - 1, targetIndex);
    }

    static int quickSelect(int[] arr, int left, int right, int targetIndex) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);

        if (pivotIndex == targetIndex) {
            return arr[pivotIndex];
        } else if (targetIndex < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, targetIndex);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, targetIndex);
        }
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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