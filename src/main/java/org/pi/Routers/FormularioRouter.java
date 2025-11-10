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
        app.post("/formularios",formularioController::saveFormulario);
        app.delete("/formularios/{id}",formularioController::deleteFormulario);
        app.patch("/formularios",formularioController::updateFormulario);
    }
}
