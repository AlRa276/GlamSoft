package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.HorarioController;

public class HorarioRouter {
    private final HorarioController horarioController;

    public HorarioRouter(HorarioController horarioController) {
        this.horarioController = horarioController;
    }

    public void register(Javalin app){
        app.get("/horarios",horarioController::findALL);
        app.post("/horarios",horarioController::saveHorario);
        app.delete("/horarios/{id}",horarioController::deleteHorario);
        app.patch("/horarios",horarioController::updateHorario);
    }
}
