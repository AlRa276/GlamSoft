package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.PersonaController;

public class PersonaRouter {
    private final PersonaController personaController;

    public PersonaRouter(PersonaController pc) {
        this.personaController = pc;
    }

    public void register(Javalin app) {
        app.get("/Personas", personaController::findAllpersona);
        app.get("/Personas/{id}", personaController::findPersona);
        app.post("/Personas", personaController::savePersona);
        app.delete("/Personas/{id}", personaController::deletePersona);
        app.patch("/Personas/{id}", personaController::updatePersona);

    }
}