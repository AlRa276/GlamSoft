package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.EmpleadoController;

public class EmpleadoRouter {
    private final EmpleadoController empleadoController;

    public EmpleadoRouter(EmpleadoController empleadoController) {
        this.empleadoController = empleadoController;
    }

    public void register(Javalin app){
        app.get("/empleados/rol/{id}",empleadoController::findAll);
        app.get("/empleados/{id}",empleadoController::findById);
}
}

