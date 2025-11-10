package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.EstilistaServicioController;

public class EstilistaServicioRouter {
    private final EstilistaServicioController estilistaServicioController;

    public EstilistaServicioRouter(EstilistaServicioController estilistaServicioController) {
        this.estilistaServicioController = estilistaServicioController;
    }

    public void register(Javalin app){
        app.get("/estilistas/servicios",estilistaServicioController::findALL);
        app.get("/estilistas/servicios/{id}",estilistaServicioController::findServicios);
        app.post("/estilistas/servicios",estilistaServicioController::saveRelacion);
        app.delete("/estilistas/{idEstilista/servicios/{idServicio}",estilistaServicioController::deleteRelacion);
    }
}
