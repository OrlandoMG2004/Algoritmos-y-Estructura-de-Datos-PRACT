// Actividad 3.4: metodos rotateSR() y rotateSL()
public class Actividad3_4 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 3.4 - ROTACIONES SIMPLES rotateSR() Y rotateSL()");

        AVLTree<Integer> derecha = new AVLTree<Integer>();
        Integer[] casoRSR = {30, 20, 10};
        for (int i = 0; i < casoRSR.length; i++) {
            derecha.insert(casoRSR[i]);
        }
        System.out.println("\nRotacion simple derecha, RSR:");
        derecha.printTree();
        derecha.printRotationTable();

        AVLTree<Integer> izquierda = new AVLTree<Integer>();
        Integer[] casoRSL = {10, 20, 30};
        for (int i = 0; i < casoRSL.length; i++) {
            izquierda.insert(casoRSL[i]);
        }
        System.out.println("\nRotacion simple izquierda, RSL:");
        izquierda.printTree();
        izquierda.printRotationTable();
    }
}
