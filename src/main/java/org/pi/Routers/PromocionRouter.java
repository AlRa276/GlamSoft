package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.PromocionController;

public class PromocionRouter {
    private final PromocionController promocionController;

    public PromocionRouter(PromocionController promocionController) {
        this.promocionController = promocionController;
    }

    public void register(Javalin app){
        app.get("/promociones",promocionController::findAll);
        app.get("/promociones/{id}",promocionController::findPromocion);
        app.post("/promociones",promocionController::savePromocion);
        app.delete("/promociones/{id}",promocionController::deletePromocion);
        app.patch("/promociones",promocionController::updatePromocion);
    }

}
