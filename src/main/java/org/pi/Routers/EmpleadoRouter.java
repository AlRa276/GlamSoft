package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.EmpleadoController;

public class EmpleadoRouter {
    private final EmpleadoController empleadoController;

    public EmpleadoRouter(EmpleadoController empleadoController) {
        this.empleadoController = empleadoController;
    }

    public void register(Javalin app){
        app.get("/empleados/{id}/rol",empleadoController::findALL);
        app.get("/empleados/{id}",empleadoController::findEmpleado);
        app.post("/empleados",empleadoController::saveEmpleado);
        app.delete("/empleados/{id}",empleadoController::deleteEmpleado);
    }
}
