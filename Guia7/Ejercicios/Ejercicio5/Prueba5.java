package Ejercicio5;

import Actividad1.ItemDuplicated;

public class Prueba5 {
    public static void main(String[] args) {

        System.out.println("=== EJERCICIO 5 ===\n");

        LinkedBST5<Integer> inventario = new LinkedBST5<>();

        try {
            // a) Insertar productos (códigos enteros)
            inventario.insert(50); inventario.insert(30); inventario.insert(70);
            inventario.insert(20); inventario.insert(40); inventario.insert(60);
            inventario.insert(80); inventario.insert(10); inventario.insert(35);
            inventario.insert(45);
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        inventario.inOrder();
        System.out.println();

        // isValidBST
        System.out.println("isValidBST() = " + inventario.isValidBST()); // true

        // b) searchRange
        System.out.println();
        System.out.print("b) searchRange(25, 55): ");
        Object[] rango = inventario.searchRange(25, 55);
        for (Object o : rango) System.out.print(o + " ");
        System.out.println();

        // c) countLeaves
        System.out.println("c) countLeaves() = " + inventario.countLeaves());

        // d) printDescending
        System.out.print("d) ");
        inventario.printDescending();

        // Verificar isValidBST en árbol inválido (forzado manualmente)
        System.out.println("\n--- Árbol válido con valor fuera de orden ---");
        LinkedBST5<Integer> arbol2 = new LinkedBST5<>();
        try {
            arbol2.insert(10); arbol2.insert(5); arbol2.insert(15);
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }
        System.out.println("isValidBST(árbol correcto) = " + arbol2.isValidBST()); // true
    }
}