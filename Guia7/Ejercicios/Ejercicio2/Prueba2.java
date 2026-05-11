package Ejercicio2;

import Actividad1.ItemDuplicated;
import Actividad1.ItemNoFound;
import Actividad1.ExceptionIsEmpty;

public class Prueba2 {
    public static void main(String[] args) {

        System.out.println("=== EJERCICIO 2 === (15, 8, 22, 5, 12, 18, 30)\n");

        LinkedBST2<Integer> arbol = new LinkedBST2<>();

        try {
            arbol.insert(15); arbol.insert(8);  arbol.insert(22);
            arbol.insert(5);  arbol.insert(12); arbol.insert(18);
            arbol.insert(30);
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        arbol.inOrder();
        System.out.println();

        // 2b) countAllNodes
        System.out.println("2b) countAllNodes() = " + arbol.countAllNodes()); // 7

        // 2c) countNodes (no-hoja)
        System.out.println("2c) countNodes()    = " + arbol.countNodes());    // 3

        // 2d) height(x)
        System.out.println();
        System.out.println("2d) height(15) raiz    = " + arbol.height(15)); // 2
        System.out.println("    height(8)  subarbol = " + arbol.height(8));  // 1
        System.out.println("    height(5)  hoja     = " + arbol.height(5));  // 0
        System.out.println("    height(99) no existe= " + arbol.height(99)); // -1

        // 2e) amplitude(nivel)
        System.out.println();
        System.out.println("2e) amplitude(0) = " + arbol.amplitude(0)); // 1
        System.out.println("    amplitude(1) = " + arbol.amplitude(1)); // 2
        System.out.println("    amplitude(2) = " + arbol.amplitude(2)); // 4

        // 2a) destroyNodes
        System.out.println();
        try {
            arbol.destroyNodes();
            System.out.println("2a) destroyNodes() OK -> isEmpty: " + arbol.isEmpty());
            arbol.destroyNodes(); // debe lanzar excepcion
        } catch (ExceptionIsEmpty e) {
            System.out.println("    Arbol ya vacio OK -> " + e.getMessage());
        }
    }
}
