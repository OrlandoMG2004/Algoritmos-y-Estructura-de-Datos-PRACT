package ejercicios.ej1;

// Tabla hash de tamano 11 (primo) con h(x) = x % 11.
// El arreglo se inicializa en -1 para marcar las posiciones vacias.
public class Main {
    static final int M = 11;

    static int hash(int x) {
        return x % M;
    }

    public static void main(String[] args) {
        int[] tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        int[] valores = {3, 14, 25, 36, 47, 58};

        System.out.println("=== Direcciones hash calculadas ===");
        for (int v : valores) {
            System.out.println("h(" + v + ") = " + v + " % 11 = " + hash(v));
        }
        // todas dan 3 porque forman una progresion con paso 11 (mismo residuo).

        System.out.println("\n=== Insercion ===");
        for (int v : valores) {
            int pos = hash(v);
            int saltos = 0;
            // sondeo lineal por si la posicion ya esta ocupada
            while (tabla[pos] != -1) {
                pos = (pos + 1) % M;
                saltos++;
            }
            tabla[pos] = v;
            if (saltos == 0)
                System.out.println("Insertado " + v + " en indice " + pos);
            else
                System.out.println("Insertado " + v + " en indice " + pos + " (colision, " + saltos + " salto(s))");
        }

        System.out.println("\n=== Tabla hash final ===");
        mostrar(tabla);

        System.out.print("\nPosiciones vacias: ");
        StringBuilder vacias = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (tabla[i] == -1) {
                if (vacias.length() > 0) vacias.append(", ");
                vacias.append(i);
            }
        }
        System.out.println(vacias);

        System.out.println("\n=== Por que el tamano debe ser primo ===");
        System.out.println("Un tamano primo reparte mejor las claves cuando estas comparten");
        System.out.println("factores comunes con M. Si M fuera compuesto (ej. 12), claves que");
        System.out.println("son multiplos de sus factores se concentrarian en pocas posiciones.");
        System.out.println("Aun asi, con un primo no se evitan TODAS las colisiones: aqui las");
        System.out.println("6 claves son una progresion de paso 11, por lo que todas caen en el");
        System.out.println("indice 3 y hay que resolver las colisiones con sondeo.");
    }

    static void mostrar(int[] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            System.out.println("  [" + i + "] -> " + (tabla[i] == -1 ? "vacio" : tabla[i]));
        }
    }
}
