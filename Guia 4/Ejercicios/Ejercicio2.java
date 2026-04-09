import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linea = br.readLine();

        if (linea == null || linea.trim().isEmpty()) {
            return;
        }

        linea = linea.trim();

        int posCierre = linea.indexOf(']');
        String parteArreglo = linea.substring(1, posCierre);
        String parteK = linea.substring(posCierre + 1).replace(",", "").trim();

        String[] partes = parteArreglo.split(",");
        int[] arr = new int[partes.length];

        for (int i = 0; i < partes.length; i++) {
            arr[i] = Integer.parseInt(partes[i].trim());
        }

        int k = Integer.parseInt(parteK);

        int resultado = kthLargest(arr, k);
        System.out.println(resultado);
    }

    static int kthLargest(int[] arr, int k) {
        int targetIndex = arr.length - k;
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
}