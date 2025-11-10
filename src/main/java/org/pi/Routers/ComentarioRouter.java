package org.pi.Routers;

import io.javalin.Javalin;
import org.pi.Controllers.ComentarioController;

public class ComentarioRouter {
    private final ComentarioController comentarioController;

    public ComentarioRouter(ComentarioController comentarioController) {
        this.comentarioController = comentarioController;
    }

    public void register(Javalin app){
        app.get("/comentarios",comentarioController::findALL);
        app.get("/comentarios/{id}",comentarioController::findComentario);
        app.post("/comentarios",comentarioController::saveComentario);
        app.delete("/comentarios/{id}",comentarioController::deleteComentario);
        app.patch("/comentarios",comentarioController::updateComentario);
    }
}
