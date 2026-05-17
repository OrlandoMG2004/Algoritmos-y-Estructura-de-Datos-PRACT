// Ejercicio adicional 1: codigos de productos en almacen
public class Adicional1 {
    public static void main(String[] args) {
        System.out.println("ADICIONAL 1 - Codigos de productos");
        AVL<Integer> productos = new AVL<Integer>("Productos");
        Integer[] insertar = {300, 100, 200, 400, 500, 250};
        Integer[] retirar = {100, 400};

        for (int i = 0; i < insertar.length; i++) {
            productos.insert(insertar[i]);
        }
        for (int i = 0; i < retirar.length; i++) {
            productos.delete(retirar[i]);
        }

        productos.printTree();
        System.out.println("Buscar 250: " + productos.contains(250));
        System.out.println("Buscar 999: " + productos.contains(999));
        System.out.println("Inorden ordena codigos porque visita izquierda, raiz y derecha: " + productos.inOrder());
        productos.printRotationLog();
    }
}
