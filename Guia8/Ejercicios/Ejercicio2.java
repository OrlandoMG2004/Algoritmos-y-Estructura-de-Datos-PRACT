// Ejercicio 2: comparacion entre BST y AVL
public class Ejercicio2 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 2 - Comparacion entre BST y AVL");
        Integer[][] casos = {
            {10, 20, 30, 40, 50, 60, 70},
            {40, 20, 60, 10, 30, 50, 70, 5, 1}
        };

        for (int i = 0; i < casos.length; i++) {
            BST<Integer> bst = new BST<Integer>();
            AVL<Integer> avl = new AVL<Integer>("AVL caso " + (i + 1));

            for (int j = 0; j < casos[i].length; j++) {
                bst.insert(casos[i][j]);
                avl.insert(casos[i][j]);
            }

            System.out.println("\nCaso " + (i + 1));
            System.out.println("BST inorden: " + bst.inOrder());
            System.out.println("AVL inorden: " + avl.inOrder());
            System.out.println("Altura BST: " + bst.height());
            System.out.println("Altura AVL: " + avl.height());
            System.out.println("Buscar 50 en BST: " + bst.contains(50));
            System.out.println("Buscar 50 en AVL: " + avl.contains(50));
        }
    }
}
