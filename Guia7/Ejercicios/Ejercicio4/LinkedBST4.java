package Ejercicio4;

import Ejercicio3.LinkedBST3;

public class LinkedBST4<E extends Comparable<E>> extends LinkedBST3<E> {

    // 4) parenthesize() — representación con paréntesis y sangría ─────────────
    // Ejemplo (figura 7.13):
    //   Sales (
    //     Domestic
    //     International (
    //       Canada
    //       S.America
    //       Overseas (
    //         Africa
    //         Europe
    //         Asia
    //         Australia
    //       )
    //     )
    //   )
    public void parenthesize() {
        if (isEmpty()) {
            System.out.println("Árbol vacío");
            return;
        }
        parenthesizeRec(getRoot(), 0);
    }

    private void parenthesizeRec(Node node, int nivel) {
        if (node == null) return;

        String sangria = "  ".repeat(nivel);
        boolean esHoja = (node.left == null && node.right == null);

        if (esHoja) {
            System.out.println(sangria + node.data);
        } else {
            System.out.println(sangria + node.data + " (");
            parenthesizeRec(node.left,  nivel + 1);
            parenthesizeRec(node.right, nivel + 1);
            System.out.println(sangria + ")");
        }
    }
}