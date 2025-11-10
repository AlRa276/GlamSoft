package org.pi.Routers;


import io.javalin.Javalin;
import org.pi.Controllers.CitaServicioController;

public class CitaServicioRouter {
    private final CitaServicioController citaServicioController;

    public CitaServicioRouter(CitaServicioController citaServicioController) {
        this.citaServicioController = citaServicioController;
    }

    public void register(Javalin app){
        app.get("/citas/servicios",citaServicioController::findALL);
        app.get("/citas/{idCita}/servicios/{idServicio}",citaServicioController::findRelacion);
        app.post("/citas/servicios",citaServicioController::saveRelacion);
        app.delete("/citas/{idCita}/servicios/{idServicio}",citaServicioController::deleteRelacion);
    }
}
