package Ejercicio3;

import Ejercicio2.LinkedBST2;
import Actividad1.ExceptionIsEmpty;
import Actividad1.ItemNoFound;

public class LinkedBST3<E extends Comparable<E>> extends LinkedBST2<E> {

    // 3a) areaBST() = hojas * altura — iterativo, sin recursividad ─────────────
    public int areaBST() {
        if (isEmpty()) return 0;

        // Contar hojas iterativamente con pila propia (arreglo)
        int totalNodos = countAllNodes();
        Object[] stack = new Object[totalNodos + 1];
        int top = -1;

        stack[++top] = getRoot();
        int hojas = 0;

        while (top >= 0) {
            Node cur = (Node) stack[top--];
            if (cur.left == null && cur.right == null) {
                hojas++;
            } else {
                if (cur.right != null) stack[++top] = cur.right;
                if (cur.left  != null) stack[++top] = cur.left;
            }
        }

        // Altura del árbol = height(raíz)
        int altura = height(getRoot().data);

        return hojas * altura;
    }

    // 3b) drawBST() — imprime usando toString() heredado ──────────────────────
    public void drawBST() {
        System.out.println("Árbol BST: " + toString());
    }
}