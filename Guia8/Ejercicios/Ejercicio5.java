// Ejercicio 5: BFS recursivo con el orden solicitado
public class Ejercicio5 {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 5 - BFS recursivo con orden esperado");
        AVL<Integer> avl = new AVL<Integer>("BFS esperado");
        Integer[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};

        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }

        avl.printTree();
        System.out.println("Por niveles: " + avl.levelOrderRecursive());
        System.out.println("Esperado:    50 30 70 20 40 60 80 10 25 65");
    }
}
