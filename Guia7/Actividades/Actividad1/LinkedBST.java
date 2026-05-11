package Actividad1;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // ── Nodo interno ──────────────────────────────────────────────────────────
    public class Node {
        public E    data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }
        public Node(E data, Node left, Node right) {
            this.data  = data;
            this.left  = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    // ── isEmpty ───────────────────────────────────────────────────────────────
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    // ── INSERT ────────────────────────────────────────────────────────────────
    @Override
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRecursive(this.root, data);
    }

    private Node insertRecursive(Node current, E data) throws ItemDuplicated {
        if (current == null) return new Node(data);
        int cmp = data.compareTo(current.data);
        if      (cmp < 0) current.left  = insertRecursive(current.left,  data);
        else if (cmp > 0) current.right = insertRecursive(current.right, data);
        else              throw new ItemDuplicated("El dato '" + data + "' ya existe en el arbol.");
        return current;
    }

    // ── SEARCH ────────────────────────────────────────────────────────────────
    @Override
    public E search(E data) throws ItemNoFound {
        Node result = searchRecursive(this.root, data);
        if (result == null)
            throw new ItemNoFound("No se encontro el dato '" + data + "' en el arbol.");
        return result.data;
    }

    private Node searchRecursive(Node current, E data) {
        if (current == null || data.equals(current.data)) return current;
        return data.compareTo(current.data) < 0
            ? searchRecursive(current.left,  data)
            : searchRecursive(current.right, data);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    // CORRECCION: ahora lanza ItemNoFound si el dato no existe
    @Override
    public void delete(E data) throws ExceptionIsEmpty, ItemNoFound {
        if (isEmpty()) throw new ExceptionIsEmpty();
        search(data); // valida existencia, lanza ItemNoFound si no existe
        this.root = deleteRecursive(this.root, data);
    }

    private Node deleteRecursive(Node current, E data) {
        if (current == null) return null;
        int cmp = data.compareTo(current.data);
        if      (cmp < 0) current.left  = deleteRecursive(current.left,  data);
        else if (cmp > 0) current.right = deleteRecursive(current.right, data);
        else {
            // Caso 1: hoja
            if (current.left == null && current.right == null) return null;
            // Caso 2: un solo hijo
            if (current.left  == null) return current.right;
            if (current.right == null) return current.left;
            // Caso 3: dos hijos → sucesor inorden (minimo del subarbol derecho)
            current.data  = findMinValue(current.right);
            current.right = deleteRecursive(current.right, current.data);
        }
        return current;
    }

    private E findMinValue(Node node) {
        while (node.left != null) node = node.left;
        return node.data;
    }

    // ── TRAVERSALS ────────────────────────────────────────────────────────────

    public void inOrder() {
        System.out.print("Recorrido In-Orden: ");
        inOrder(this.root);
        System.out.println();
    }
    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void preOrder() {
        System.out.print("Recorrido Pre-Orden: ");
        preOrder(this.root);
        System.out.println();
    }
    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        System.out.print("Recorrido Post-Orden: ");
        postOrder(this.root);
        System.out.println();
    }
    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    // ── MIN / MAX ─────────────────────────────────────────────────────────────

    public E findMin() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("El arbol esta vacio.");
        return findMinNode(this.root);
    }
    private E findMinNode(Node node) throws ItemNoFound {
        Node current = node;
        while (current.left != null) current = current.left;
        return search(current.data);
    }

    public E findMax() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("El arbol esta vacio.");
        return findMaxNode(this.root);
    }
    private E findMaxNode(Node node) throws ItemNoFound {
        Node current = node;
        while (current.right != null) current = current.right;
        return search(current.data);
    }

    // ── TOSTRING ──────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        if (isEmpty()) return "Arbol vacio";
        StringBuilder sb = new StringBuilder("BST [ ");
        toStringInOrder(this.root, sb);
        sb.append("]");
        return sb.toString();
    }
    private void toStringInOrder(Node node, StringBuilder sb) {
        if (node == null) return;
        toStringInOrder(node.left, sb);
        sb.append(node.data).append(" ");
        toStringInOrder(node.right, sb);
    }

    // ── helper: total de nodos (para uso interno) ─────────────────────────────
    protected int countAllRec(Node node) {
        if (node == null) return 0;
        return 1 + countAllRec(node.left) + countAllRec(node.right);
    }

    // ── getter de root para subclases ─────────────────────────────────────────
    protected Node getRoot() {
        return this.root;
    }
}
