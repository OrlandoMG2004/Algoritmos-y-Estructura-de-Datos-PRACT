public class Main {

    public static void main(String[] args) {

        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        // 5 tareas con distintas prioridades y estados
        gestor.agregarTarea(new Tarea("Diseñar BD",        2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API",    3, "completada"));
        gestor.agregarTarea(new Tarea("Code review",       2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42",  1, "completada"));

        System.out.println("=== Lista inicial (" + gestor.contarTareas() + " tareas) ===");
        gestor.imprimirTareas();

        // eliminar una tarea existente
        System.out.println("\n=== Eliminando 'Code review' ===");
        boolean eliminada = gestor.eliminarTarea(new Tarea("Code review", 0, ""));
        System.out.println("  Resultado: " + (eliminada ? "eliminada correctamente" : "no encontrada"));

        System.out.println("\n=== Tareas actuales (" + gestor.contarTareas() + " tareas) ===");
        gestor.imprimirTareas();

        // verificar si ciertas tareas existen
        System.out.println("\n=== Búsqueda de tareas ===");
        System.out.println("  ¿Existe 'Deploy produccion'? " + gestor.contieneTarea(new Tarea("Deploy produccion", 0, "")));
        System.out.println("  ¿Existe 'Code review'?       " + gestor.contieneTarea(new Tarea("Code review", 0, "")));

        // tarea más prioritaria
        System.out.println("\n=== Tarea más prioritaria ===");
        System.out.println("  " + gestor.obtenerTareaMasPrioritaria());

        // invertir la lista
        System.out.println("\n=== Lista invertida ===");
        gestor.invertirTareas();
        gestor.imprimirTareas();

        // separar completadas y pendientes en dos listas
        System.out.println("\n=== Separando tareas por estado ===");
        GestorDeTareas<Tarea> completadas = new GestorDeTareas<>();
        GestorDeTareas<Tarea> pendientes  = new GestorDeTareas<>();

        Node<Tarea> current = gestor.getLista().getFirst();
        while (current != null) {
            if ("completada".equals(current.value.getEstado())) {
                completadas.agregarTarea(current.value);
            } else {
                pendientes.agregarTarea(current.value);
            }
            current = current.next;
        }

        System.out.println("\n  -- Completadas (" + completadas.contarTareas() + ") --");
        completadas.imprimirTareas();

        System.out.println("\n  -- Pendientes (" + pendientes.contarTareas() + ") --");
        pendientes.imprimirTareas();
    }
}