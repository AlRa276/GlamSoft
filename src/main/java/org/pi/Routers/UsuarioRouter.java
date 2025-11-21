package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.UsuarioController;

public class UsuarioRouter {
    private final UsuarioController usuarioController;

    public UsuarioRouter(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void register(Javalin app){
        // Auth
        app.post("/registrar", usuarioController::registrarUsuario);
        app.post("/login", usuarioController::verificarUsuario);

        // Empleados
        app.post("/empleados", usuarioController::registrarEmpleadoCompleto);
        app.patch("/empleados", usuarioController::updateEmpleadoCompleto);

        // Usuarios (CRUD)
        app.get("/usuarios", usuarioController::findAll);            // Listar todos
        app.get("/usuarios/{id}", usuarioController::findById);      // Buscar por ID (Antes era por email)
        app.delete("/usuarios/{id}", usuarioController::deleteUser); // Eliminar
        app.patch("/usuarios", usuarioController::updateUser);       // Actualizar (requiere JSON con idUsuario)
    }
}