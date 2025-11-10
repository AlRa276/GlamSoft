package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ServicioPromocionController;

public class ServicioPromocionRouter {
    private final ServicioPromocionController servicioPromocionController;

    public ServicioPromocionRouter(ServicioPromocionController servicioPromocionController) {
        this.servicioPromocionController = servicioPromocionController;
    }

    public void register(Javalin app){
        app.get("/servicios/promociones",servicioPromocionController::findALL);
        app.get("/servicios/promociones/{id}",servicioPromocionController::findRelacion);
        app.post("/servicios/promociones",servicioPromocionController::saveRelacion);
        app.delete("/servicios/{idServicio}/promociones/{idPromocion}",servicioPromocionController::deleteRelacion);

    }
}
