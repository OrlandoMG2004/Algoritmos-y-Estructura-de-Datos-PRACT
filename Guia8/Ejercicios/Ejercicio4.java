// Ejercicio 4: recorrido por amplitud recursivo
public class Ejercicio4 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 4 - Recorrido por amplitud recursivo");
        AVL<Integer> a = new AVL<Integer>("Niveles A");
        AVL<Integer> b = new AVL<Integer>("Niveles B");
        Integer[] datosA = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        Integer[] datosB = {30, 10, 40, 5, 20, 35, 50, 45};

        for (int i = 0; i < datosA.length; i++) {
            a.insert(datosA[i]);
        }
        for (int i = 0; i < datosB.length; i++) {
            b.insert(datosB[i]);
        }

        System.out.println("Arbol A por niveles: " + a.levelOrderRecursive());
        System.out.println("Arbol A inorden:     " + a.inOrder());
        System.out.println("Arbol A preorden:    " + a.preOrder());
        System.out.println("Arbol B por niveles: " + b.levelOrderRecursive());
        System.out.println("Arbol B inorden:     " + b.inOrder());
    }
}
