package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.CitaController;

public class CitaRouter {
    private final CitaController citaController;

    public CitaRouter(CitaController citaController) {
        this.citaController = citaController;
    }

    public void register(Javalin app){
        app.get("/citas/mes",citaController::getCitasPorMes);
        app.get("/citas/semanas",citaController::getCitasPorSemana);
        app.get("/citas/dias",citaController::getCitasPorDia);
        app.get("/citas/clientes/{id}",citaController::getHistorialCliente);
        app.get("/citas",citaController::findALL);
        app.get("/citas/{id}",citaController::findCita);
        app.post("/citas",citaController::saveCita);
        app.delete("/citas/{id}",citaController::deleteCita);
        app.patch("/citas/estado",citaController::statusCita);
        app.patch("/citas/fecha",citaController::fechaCita);

    }
}
