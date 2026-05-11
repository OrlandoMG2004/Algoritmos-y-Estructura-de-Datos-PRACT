package Actividad1;

public class ItemDuplicated extends Exception {
    public ItemDuplicated(String msg) {
        super(msg);
    }
    public ItemDuplicated() {
        super("El dato ya existe en el arbol.");
    }
}
