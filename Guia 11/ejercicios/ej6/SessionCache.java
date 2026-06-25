package ejercicios.ej6;

// Cache de sesiones con tabla hash de encadenamiento.
// La clave es el token y se dispersa con token.hashCode().
public class SessionCache {
    private LinkedList<Session>[] tabla;
    private int M;

    @SuppressWarnings("unchecked")
    public SessionCache(int size) {
        this.M = size;
        tabla = new LinkedList[M];
        for (int i = 0; i < M; i++) tabla[i] = new LinkedList<>();
    }

    private int hash(String token) {
        return Math.abs(token.hashCode()) % M;
    }

    // registra una nueva sesion con tiempo de vida en milisegundos
    public void login(String token, String username, String role, long ttlMs) {
        long expiresAt = System.currentTimeMillis() + ttlMs;
        tabla[hash(token)].add(new Session(token, username, role, expiresAt));
    }

    // devuelve la sesion si el token existe y no ha expirado; si no, null
    public Session validate(String token) {
        LinkedList<Session> lista = tabla[hash(token)];
        long ahora = System.currentTimeMillis();
        for (int i = 0; i < lista.size(); i++) {
            Session s = lista.get(i);
            if (s.token.equals(token)) {
                return s.haExpirado(ahora) ? null : s;
            }
        }
        return null;
    }

    // cierre de sesion explicito: elimina el token del cache
    public void logout(String token) {
        LinkedList<Session> lista = tabla[hash(token)];
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).token.equals(token)) {
                lista.removeAt(i);
                return;
            }
        }
    }

    // recorre toda la tabla y elimina las sesiones ya expiradas
    public void cleanExpired() {
        long ahora = System.currentTimeMillis();
        for (int i = 0; i < M; i++) {
            LinkedList<Session> lista = tabla[i];
            int j = 0;
            while (j < lista.size()) {
                if (lista.get(j).haExpirado(ahora)) {
                    lista.removeAt(j); // no avanzamos j: la lista se corrio
                } else {
                    j++;
                }
            }
        }
    }

    public int activeCount() {
        int total = 0;
        for (int i = 0; i < M; i++) total += tabla[i].size();
        return total;
    }
}
