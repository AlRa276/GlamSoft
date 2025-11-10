package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.CitaController;

public class CitaRouter {
    private final CitaController citaController;

    public CitaRouter(CitaController citaController) {
        this.citaController = citaController;
    }

    public void register(Javalin app){
        app.get("/citas",citaController::findALL);
        app.get("/citas/{id}",citaController::findCita);
        app.post("/citas",citaController::saveCita);
        app.delete("/citas/{id}",citaController::deleteCita);
        app.patch("/citas/{id}/estado",citaController::statusCita);
        app.patch("/citas/{id}/fecha",citaController::fechaCita);

    }
}
