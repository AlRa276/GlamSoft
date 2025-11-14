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
        app.post("/comentarios",comentarioController::saveComentario);
        app.delete("/comentarios/{id}",comentarioController::deleteComentario);
        app.get("/comentarios/clientes/{id}",comentarioController::HistorialComen);
        app.get("/comentarios/fecha",comentarioController::find8comen);//muestra los ultimos 8 comentarios
    }
}
