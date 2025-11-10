package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Valoracion;
import org.pi.Services.ValoracionService;

import java.sql.SQLException;
import java.util.List;

public class ValoracionController {
    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    public void findALLvaloracio(Context ctx){
        try {
            List<Valoracion> valoraciones = valoracionService.findAllV();
            ctx.json(valoraciones);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void saveValoracion(Context ctx){
        try {
            Valoracion valoracion = ctx.bodyAsClass(Valoracion.class);
            valoracionService.saveS(valoracion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deleteValoracion(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            valoracionService.deleteS(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
