public class Tarea implements Comparable<Tarea> {

    private String titulo;
    private int prioridad; // 1 = alta, 2 = media, 3 = baja
    private String estado; // "pendiente" o "completada"

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getTitulo()   { return titulo; }
    public int getPrioridad()   { return prioridad; }
    public String getEstado()   { return estado; }

    public void setTitulo(String titulo)     { this.titulo = titulo; }
    public void setPrioridad(int prioridad)  { this.prioridad = prioridad; }
    public void setEstado(String estado)     { this.estado = estado; }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    // equals por titulo, necesario para que search y removeNode funcionen
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tarea)) return false;
        Tarea otra = (Tarea) obj;
        return this.titulo.equalsIgnoreCase(otra.titulo);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}