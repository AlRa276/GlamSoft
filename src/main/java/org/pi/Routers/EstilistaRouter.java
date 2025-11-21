package org.pi.Routers;
import io.javalin.Javalin;
import org.pi.Controllers.EstilistaController;

public class EstilistaRouter {
    private final EstilistaController estilistaController;

    public EstilistaRouter(EstilistaController estilistaController) {
        this.estilistaController = estilistaController;
    }

   public void register(Javalin app){
        app.get("/estilistas",estilistaController::findAll);
        app.get("/estilistas/{id}",estilistaController::findById);
        app.get("/estilistas/{id}/horarios",estilistaController::findHorarios);
        app.get("/estilistas/{id}/servicios",estilistaController::findServicios);
        app.post("/estilistas/horarios",estilistaController::saveHorario);
        app.post("/estilistas/servicios",estilistaController::saveServicios);
        
        // La ruta original:
        app.get("/estilistas/servicios/{id}",estilistaController::fidnEstilistaServicio);
        
        // AÑADIR ESTA LÍNEA PARA SOLUCIONAR EL 404 CON EL POST:
        app.post("/estilistas/servicios/{id}",estilistaController::fidnEstilistaServicio);
}
}