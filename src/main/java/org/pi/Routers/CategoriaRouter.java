package org.pi.Routers;
import io.javalin.Javalin;
import org.pi.Controllers.CategoriaController;

public class CategoriaRouter {
    private final CategoriaController categoriaController;

    public CategoriaRouter(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    public void register(Javalin app){
        app.get("/categorias",categoriaController::findAllCategoria);
        app.get("/categorias/{id}",categoriaController::findCategoria);
        app.post("/categorias",categoriaController::saveCategoria);
        app.delete("/categorias/{id}",categoriaController::deleteCategoria);
    }
}
