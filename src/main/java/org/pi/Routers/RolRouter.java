package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.RolController;

public class RolRouter {
    private final RolController rolController;

    public RolRouter(RolController RolController) {
        this.rolController = RolController;
    }

    public void register(Javalin app){
        app.get("/roles",rolController::findAll);
        app.get("/roles/{id}",rolController::findById);
        app.post("/roles",rolController::saveRol);
        app.delete("/roles/{id}",rolController::deleteRol);
        app.patch("/roles",rolController::updateRol);
    }
}
