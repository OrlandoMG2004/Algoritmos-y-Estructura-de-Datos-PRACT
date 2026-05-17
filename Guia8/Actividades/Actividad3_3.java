// Actividad 3.3: balanceToLeft y balanceToRight mediante casos de desbalance
public class Actividad3_3 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 3.3 - balanceToLeft() Y balanceToRight()");
        probar("Derecha-Derecha: usa balanceToLeft", new Integer[] {10, 20, 30});
        probar("Derecha-Izquierda: usa balanceToLeft", new Integer[] {10, 30, 20});
        probar("Izquierda-Izquierda: usa balanceToRight", new Integer[] {30, 20, 10});
        probar("Izquierda-Derecha: usa balanceToRight", new Integer[] {30, 10, 20});
    }

    private static void probar(String titulo, Integer[] datos) {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        System.out.println("\n" + titulo);

        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }

        avl.printTree();
        System.out.println("Rotaciones:");
        avl.printRotationTable();
    }
}
