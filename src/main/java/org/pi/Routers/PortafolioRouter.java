package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.PortafolioController;

public class PortafolioRouter {
    private final PortafolioController portafolioController;

    public PortafolioRouter(PortafolioController portafolioController) {
        this.portafolioController = portafolioController;
    }

    public void register(Javalin app){
        app.get("/imagenes",portafolioController::findAll);
        app.post("/imagenes",portafolioController::savePortafolio);
        app.delete("/imagenes/{id}",portafolioController::deletePortafolio);
        app.patch("/imagenes",portafolioController::updatePortafolio);
    }
}
