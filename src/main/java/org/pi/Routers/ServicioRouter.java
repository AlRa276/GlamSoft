package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ServicioController;

public class ServicioRouter {
    private final ServicioController servicioController;

    public ServicioRouter(ServicioController servicioController) {
        this.servicioController = servicioController;
    }

    public void register(Javalin app){
        app.get("/servicios",servicioController::findALL);
        app.get("/servicios/{id}",servicioController::findServicio);
        app.post("/servicios",servicioController::saveServicio);
        app.delete("/servicios/{id}",servicioController::deleteServicio);
        app.patch("/servicios",servicioController::updateServicio);
    }
}
