package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Valoracion;
import org.pi.Services.ValoracionService;

import java.sql.SQLException;
import java.util.List;

public class ValoracionController {
    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService ps) {
        this.valoracionService = ps;
    }

    public void findAllValoracion(Context ctx) {
        try {
            List<Valoracion> valoracions = valoracionService.findAllValoracion();
            ctx.json(valoracions);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void findValoracion(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Valoracion valoracion = valoracionService.findValoracion(id);
            ctx.json(valoracion);
        }catch (SQLException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void saveValoracion(Context ctx){
        try{
            Valoracion valoracion = ctx.bodyAsClass(Valoracion.class);
            valoracionService.saveValoracion(valoracion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        }catch (SQLException e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deleteValoracion(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            valoracionService.deleteValoracion(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updateValoracion(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Valoracion valoracion = ctx.bodyAsClass(Valoracion.class);
            valoracionService.updateValoracion(id,valoracion);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
