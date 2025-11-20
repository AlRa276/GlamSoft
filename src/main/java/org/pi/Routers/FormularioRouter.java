package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.FormularioController;

public class FormularioRouter {
    private final FormularioController formularioController;

    public FormularioRouter(FormularioController formularioController) {
        this.formularioController = formularioController;
    }

    public void register(Javalin app){
        app.get("/formularios",formularioController::findAll);
        app.get("/formularios/{id}",formularioController::findById);
        app.post("/formularios",formularioController::save);
        app.delete("/formularios/{id}",formularioController::deleteFormulario);
        app.patch("/formularios/{id}",formularioController::updateFormulario);
    }
}
