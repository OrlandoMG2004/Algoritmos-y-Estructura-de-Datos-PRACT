package Actividad1;

public class Prueba {
    public static void main(String[] args) {

        LinkedBST<Integer> arbol = new LinkedBST<>();

        System.out.println("=== ACTIVIDADES - BST ===\n");

        // INSERT
        try {
            arbol.insert(400); arbol.insert(100); arbol.insert(700);
            arbol.insert(50);  arbol.insert(200); arbol.insert(75);
            System.out.println("Insertados: 400, 100, 700, 50, 200, 75");
        } catch (ItemDuplicated e) { System.out.println(e.getMessage()); }

        // Duplicado
        try   { arbol.insert(100); }
        catch (ItemDuplicated e) { System.out.println("Duplicado OK -> " + e.getMessage()); }

        // Traversals
        System.out.println();
        arbol.inOrder();
        arbol.preOrder();
        arbol.postOrder();

        // Search
        System.out.println();
        try   { System.out.println("Search 200 -> " + arbol.search(200)); }
        catch (ItemNoFound e) { System.out.println(e.getMessage()); }
        try   { arbol.search(999); }
        catch (ItemNoFound e) { System.out.println("No encontrado OK -> " + e.getMessage()); }

        // Min / Max
        System.out.println();
        try {
            System.out.println("Min: " + arbol.findMin());
            System.out.println("Max: " + arbol.findMax());
        } catch (ItemNoFound e) { System.out.println(e.getMessage()); }

        // Delete
        System.out.println();
        try {
            arbol.delete(100);
            System.out.print("Tras delete(100) -> "); arbol.inOrder();
            arbol.delete(700);
            System.out.print("Tras delete(700) -> "); arbol.inOrder();
            arbol.delete(999); // no existe
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println("No existe OK -> " + e.getMessage());
        }

        // toString
        System.out.println();
        System.out.println(arbol);
    }
}
