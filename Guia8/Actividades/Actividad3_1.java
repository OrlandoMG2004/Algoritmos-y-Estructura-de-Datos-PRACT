// Actividad 3.1: creacion de AVLTree y NodeAVL con herencia desde BSTree
public class Actividad3_1 {
    public static void main(String[] args) {
        System.out.println("ACTIVIDAD 3.1 - CREACION DE AVLTree Y NodeAVL");
        System.out.println("AVLTree<E> extiende de BSTree<E>.");
        System.out.println("NodeAVL<E> extiende de Node<E> y agrega bf y height.");
        System.out.println("Los atributos data, left y right son protected en Node.");

        AVLTree<Integer> avl = new AVLTree<Integer>();
        Integer[] datos = {40, 20, 60, 10, 30, 50, 70};

        for (int i = 0; i < datos.length; i++) {
            avl.insert(datos[i]);
        }

        System.out.println("\nArbol AVL construido:");
        avl.printTree();
        System.out.println("Inorden heredado desde BSTree: " + avl.inOrder());
        System.out.println("Busqueda heredada de 50: " + avl.contains(50));
        System.out.println("Busqueda heredada de 99: " + avl.contains(99));
    }
}
