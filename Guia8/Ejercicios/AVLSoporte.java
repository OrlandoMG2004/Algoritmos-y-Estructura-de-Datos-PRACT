// Soporte para ejercicios: BST, AVL, eliminacion, recorridos y rotaciones
class Nodo<E extends Comparable<E>> {
    E data;
    Nodo<E> left;
    Nodo<E> right;
    int height;
    int bf;

    Nodo(E data) {
        this.data = data;
        height = 1;
    }
}

class BST<E extends Comparable<E>> {
    protected Nodo<E> root;

    void insert(E value) {
        root = insert(root, value);
    }

    private Nodo<E> insert(Nodo<E> node, E value) {
        if (node == null) {
            return new Nodo<E>(value);
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    boolean contains(E value) {
        Nodo<E> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.data);
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    int height() {
        return height(root);
    }

    int height(Nodo<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    String inOrder() {
        StringBuilder out = new StringBuilder();
        inOrder(root, out);
        return out.toString().trim();
    }

    void inOrder(Nodo<E> node, StringBuilder out) {
        if (node != null) {
            inOrder(node.left, out);
            out.append(node.data).append(' ');
            inOrder(node.right, out);
        }
    }
}

class AVL<E extends Comparable<E>> extends BST<E> {
    private String name;
    private String[] rotationLog = new String[200];
    private String[] deleteLog = new String[100];
    private int rotations;
    private int deletes;
    private E currentKey;

    AVL(String name) {
        this.name = name;
    }

    void insert(E value) {
        currentKey = value;
        root = insertAvl(root, value);
    }

    private Nodo<E> insertAvl(Nodo<E> node, E value) {
        if (node == null) {
            return new Nodo<E>(value);
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertAvl(node.left, value);
        } else if (cmp > 0) {
            node.right = insertAvl(node.right, value);
        } else {
            return node;
        }
        update(node);
        return rebalanceInsert(node, value);
    }

    void delete(E value) {
        currentKey = value;
        DeleteInfo<E> info = describeDelete(value);
        int before = rotations;
        root = deleteAvl(root, value);
        if (rotations == before) {
            deleteLog[deletes++] = value + " | " + info.caseName + " | " + info.successor + " | - | -";
        } else {
            deleteLog[deletes++] = value + " | " + info.caseName + " | " + info.successor + " | "
                    + infoFrom(rotationLog[before], 1) + " | " + infoFrom(rotationLog[before], 3);
        }
    }

    private Nodo<E> deleteAvl(Nodo<E> node, E value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = deleteAvl(node.left, value);
        } else if (cmp > 0) {
            node.right = deleteAvl(node.right, value);
        } else if (node.left == null || node.right == null) {
            node = node.left != null ? node.left : node.right;
        } else {
            Nodo<E> successor = min(node.right);
            node.data = successor.data;
            node.right = deleteAvl(node.right, successor.data);
        }
        if (node == null) {
            return null;
        }
        update(node);
        return rebalanceDelete(node);
    }

    private Nodo<E> rebalanceInsert(Nodo<E> node, E value) {
        if (node.bf < -1) {
            if (value.compareTo(node.left.data) < 0) {
                return rotateRight(node, "Izquierda-Izquierda", "RSR");
            }
            node.left = rotateLeft(node.left, "Preparacion Izquierda-Derecha", "RSL");
            return rotateRight(node, "Izquierda-Derecha", "RDR");
        }
        if (node.bf > 1) {
            if (value.compareTo(node.right.data) > 0) {
                return rotateLeft(node, "Derecha-Derecha", "RSL");
            }
            node.right = rotateRight(node.right, "Preparacion Derecha-Izquierda", "RSR");
            return rotateLeft(node, "Derecha-Izquierda", "RDL");
        }
        return node;
    }

    private Nodo<E> rebalanceDelete(Nodo<E> node) {
        if (node.bf < -1) {
            if (balance(node.left) <= 0) {
                return rotateRight(node, "Izquierda-Izquierda", "RSR");
            }
            node.left = rotateLeft(node.left, "Preparacion Izquierda-Derecha", "RSL");
            return rotateRight(node, "Izquierda-Derecha", "RDR");
        }
        if (node.bf > 1) {
            if (balance(node.right) >= 0) {
                return rotateLeft(node, "Derecha-Derecha", "RSL");
            }
            node.right = rotateRight(node.right, "Preparacion Derecha-Izquierda", "RSR");
            return rotateLeft(node, "Derecha-Izquierda", "RDL");
        }
        return node;
    }

    private Nodo<E> rotateLeft(Nodo<E> x, String type, String rotation) {
        Nodo<E> y = x.right;
        Nodo<E> t2 = y.left;
        y.left = x;
        x.right = t2;
        update(x);
        update(y);
        rotationLog[rotations++] = currentKey + " | X=" + x.data + " | " + type + " | " + rotation + " | Y=" + y.data;
        return y;
    }

    private Nodo<E> rotateRight(Nodo<E> y, String type, String rotation) {
        Nodo<E> x = y.left;
        Nodo<E> t2 = x.right;
        x.right = y;
        y.left = t2;
        update(y);
        update(x);
        rotationLog[rotations++] = currentKey + " | X=" + y.data + " | " + type + " | " + rotation + " | Y=" + x.data;
        return x;
    }

    private void update(Nodo<E> node) {
        node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
        node.bf = nodeHeight(node.right) - nodeHeight(node.left);
    }

    private int nodeHeight(Nodo<E> node) {
        return node == null ? 0 : node.height;
    }

    private int balance(Nodo<E> node) {
        return node == null ? 0 : node.bf;
    }

    private Nodo<E> min(Nodo<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private DeleteInfo<E> describeDelete(E value) {
        Nodo<E> node = root;
        while (node != null && value.compareTo(node.data) != 0) {
            node = value.compareTo(node.data) < 0 ? node.left : node.right;
        }
        if (node == null) {
            return new DeleteInfo<E>("No existe", "-");
        }
        if (node.left == null && node.right == null) {
            return new DeleteInfo<E>("Caso 1", "-");
        }
        if (node.left != null && node.right != null) {
            return new DeleteInfo<E>("Caso 3", String.valueOf(min(node.right).data));
        }
        return new DeleteInfo<E>("Caso 2", "-");
    }

    String preOrder() {
        StringBuilder out = new StringBuilder();
        preOrder(root, out);
        return out.toString().trim();
    }

    private void preOrder(Nodo<E> node, StringBuilder out) {
        if (node != null) {
            out.append(node.data).append(' ');
            preOrder(node.left, out);
            preOrder(node.right, out);
        }
    }

    String levelOrderRecursive() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < height(); i++) {
            printLevel(root, i, out);
        }
        return out.toString().trim();
    }

    private void printLevel(Nodo<E> node, int level, StringBuilder out) {
        if (node == null) {
            return;
        }
        if (level == 0) {
            out.append(node.data).append(' ');
            return;
        }
        printLevel(node.left, level - 1, out);
        printLevel(node.right, level - 1, out);
    }

    void printTree() {
        System.out.println(name + " | altura=" + height() + " | inorden=" + inOrder());
        printTree(root, "", true);
    }

    private void printTree(Nodo<E> node, String prefix, boolean isRoot) {
        if (node == null) {
            return;
        }
        System.out.println(prefix + (isRoot ? "+-- " : "|-- ") + node.data + "(bf=" + node.bf + ")");
        if (node.left != null || node.right != null) {
            printTree(node.left, prefix + "    ", false);
            printTree(node.right, prefix + "    ", false);
        }
    }

    void printRotationLog() {
        System.out.println("\nRotaciones registradas");
        for (int i = 0; i < rotations; i++) {
            System.out.println((i + 1) + ". " + rotationLog[i]);
        }
    }

    void printDeleteLog() {
        for (int i = 0; i < deletes; i++) {
            System.out.println(deleteLog[i]);
        }
    }

    private String infoFrom(String row, int part) {
        int current = 0;
        int start = 0;
        for (int i = 0; i <= row.length(); i++) {
            if (i == row.length() || row.charAt(i) == '|') {
                if (current == part) {
                    return row.substring(start, i).trim();
                }
                current++;
                start = i + 1;
            }
        }
        return "-";
    }
}

class DeleteInfo<E> {
    String caseName;
    String successor;

    DeleteInfo(String caseName, String successor) {
        this.caseName = caseName;
        this.successor = successor;
    }
}
