package Actividad1;

public interface BinarySearchTree<E extends Comparable<E>> {

    // Inserta un dato. Lanza excepcion si el dato ya existe.
    void insert(E data) throws ItemDuplicated;

    // Busca un dato y lo retorna. Lanza excepcion si no lo encuentra.
    E search(E data) throws ItemNoFound;

    // Elimina un dato. Lanza excepcion si el arbol esta vacio o no existe el dato.
    void delete(E data) throws ExceptionIsEmpty, ItemNoFound;

    // Verifica si el arbol tiene elementos o no.
    boolean isEmpty();
}
