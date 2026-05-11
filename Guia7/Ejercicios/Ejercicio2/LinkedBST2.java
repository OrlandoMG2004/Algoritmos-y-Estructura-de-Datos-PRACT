package Ejercicio2;

import Actividad1.LinkedBST;
import Actividad1.ExceptionIsEmpty;
import Actividad1.ItemNoFound;

public class LinkedBST2<E extends Comparable<E>> extends LinkedBST<E> {

    // ─────────────────────────────────────────────────────────────────────────
    //  EJERCICIO 2
    // ─────────────────────────────────────────────────────────────────────────

    // 2a) destroyNodes ─────────────────────────────────────────────────────────
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El arbol ya esta vacio.");
        // Vaciamos seteando root a null via delete hasta que este vacio
        // Como no podemos acceder a root directamente lo hacemos con inOrder
        // Usamos getRoot() que esta protegido en LinkedBST
        while (!isEmpty()) {
            try {
                delete(getRoot().data);
            } catch (ExceptionIsEmpty | ItemNoFound e) {
                break;
            }
        }
    }

    // 2b) countAllNodes - total de nodos (hojas + internos) ───────────────────
    public int countAllNodes() {
        return countAllRec(getRoot());
    }

    // 2c) countNodes - solo nodos internos (no-hoja) ───────────────────────────
    public int countNodes() {
        return countNodesRec(getRoot());
    }

    private int countNodesRec(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0; // hoja, no cuenta
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    // 2d) height(x) ITERATIVO - sin java.util ──────────────────────────────────
    public int height(E x) {
        // Paso 1: buscar el nodo con valor x de forma iterativa
        Node target = getRoot();
        while (target != null) {
            int cmp = x.compareTo(target.data);
            if      (cmp < 0) target = target.left;
            else if (cmp > 0) target = target.right;
            else              break;
        }
        if (target == null) return -1; // no existe en el arbol

        // Paso 2: calcular altura del subarbol usando BFS iterativo
        // Cola implementada con arreglo (sin java.util)
        Object[] queue = new Object[countAllNodes() + 1];
        int head = 0, tail = 0;
        queue[tail++] = target;
        int h = -1;

        while (head < tail) {
            int levelSize = tail - head;
            h++;
            for (int i = 0; i < levelSize; i++) {
                Node cur = (Node) queue[head++];
                if (cur.left  != null) queue[tail++] = cur.left;
                if (cur.right != null) queue[tail++] = cur.right;
            }
        }
        return h;
    }

    // 2e) amplitude(nivel) - nodos en ese nivel, usa height internamente ───────
    public int amplitude(int nivel) {
        if (isEmpty()) return 0;
        int h = height(getRoot().data); // altura total del arbol
        if (nivel < 0 || nivel > h) return 0;
        return nodesAtLevel(getRoot(), nivel);
    }

    private int nodesAtLevel(Node node, int nivel) {
        if (node == null)  return 0;
        if (nivel == 0)    return 1;
        return nodesAtLevel(node.left,  nivel - 1)
             + nodesAtLevel(node.right, nivel - 1);
    }
}
