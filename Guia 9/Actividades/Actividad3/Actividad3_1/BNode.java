import java.util.ArrayList;

// Actividad 3.1 - Nodo del Arbol B (figura 10.3)
public class BNode<E extends Comparable<E>> {

    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    private static int contador = 0;
    protected int idNode;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        this.idNode = ++contador;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int n) {
        return this.count == n;
    }

    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // true + pos de la clave si la halla; false + pos del hijo a descender si no
    public boolean searchNode(E cl, int pos[]) {
        int i = 0;
        while (i < this.count && this.keys.get(i).compareTo(cl) < 0) {
            i++;
        }
        if (i < this.count && this.keys.get(i).compareTo(cl) == 0) {
            pos[0] = i;
            return true;
        }
        pos[0] = i;
        return false;
    }

    public boolean isLeaf() {
        return this.childs.get(0) == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(idNode).append("] ");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
