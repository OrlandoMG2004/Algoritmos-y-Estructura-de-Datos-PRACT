// Actividad 3.2 - Prueba de la insercion (sin visualizacion, eso es 3.3)
public class Main {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(4);
        int[] datos = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};

        for (int d : datos) {
            arbol.insert(d);
        }
        System.out.println("Se insertaron " + datos.length + " claves. Arbol vacio? " + arbol.isEmpty());

        System.out.println("\nProbando insercion de un duplicado (50):");
        arbol.insert(50);
    }
}
