public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca(4);

        biblioteca.cargarDesdeArchivo("biblioteca.txt");

        System.out.println("\nMOSTRAR LIBROS:");
        biblioteca.mostrarLibros();

        System.out.println("\nBUSCAR LIBRO:");
        biblioteca.buscarPorISBN("9780132350884");

        System.out.println("\nELIMINAR LIBRO:");
        biblioteca.eliminarLibro("9780201633610");

        System.out.println("\nLIBROS DESPUÉS DE ELIMINAR:");
        biblioteca.mostrarLibros();
    }
}
