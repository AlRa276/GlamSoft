package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.UsuarioController;

public class UsuarioRouter {
    private final UsuarioController usuarioController;

    public UsuarioRouter(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void register(Javalin app){
        app.post("/resgistrar",usuarioController::registrarUsuario);
        app.post("/login",usuarioController::verificarUsuario);
        app.get("/usuarios",usuarioController::findUser);
        app.delete("/usuarios",usuarioController::deleteUser);
        app.patch("/usuarios",usuarioController::updateUser);
    }
}
