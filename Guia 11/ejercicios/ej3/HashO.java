package ejercicios.ej3;

// Tabla hash abierta (encadenamiento). Cada celda es una LinkedList propia
// que guarda los Register que colisionan en el mismo indice.
public class HashO<T> {
    private LinkedList<Register<T>>[] tabla;
    private int M;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.M = size;
        tabla = new LinkedList[M];
        for (int i = 0; i < M; i++) tabla[i] = new LinkedList<>();
    }

    private int hash(int key) {
        return key % M;
    }

    public void insert(int key, T data) {
        int pos = hash(key);
        tabla[pos].add(new Register<>(key, data));
    }

    // busca la clave y devuelve su dato, o null si no esta
    public T search(int key) {
        int pos = hash(key);
        LinkedList<Register<T>> lista = tabla[pos];
        for (int i = 0; i < lista.size(); i++) {
            Register<T> r = lista.get(i);
            if (r.getKey() == key) return r.getData();
        }
        return null;
    }

    public int indexOf(int key) {
        return hash(key);
    }

    // posicion del nodo dentro de la lista (0-based), o -1 si no esta
    public int nodeOf(int key) {
        int pos = hash(key);
        LinkedList<Register<T>> lista = tabla[pos];
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getKey() == key) return i;
        }
        return -1;
    }

    public boolean delete(int key) {
        int pos = hash(key);
        return tabla[pos].remove(new Register<>(key, null));
    }

    public int chainSize(int index) {
        return tabla[index].size();
    }

    public void print() {
        for (int i = 0; i < M; i++) {
            System.out.println("  [" + i + "] -> " + tabla[i].toString());
        }
    }
}
