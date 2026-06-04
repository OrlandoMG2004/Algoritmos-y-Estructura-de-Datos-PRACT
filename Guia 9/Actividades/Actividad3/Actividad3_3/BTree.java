import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Actividad 3.3 - BTree completa: insercion (3.2) + toString/writeTree (figura 10.14)
public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // ----- Insercion (3.2) -----
    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado: " + cl);
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // ----- Visualizacion (3.3) -----
    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
        StringBuilder sb = new StringBuilder();
        sb.append("Id.Nodo\tClaves Nodo\tId.Padre\tId.Hijos\n");

        Queue<BNode<E>> cola = new LinkedList<>();
        Queue<Integer> padres = new LinkedList<>();
        cola.add(current);
        padres.add(null);

        while (!cola.isEmpty()) {
            BNode<E> nodo = cola.poll();
            Integer idPadre = padres.poll();

            sb.append(nodo.idNode).append("\t[");
            for (int i = 0; i < nodo.count; i++) {
                sb.append(nodo.keys.get(i));
                if (i < nodo.count - 1) sb.append(", ");
            }
            sb.append("]\t\t").append(idPadre == null ? "--" : idPadre).append("\t\t");

            if (nodo.isLeaf()) {
                sb.append("--");
            } else {
                ArrayList<Integer> ids = new ArrayList<>();
                for (int i = 0; i <= nodo.count; i++) {
                    BNode<E> h = nodo.childs.get(i);
                    if (h != null) {
                        ids.add(h.idNode);
                        cola.add(h);
                        padres.add(nodo.idNode);
                    }
                }
                sb.append(ids);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
