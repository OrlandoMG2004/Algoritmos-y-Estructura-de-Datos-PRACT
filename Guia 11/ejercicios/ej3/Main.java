package ejercicios.ej3;

// Tabla hash abierta de tamano 7 con h(k) = k % 7.
public class Main {
    public static void main(String[] args) {
        HashO<String> hash = new HashO<>(7);

        System.out.println("=== Insercion (clave, nombre) ===");
        insertar(hash, 10, "Juan");
        insertar(hash, 17, "Ana");
        insertar(hash, 24, "Luis");
        insertar(hash, 31, "Rosa");
        insertar(hash, 5, "Pedro");
        insertar(hash, 12, "Carla");

        System.out.println("\n=== Estado final de la tabla ===");
        hash.print();
        System.out.println("\nColisiones: 10,17,24,31 caen en el indice 3 y 5,12 en el indice 5.");

        System.out.println("\n=== Buscar clave 24 ===");
        String nombre = hash.search(24);
        System.out.println("Nombre asociado a 24: " + nombre);
        System.out.println("Esta en el indice " + hash.indexOf(24) +
                " y en el nodo " + hash.nodeOf(24) + " de la lista (0-based).");

        System.out.println("\n=== Eliminar clave 17 ===");
        hash.delete(17);
        hash.print();
        System.out.println("Nodos restantes en la cadena del indice 3: " + hash.chainSize(3));
    }

    static void insertar(HashO<String> hash, int key, String nombre) {
        hash.insert(key, nombre);
        System.out.println("Insertar(" + key + ", \"" + nombre + "\") -> indice " + hash.indexOf(key));
    }
}
