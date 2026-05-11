package Ejercicio3;

import Actividad1.ItemDuplicated;

public class Prueba3 {

    // 3c) sameArea() — compara área de dos árboles distintos ──────────────────
    public static boolean sameArea(LinkedBST3<?> a, LinkedBST3<?> b) {
        return a.areaBST() == b.areaBST();
    }

    public static void main(String[] args) {

        System.out.println("=== EJERCICIO 3 ===\n");

        LinkedBST3<Integer> arbol1 = new LinkedBST3<>();
        LinkedBST3<Integer> arbol2 = new LinkedBST3<>();
        LinkedBST3<Integer> arbol3 = new LinkedBST3<>();

        try {
            // Árbol 1: 15, 8, 22, 5, 12, 18, 30
            arbol1.insert(15); arbol1.insert(8);  arbol1.insert(22);
            arbol1.insert(5);  arbol1.insert(12); arbol1.insert(18);
            arbol1.insert(30);

            // Árbol 2: misma área diferente estructura
            arbol2.insert(10); arbol2.insert(5); arbol2.insert(20);
            arbol2.insert(1);  arbol2.insert(7); arbol2.insert(15);
            arbol2.insert(25);

            // Árbol 3: diferente área
            arbol3.insert(50); arbol3.insert(30); arbol3.insert(70);

        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        // 3a) areaBST
        System.out.println("3a) areaBST(arbol1) = " + arbol1.areaBST());
        System.out.println("    areaBST(arbol2) = " + arbol2.areaBST());
        System.out.println("    areaBST(arbol3) = " + arbol3.areaBST());

        // 3b) drawBST
        System.out.println();
        System.out.print("3b) "); arbol1.drawBST();

        // 3c) sameArea
        System.out.println();
        System.out.println("3c) sameArea(arbol1, arbol2) = " + sameArea(arbol1, arbol2));
        System.out.println("    sameArea(arbol1, arbol3) = " + sameArea(arbol1, arbol3));
    }
}