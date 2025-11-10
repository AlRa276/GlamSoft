package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.PreguntaController;

public class PreguntaRouter {
    private final PreguntaController preguntaController;

    public PreguntaRouter(PreguntaController preguntaController) {
        this.preguntaController = preguntaController;
    }
     public void register(Javalin app){
        app.get("/preguntas",preguntaController::findALL);
        app.get("/preguntas/{id}",preguntaController::findPregunta);
        app.post("/preguntas",preguntaController::savePregunta);
        app.delete("/preguntas/{id}",preguntaController::deletePregunta);
        app.patch("/preguntas",preguntaController::updatePregunta);
     }
}
