package ejercicios.ej6;

// Una sesion activa del sistema. expiresAt es un timestamp Unix en milisegundos.
public class Session {
    String token;
    String username;
    String role;
    long expiresAt;

    public Session(String token, String username, String role, long expiresAt) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public boolean haExpirado(long ahora) {
        return expiresAt < ahora;
    }

    @Override
    public String toString() {
        return "Session{token=" + token + ", user=" + username + ", role=" + role +
               ", expiresAt=" + expiresAt + "}";
    }
}
