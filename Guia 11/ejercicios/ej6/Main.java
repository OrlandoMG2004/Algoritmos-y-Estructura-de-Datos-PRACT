package ejercicios.ej6;

// TestSessionCache: simula el flujo de un cache de sesiones de un sistema web.
public class Main {
    public static void main(String[] args) {
        SessionCache cache = new SessionCache(7);

        System.out.println("=== (1) Tres usuarios inician sesion ===");
        cache.login("abc123", "Juan", "admin", 60000);   // valido por 60 s
        cache.login("xyz789", "Ana", "user", 60000);    // valido por 60 s
        cache.login("def456", "Luis", "user", -1000);   // ttl negativo: ya expirado
        System.out.println("Sesiones activas registradas: " + cache.activeCount());

        System.out.println("\n=== (2) Validacion de tokens ===");
        validar(cache, "abc123");
        validar(cache, "xyz789");
        validar(cache, "def456"); // este ya expiro

        System.out.println("\n=== (3) Cierre de sesion explicito de abc123 ===");
        cache.logout("abc123");
        System.out.println("Sesiones tras el logout: " + cache.activeCount());

        System.out.println("\n=== (4) Limpieza de sesiones expiradas ===");
        cache.cleanExpired();
        System.out.println("Sesiones activas tras cleanExpired(): " + cache.activeCount());

        System.out.println("\n=== Reflexion ===");
        System.out.println("Una tabla hash valida un token en O(1) promedio porque salta");
        System.out.println("directo a su cubo, en vez de recorrer una lista de N sesiones O(N).");
        System.out.println("HashMap de Java ya trae redimensionado automatico, manejo de");
        System.out.println("colisiones (listas/arboles) y un hashing mas robusto que esta");
        System.out.println("implementacion manual, ademas de estar bien probado y optimizado.");
    }

    static void validar(SessionCache cache, String token) {
        Session s = cache.validate(token);
        if (s != null) {
            System.out.println("Token " + token + " VALIDO -> " + s.username + " (" + s.role + ")");
        } else {
            System.out.println("Token " + token + " INVALIDO o expirado");
        }
    }
}
