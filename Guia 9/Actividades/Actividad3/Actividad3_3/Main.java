// Actividad 3.3 - Muestra el Arbol B con el formato de la figura 10.14
public class Main {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(4);
        int[] datos = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};

        for (int d : datos) {
            arbol.insert(d);
        }

        System.out.println("Arbol B de orden 4 (formato figura 10.14):\n");
        System.out.println(arbol);
    }
}
