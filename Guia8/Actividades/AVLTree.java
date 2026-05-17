// Soporte para actividades: BST, NodeAVL, AVLTree, rotaciones y tablas
class Node<E extends Comparable<E>> {
    protected E data;
    protected Node<E> left;
    protected Node<E> right;

    Node(E data) {
        this.data = data;
    }
}

class NodeAVL<E extends Comparable<E>> extends Node<E> {
    protected int bf;
    protected int height;

    NodeAVL(E data) {
        super(data);
        height = 1;
    }

    public String toString() {
        return data + "(bf=" + bf + ")";
    }
}

class BSTree<E extends Comparable<E>> {
    protected Node<E> root;

    public boolean contains(E value) {
        Node<E> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.data);
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    public int height() {
        return height(root);
    }

    protected int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public String inOrder() {
        StringBuilder out = new StringBuilder();
        inOrder(root, out);
        return out.toString().trim();
    }

    protected void inOrder(Node<E> node, StringBuilder out) {
        if (node != null) {
            inOrder(node.left, out);
            out.append(node.data).append(' ');
            inOrder(node.right, out);
        }
    }
}

class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    private RotationEvent<E>[] rotations;
    private DeleteEvent<E>[] deletes;
    private int rotationCount;
    private int deleteCount;
    private E currentOperation;

    @SuppressWarnings("unchecked")
    AVLTree() {
        rotations = (RotationEvent<E>[]) new RotationEvent[200];
        deletes = (DeleteEvent<E>[]) new DeleteEvent[100];
    }

    public void insert(E value) {
        currentOperation = value;
        root = insert((NodeAVL<E>) root, value);
    }

    private NodeAVL<E> insert(NodeAVL<E> node, E value) {
        if (node == null) {
            return new NodeAVL<E>(value);
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>) node.left, value);
        } else if (cmp > 0) {
            node.right = insert((NodeAVL<E>) node.right, value);
        } else {
            return node;
        }

        update(node);
        return rebalanceAfterInsert(node, value);
    }

    public void delete(E value) {
        DeleteEvent<E> event = describeDelete(value);
        int before = rotationCount;
        root = delete((NodeAVL<E>) root, value);
        event.withRotations(rotations, before, rotationCount);
        deletes[deleteCount++] = event;
    }

    private NodeAVL<E> delete(NodeAVL<E> node, E value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete((NodeAVL<E>) node.left, value);
        } else if (cmp > 0) {
            node.right = delete((NodeAVL<E>) node.right, value);
        } else if (node.left == null || node.right == null) {
            node = (NodeAVL<E>) (node.left != null ? node.left : node.right);
        } else {
            NodeAVL<E> successor = min((NodeAVL<E>) node.right);
            node.data = successor.data;
            node.right = delete((NodeAVL<E>) node.right, successor.data);
        }

        if (node == null) {
            return null;
        }

        update(node);
        return rebalanceAfterDelete(node);
    }

    private NodeAVL<E> rebalanceAfterInsert(NodeAVL<E> node, E value) {
        if (node.bf < -1) {
            NodeAVL<E> left = (NodeAVL<E>) node.left;
            if (value.compareTo(left.data) < 0) {
                return rotateSR(node, "Izquierda-Izquierda", "RSR");
            }
            node.left = rotateSL(left, "Preparacion Izquierda-Derecha", "RSL");
            return rotateSR(node, "Izquierda-Derecha", "RDR");
        }

        if (node.bf > 1) {
            NodeAVL<E> right = (NodeAVL<E>) node.right;
            if (value.compareTo(right.data) > 0) {
                return rotateSL(node, "Derecha-Derecha", "RSL");
            }
            node.right = rotateSR(right, "Preparacion Derecha-Izquierda", "RSR");
            return rotateSL(node, "Derecha-Izquierda", "RDL");
        }
        return node;
    }

    private NodeAVL<E> rebalanceAfterDelete(NodeAVL<E> node) {
        if (node.bf < -1) {
            return balanceToRight(node);
        }
        if (node.bf > 1) {
            return balanceToLeft(node);
        }
        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> right = (NodeAVL<E>) node.right;
        if (balance(right) >= 0) {
            return rotateSL(node, "Derecha-Derecha", "RSL");
        }
        node.right = rotateSR(right, "Preparacion Derecha-Izquierda", "RSR");
        return rotateSL(node, "Derecha-Izquierda", "RDL");
    }

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> left = (NodeAVL<E>) node.left;
        if (balance(left) <= 0) {
            return rotateSR(node, "Izquierda-Izquierda", "RSR");
        }
        node.left = rotateSL(left, "Preparacion Izquierda-Derecha", "RSL");
        return rotateSR(node, "Izquierda-Derecha", "RDR");
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> x, String type, String rotation) {
        NodeAVL<E> y = (NodeAVL<E>) x.right;
        NodeAVL<E> t2 = (NodeAVL<E>) y.left;
        y.left = x;
        x.right = t2;
        update(x);
        update(y);
        logRotation(currentOperation, x.data, type, rotation, y.data);
        return y;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> y, String type, String rotation) {
        NodeAVL<E> x = (NodeAVL<E>) y.left;
        NodeAVL<E> t2 = (NodeAVL<E>) x.right;
        x.right = y;
        y.left = t2;
        update(y);
        update(x);
        logRotation(currentOperation, y.data, type, rotation, x.data);
        return x;
    }

    private void update(NodeAVL<E> node) {
        node.height = 1 + Math.max(nodeHeight((NodeAVL<E>) node.left), nodeHeight((NodeAVL<E>) node.right));
        node.bf = nodeHeight((NodeAVL<E>) node.right) - nodeHeight((NodeAVL<E>) node.left);
    }

    private int nodeHeight(NodeAVL<E> node) {
        return node == null ? 0 : node.height;
    }

    private int balance(NodeAVL<E> node) {
        return node == null ? 0 : node.bf;
    }

    private NodeAVL<E> min(NodeAVL<E> node) {
        while (node.left != null) {
            node = (NodeAVL<E>) node.left;
        }
        return node;
    }

    private DeleteEvent<E> describeDelete(E value) {
        NodeAVL<E> node = (NodeAVL<E>) root;
        while (node != null && value.compareTo(node.data) != 0) {
            node = value.compareTo(node.data) < 0 ? (NodeAVL<E>) node.left : (NodeAVL<E>) node.right;
        }
        if (node == null) {
            return new DeleteEvent<E>(value, "No existe", "-", "No", "-", "-", "-", "No");
        }
        boolean hasLeft = node.left != null;
        boolean hasRight = node.right != null;
        if (!hasLeft && !hasRight) {
            return new DeleteEvent<E>(value, "Caso 1", "-", "", "", "", "", "");
        }
        if (hasLeft && hasRight) {
            return new DeleteEvent<E>(value, "Caso 3", min((NodeAVL<E>) node.right).data.toString(), "", "", "", "", "");
        }
        return new DeleteEvent<E>(value, "Caso 2", "-", "", "", "", "", "");
    }

    private void logRotation(E key, E x, String type, String rotation, E y) {
        if (type.startsWith("Preparacion")) {
            return;
        }
        rotations[rotationCount++] = new RotationEvent<E>(key, x, type, rotation, y);
    }

    public void printTree() {
        printTree((NodeAVL<E>) root, "", true);
    }

    private void printTree(NodeAVL<E> node, String prefix, boolean rootNode) {
        if (node == null) {
            return;
        }
        System.out.println(prefix + (rootNode ? "+-- " : "|-- ") + node);
        if (node.left != null || node.right != null) {
            printTree((NodeAVL<E>) node.left, prefix + "    ", false);
            printTree((NodeAVL<E>) node.right, prefix + "    ", false);
        }
    }

    public String preOrder() {
        StringBuilder out = new StringBuilder();
        preOrder((NodeAVL<E>) root, out);
        return out.toString().trim();
    }

    private void preOrder(NodeAVL<E> node, StringBuilder out) {
        if (node != null) {
            out.append(node.data).append(' ');
            preOrder((NodeAVL<E>) node.left, out);
            preOrder((NodeAVL<E>) node.right, out);
        }
    }

    public String levelOrderRecursive() {
        StringBuilder out = new StringBuilder();
        for (int level = 0; level < height(); level++) {
            printLevel((NodeAVL<E>) root, level, out);
        }
        return out.toString().trim();
    }

    private void printLevel(NodeAVL<E> node, int level, StringBuilder out) {
        if (node == null) {
            return;
        }
        if (level == 0) {
            out.append(node.data).append(' ');
            return;
        }
        printLevel((NodeAVL<E>) node.left, level - 1, out);
        printLevel((NodeAVL<E>) node.right, level - 1, out);
    }

    public void printRotationTable() {
        for (int i = 0; i < rotationCount; i++) {
            System.out.println((i + 1) + " | " + rotations[i]);
        }
    }

    public void printDeleteTable() {
        for (int i = 0; i < deleteCount; i++) {
            System.out.println(deletes[i]);
        }
    }

    static AVLTree<Integer> figure810() {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        NodeAVL<Integer> n33 = new NodeAVL<Integer>(33);
        NodeAVL<Integer> n20 = new NodeAVL<Integer>(20);
        NodeAVL<Integer> n45 = new NodeAVL<Integer>(45);
        NodeAVL<Integer> n12 = new NodeAVL<Integer>(12);
        NodeAVL<Integer> n26 = new NodeAVL<Integer>(26);
        NodeAVL<Integer> n41 = new NodeAVL<Integer>(41);
        NodeAVL<Integer> n56 = new NodeAVL<Integer>(56);
        NodeAVL<Integer> n6 = new NodeAVL<Integer>(6);
        NodeAVL<Integer> n15 = new NodeAVL<Integer>(15);
        NodeAVL<Integer> n24 = new NodeAVL<Integer>(24);
        NodeAVL<Integer> n35 = new NodeAVL<Integer>(35);
        NodeAVL<Integer> n44 = new NodeAVL<Integer>(44);
        NodeAVL<Integer> n48 = new NodeAVL<Integer>(48);
        NodeAVL<Integer> n59 = new NodeAVL<Integer>(59);
        NodeAVL<Integer> n17 = new NodeAVL<Integer>(17);
        NodeAVL<Integer> n38 = new NodeAVL<Integer>(38);
        NodeAVL<Integer> n46 = new NodeAVL<Integer>(46);
        NodeAVL<Integer> n53 = new NodeAVL<Integer>(53);
        NodeAVL<Integer> n65 = new NodeAVL<Integer>(65);
        NodeAVL<Integer> n50 = new NodeAVL<Integer>(50);

        n33.left = n20; n33.right = n45;
        n20.left = n12; n20.right = n26;
        n12.left = n6; n12.right = n15;
        n15.right = n17;
        n26.left = n24;
        n45.left = n41; n45.right = n56;
        n41.left = n35; n41.right = n44;
        n35.right = n38;
        n56.left = n48; n56.right = n59;
        n48.left = n46; n48.right = n53;
        n53.left = n50;
        n59.right = n65;
        avl.root = n33;
        avl.updateAll(n33);
        return avl;
    }

    private int updateAll(NodeAVL<E> node) {
        if (node == null) {
            return 0;
        }
        int left = updateAll((NodeAVL<E>) node.left);
        int right = updateAll((NodeAVL<E>) node.right);
        node.height = 1 + Math.max(left, right);
        node.bf = right - left;
        return node.height;
    }
}

class RotationEvent<E> {
    private E key;
    private E x;
    private String type;
    private String rotation;
    private E y;

    RotationEvent(E key, E x, String type, String rotation, E y) {
        this.key = key;
        this.x = x;
        this.type = type;
        this.rotation = rotation;
        this.y = y;
    }

    public String toString() {
        return key + " | " + x + " | " + type + " | " + rotation + " | " + y;
    }

    String x() {
        return String.valueOf(x);
    }

    String rotation() {
        return rotation;
    }

    String y() {
        return String.valueOf(y);
    }
}

class DeleteEvent<E> {
    private E key;
    private String caseName;
    private String successor;
    private String unbalanced;
    private String x;
    private String rotation;
    private String y;
    private String another;

    DeleteEvent(E key, String caseName, String successor, String unbalanced, String x, String rotation, String y, String another) {
        this.key = key;
        this.caseName = caseName;
        this.successor = successor;
        this.unbalanced = unbalanced;
        this.x = x;
        this.rotation = rotation;
        this.y = y;
        this.another = another;
    }

    void withRotations(RotationEvent<E>[] events, int from, int to) {
        if (to == from) {
            unbalanced = "No";
            x = "-";
            rotation = "-";
            y = "-";
            another = "No";
            return;
        }
        unbalanced = "Si";
        x = events[from].x();
        rotation = events[from].rotation();
        y = events[from].y();
        another = to - from > 1 ? "Si" : "No";
    }

    public String toString() {
        return key + " | " + caseName + " | " + successor + " | " + unbalanced + " | "
                + x + " | " + rotation + " | " + y + " | " + another;
    }
}
