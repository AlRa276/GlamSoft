package org.pi.Controllers;
import io.javalin.http.Context;
import java.sql.*;
import java.util.Map;

public class AuthController {
    private final TokenManager tokenManager;

    public AuthController(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public void signup(Context ctx) {
        // Obtener datos del body
        Map<String, String> credentials = ctx.bodyAsClass(Map.class);
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Crear usuario (simplificado)
        String userId = saveUser(email, password);

        // Generar token
        String token = tokenManager.issueToken(userId);

        // Enviar respuesta
        ctx.json(Map.of(
                "userId", userId,
                "token", token,
                "success", true
        ));
    }

    public void login(Context ctx) {
        Map<String, String> credentials = ctx.bodyAsClass(Map.class);
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Validar credenciales
        String userId = authenticate(email, password);

        if (userId != null) {
            String token = tokenManager.issueToken(userId);
            ctx.json(Map.of(
                    "userId", userId,
                    "token", token,
                    "success", true
            ));
        } else {
            ctx.status(403).json(Map.of(
                    "error", "Credenciales inválidas",
                    "success", false
            ));
        }
    }

    private String saveUser(String email, String password) {
        // Implementar lógica de guardado
        return "user-" + System.currentTimeMillis();
    }

    private String authenticate(String email, String password) {
        // Implementar lógica de autenticación
        return "user-123";
    }
}
