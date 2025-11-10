package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ValoracionController;

public class ValoracionRouter {
    private final ValoracionController valoracionController;

    public ValoracionRouter(ValoracionController valoracionController) {
        this.valoracionController = valoracionController;
    }

    public void register(Javalin app){
        app.get("/valoraciones",valoracionController::findALLvaloracio);
        app.post("/valoraciones",valoracionController::saveValoracion);
        app.delete("/valoraciones/{id}",valoracionController::deleteValoracion);
    }
}
