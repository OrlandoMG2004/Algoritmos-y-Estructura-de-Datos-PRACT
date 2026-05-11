package Ejercicio5;

import Ejercicio4.LinkedBST4;

public class LinkedBST5<E extends Comparable<E>> extends LinkedBST4<E> {

    // 5 - Verificación BST ─────────────────────────────────────────────────────
    // isValidBST(): DFS con bounds min/max, sin reconstruir
    public boolean isValidBST() {
        return isValidRec(getRoot(), null, null);
    }

    private boolean isValidRec(Node node, E min, E max) {
        if (node == null) return true;
        if (min != null && node.data.compareTo(min) <= 0) return false;
        if (max != null && node.data.compareTo(max) >= 0) return false;
        return isValidRec(node.left,  min, node.data)
            && isValidRec(node.right, node.data, max);
    }

    // Caso aplicado — searchRange(min, max) ────────────────────────────────────
    // Retorna arreglo propio sin java.util
    public Object[] searchRange(E min, E max) {
        int total = countAllNodes();
        Object[] temp = new Object[total];
        int[] idx = {0}; // contador compartido con lambda no disponible → uso arreglo
        searchRangeRec(getRoot(), min, max, temp, idx);

        // Copia al tamaño exacto
        Object[] result = new Object[idx[0]];
        for (int i = 0; i < idx[0]; i++) result[i] = temp[i];
        return result;
    }

    private void searchRangeRec(Node node, E min, E max, Object[] arr, int[] idx) {
        if (node == null) return;
        // Si el nodo es mayor que min, puede haber resultados en la izquierda
        if (node.data.compareTo(min) > 0)
            searchRangeRec(node.left, min, max, arr, idx);
        // Agrega si está dentro del rango
        if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0)
            arr[idx[0]++] = node.data;
        // Si el nodo es menor que max, puede haber resultados en la derecha
        if (node.data.compareTo(max) < 0)
            searchRangeRec(node.right, min, max, arr, idx);
    }

    // Caso aplicado — countLeaves() ────────────────────────────────────────────
    public int countLeaves() {
        return countLeavesRec(getRoot());
    }

    private int countLeavesRec(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // Caso aplicado — printDescending() derecha→raíz→izquierda ────────────────
    public void printDescending() {
        System.out.print("Descendente: ");
        printDescendingRec(getRoot());
        System.out.println();
    }

    private void printDescendingRec(Node node) {
        if (node == null) return;
        printDescendingRec(node.right);
        System.out.print(node.data + " ");
        printDescendingRec(node.left);
    }
}