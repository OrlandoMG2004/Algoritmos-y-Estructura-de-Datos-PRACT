package ejercicios.ej3;

// Registro con clave entera y un dato generico de tipo T.
public class Register<T> {
    private int key;
    private T data;

    public Register(int key, T data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() { return key; }
    public T getData() { return data; }

    // dos registros son iguales si tienen la misma clave (sirve para remove/buscar)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Register)) return false;
        return this.key == ((Register<?>) o).key;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + data + ")";
    }
}
