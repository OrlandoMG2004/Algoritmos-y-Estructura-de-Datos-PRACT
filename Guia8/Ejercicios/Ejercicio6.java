// Ejercicio 6: recorrido preorden en un arbol AVL
public class Ejercicio6 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 6 - Preorden en AVL");
        Integer[][] casos = {
            {30, 20, 10, 25, 40, 50},
            {10, 30, 20, 5, 7, 6},
            {60, 40, 80, 30, 50, 70, 90}
        };

        for (int i = 0; i < casos.length; i++) {
            AVL<Integer> avl = new AVL<Integer>("Preorden " + (i + 1));
            for (int j = 0; j < casos[i].length; j++) {
                avl.insert(casos[i][j]);
            }
            System.out.println("Caso " + (i + 1) + " preorden: " + avl.preOrder());
            System.out.println("Caso " + (i + 1) + " inorden:  " + avl.inOrder());
        }
    }
}
