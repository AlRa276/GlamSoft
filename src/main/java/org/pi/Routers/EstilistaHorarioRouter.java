package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.EstilistaHorarioController;

public class EstilistaHorarioRouter {
    private final EstilistaHorarioController estilistaHorarioController;

    public EstilistaHorarioRouter(EstilistaHorarioController estilistaHorarioController) {
        this.estilistaHorarioController = estilistaHorarioController;
    }

    public void register(Javalin app){
        app.get("/estilistas/horarios",estilistaHorarioController::findALL);
        app.get("/estilistas/horarios/{id}",estilistaHorarioController::findHorarios);
        app.post("/estilistas/horarios",estilistaHorarioController::saveRelaciones);
        app.delete("/estilistas/{idEstilista}/horarios/{idHorario}",estilistaHorarioController::deleteRelacion);
    }
}
