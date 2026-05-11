package Actividad1;

public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String msg) {
        super(msg);
    }
    public ExceptionIsEmpty() {
        super("El arbol esta vacio.");
    }
}
