package ejercicios.ej4;

// Celda de la tabla hash con tres estados posibles.
public class Entry {
    static final int EMPTY = 0;
    static final int OCCUPIED = 1;
    static final int DELETED = 2;

    int key;
    int status;

    public Entry() {
        this.status = EMPTY;
    }

    public String estadoTexto() {
        switch (status) {
            case OCCUPIED: return "OCCUPIED(" + key + ")";
            case DELETED:  return "DELETED";
            default:       return "EMPTY";
        }
    }
}
