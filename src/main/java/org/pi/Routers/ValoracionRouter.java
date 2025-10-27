package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ValoracionController;

public class ValoracionRouter {
    private final ValoracionController valoracionController;

    public ValoracionRouter(ValoracionController vc) {
        this.valoracionController = vc;
    }

    public void register(Javalin app) {
        app.get("/valoracions", valoracionController::findAllValoracion);
        app.get("/valoracions/{id}", valoracionController::findValoracion);
        app.post("/valoracions", valoracionController::saveValoracion);
        app.delete("/valoracions/{id}", valoracionController::deleteValoracion);
        app.patch("/valoracions/{id}", valoracionController::updateValoracion);

    }
}
