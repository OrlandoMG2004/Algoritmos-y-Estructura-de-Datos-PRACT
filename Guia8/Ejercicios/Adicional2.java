// Ejercicio adicional 2: turnos de atencion en una clinica
public class Adicional2 {
    public static void main(String[] args) {
        System.out.println("ADICIONAL 2 - Turnos en clinica");
        AVL<Integer> turnos = new AVL<Integer>("Clinica");
        Integer[] insertar = {10, 20, 30, 5, 15, 25, 35};

        for (int i = 0; i < insertar.length; i++) {
            turnos.insert(insertar[i]);
        }

        turnos.printTree();
        System.out.println("Raiz despues de insertar 30 en la primera secuencia 10,20,30: 20");
        System.out.println("Buscar en AVL descarta subarboles completos; una lista revisa elemento por elemento.");
        turnos.delete(10);
        System.out.println("Al eliminar turno 10 se aplica el caso BST correspondiente a sus hijos actuales.");
        turnos.printDeleteLog();
    }
}
