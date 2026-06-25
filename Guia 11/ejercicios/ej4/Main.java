package ejercicios.ej4;

// Tabla hash cerrada de tamano 7 con sondeo lineal y eliminacion logica.
// Cada celda usa los estados EMPTY, OCCUPIED y DELETED.
public class Main {
    static final int M = 7;
    static Entry[] tabla = new Entry[M];

    public static void main(String[] args) {
        for (int i = 0; i < M; i++) tabla[i] = new Entry();

        System.out.println("=== Insercion de 5, 12, 19, 26 (h(x) = x % 7) ===");
        insertar(5);
        insertar(12);
        insertar(19);
        insertar(26);
        mostrar();

        System.out.println("\n=== Eliminacion logica de la clave 12 ===");
        eliminar(12);
        mostrar();
        System.out.println("La celda quedo marcada como DELETED, no como EMPTY.");

        System.out.println("\n=== Buscar la clave 19 despues de eliminar 12 ===");
        int pos = buscar(19);
        System.out.println("Clave 19 encontrada en el indice " + pos + ".");
        System.out.println("La celda DELETED no detiene el sondeo: si parara, no llegariamos");
        System.out.println("a 19, que se habia insertado mas adelante por una colision previa.");

        System.out.println("\n=== Reinsertar la clave 33 ===");
        insertar(33);
        mostrar();
        System.out.println("La clave 33 reutilizo la celda DELETED que dejo el 12.");

        System.out.println("\n=== Eliminacion logica vs fisica ===");
        System.out.println("Logica: se marca la celda (DELETED) sin borrar fisicamente, asi el");
        System.out.println("sondeo de otras claves no se rompe. Conviene en hash cerrado.");
        System.out.println("Fisica: se libera la celda (EMPTY). Es mas simple pero en sondeo");
        System.out.println("abierto puede dejar claves 'inalcanzables'. Conviene en hash abierto.");
    }

    static int hash(int x) {
        return x % M;
    }

    static void insertar(int key) {
        int pos = hash(key);
        int primerBorrado = -1;
        for (int i = 0; i < M; i++) {
            int idx = (pos + i) % M;
            if (tabla[idx].status == Entry.EMPTY) {
                // si vimos antes una celda DELETED, la reutilizamos
                int destino = (primerBorrado != -1) ? primerBorrado : idx;
                tabla[destino].key = key;
                tabla[destino].status = Entry.OCCUPIED;
                System.out.println("Insertado " + key + " (h=" + pos + ") en indice " + destino);
                return;
            }
            if (tabla[idx].status == Entry.DELETED && primerBorrado == -1) {
                primerBorrado = idx;
            }
            if (tabla[idx].status == Entry.OCCUPIED && tabla[idx].key == key) {
                return; // ya existe
            }
        }
        if (primerBorrado != -1) {
            tabla[primerBorrado].key = key;
            tabla[primerBorrado].status = Entry.OCCUPIED;
            System.out.println("Insertado " + key + " (h=" + pos + ") en indice " + primerBorrado);
        } else {
            System.out.println("Tabla llena, no se pudo insertar " + key);
        }
    }

    static int buscar(int key) {
        int pos = hash(key);
        for (int i = 0; i < M; i++) {
            int idx = (pos + i) % M;
            if (tabla[idx].status == Entry.EMPTY) return -1; // solo EMPTY detiene la busqueda
            if (tabla[idx].status == Entry.OCCUPIED && tabla[idx].key == key) return idx;
            // DELETED -> seguimos buscando
        }
        return -1;
    }

    static void eliminar(int key) {
        int idx = buscar(key);
        if (idx != -1) {
            tabla[idx].status = Entry.DELETED;
            System.out.println("Clave " + key + " eliminada del indice " + idx);
        } else {
            System.out.println("Clave " + key + " no encontrada");
        }
    }

    static void mostrar() {
        for (int i = 0; i < M; i++) {
            System.out.println("  [" + i + "] -> " + tabla[i].estadoTexto());
        }
    }
}
