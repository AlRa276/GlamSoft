package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.UsuarioController;

public class UsuarioRouter {
    private final UsuarioController usuarioController;

    public UsuarioRouter(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void register(Javalin app){
        app.post("/registrar",usuarioController::registrarUsuario);
        app.post("/login",usuarioController::verificarUsuario);
        app.post("/empleados",usuarioController::registrarEmpleadoCompleto);
        app.patch("/empleados",usuarioController::updateEmpleadoCompleto);
        app.get("/usuarios/{email}",usuarioController::findUser);
        app.delete("/usuarios/{id}",usuarioController::deleteUser);
        app.patch("/usuarios",usuarioController::updateUser);
      
       
    }
}
