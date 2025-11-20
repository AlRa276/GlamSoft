package org.pi.Controllers;
import io.javalin.http.Context;
import io.javalin.Javalin;
import java.sql.*;
import java.util.Map;

import io.javalin.http.UnauthorizedResponse;

public class JwtMiddleware {
    private final TokenManager tokenManager;

    public JwtMiddleware(TokenManager tokenManager) {

        this.tokenManager = tokenManager;
    }

    public void apply(Javalin app) {
        app.before("/api/protected/*", this::validateJwt);
        app.before("/materias", this::validateJwt);
    }

    private void validateJwt(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String idUser = ctx.header("idUser");

        // Verificar que existan los headers
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedResponse("Authorization header faltante o inválido");
        }

        if (idUser == null) {
            throw new UnauthorizedResponse("id_User header requerido");
            //return;
        }

        // Extraer el token
        String token = authHeader.substring(7);

        // Validar el token
        try {
            if (!tokenManager.validarToken(token,idUser)) {
                ctx.status(403).json(Map.of(
                        "error", "Token inválido o expirado"
                ));
                throw new Exception("Token inválido o expirado");
            }
            // Token válido - la solicitud continúa
        } catch (Exception e) {
            ctx.status(401).json(Map.of(
                    "error", "Error al validar el token"
            ));
        }

        int status = ctx.statusCode();
        if (status == 401 || status == 403) {
            throw new UnauthorizedResponse("Error: " + ctx.result());
        }
    }
    public void noAutorizo(UnauthorizedResponse  e, Context ctx){
        ctx.status(401).json(Map.of("error", "Acceso no autorizado: "+e.getMessage()));
    }
}
