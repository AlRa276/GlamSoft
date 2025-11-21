package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ServicioController;

public class ServicioRouter {
    private final ServicioController servicioController;

    public ServicioRouter(ServicioController servicioController) {
        this.servicioController = servicioController;
    }

    public void register(Javalin app){
        app.get("/servicios",servicioController::findAll);
        app.get("/servicios/categorias/{id}",servicioController::findByCategoria);
        app.get("/servicios/nombres",servicioController::findNombresServicios);
        app.get("/servicios/{id}",servicioController::findById);
        app.post("/servicios",servicioController::saveServicio);
        app.delete("/servicios/{id}",servicioController::deleteServicio);
        app.patch("/servicios/{id}",servicioController::updateServicio);
    }
}
