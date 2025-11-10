package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Promocion;
import org.pi.Services.PromocionService;

import java.sql.SQLException;
import java.util.List;

public class PromocionController {
    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    public void findAll(Context ctx){
        try {
            List<Promocion> promociones = promocionService.findAllP();
            ctx.json(promociones);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findPromocion(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Promocion promocion = promocionService.findP(id);
            ctx.json(promocion);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void savePromocion(Context ctx){
        try {
            Promocion promocion = ctx.bodyAsClass(Promocion.class);
            promocionService.saveP(promocion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deletePromocion(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            promocionService.deleteP(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updatePromocion(Context ctx){
        try {
            Promocion promocion = ctx.bodyAsClass(Promocion.class);
            promocionService.updateP(promocion);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
