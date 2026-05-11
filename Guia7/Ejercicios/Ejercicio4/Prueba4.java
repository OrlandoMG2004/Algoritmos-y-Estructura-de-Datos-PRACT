package Ejercicio4;

import Actividad1.ItemDuplicated;

public class Prueba4 {
    public static void main(String[] args) {

        System.out.println("=== EJERCICIO 4 — parenthesize() ===\n");

        LinkedBST4<String> arbol = new LinkedBST4<>();

        // Árbol similar al de la figura 7.13
        try {
            arbol.insert("Sales");
            arbol.insert("Domestic");
            arbol.insert("International");
            arbol.insert("Canada");
            arbol.insert("Overseas");
            arbol.insert("Africa");
            arbol.insert("Europe");
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        arbol.parenthesize();

        System.out.println("\n--- Árbol numérico ---");
        LinkedBST4<Integer> arbol2 = new LinkedBST4<>();
        try {
            arbol2.insert(15); arbol2.insert(8);  arbol2.insert(22);
            arbol2.insert(5);  arbol2.insert(12); arbol2.insert(18);
            arbol2.insert(30);
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        arbol2.parenthesize();
    }
}