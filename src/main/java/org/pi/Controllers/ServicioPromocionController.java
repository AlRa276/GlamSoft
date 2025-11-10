package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Servicio;
import org.pi.Models.ServicioPromocion;
import org.pi.Services.ServicioPromocionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServicioPromocionController {
    private final ServicioPromocionService servicioPromocionService;

    public ServicioPromocionController(ServicioPromocionService servicioPromocionService) {
        this.servicioPromocionService = servicioPromocionService;
    }

    public void findALL(Context ctx){
        try {
            List<ServicioPromocion> relaciones = servicioPromocionService.findAllSP();
            ctx.json(relaciones);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findRelacion(Context ctx){
        try {
            int idPromocion = Integer.parseInt(ctx.pathParam("id"));
            List<Servicio> servicios = servicioPromocionService.findSP( idPromocion);
            ctx.json(servicios);
        } catch (Exception e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void saveRelacion(Context ctx){
        try {
            ServicioPromocion relacion = ctx.bodyAsClass(ServicioPromocion.class);
            servicioPromocionService.save(relacion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteRelacion(Context ctx){
        try {
            int idServicio = Integer.parseInt(ctx.pathParam("idServicio"));
            int idPromocion = Integer.parseInt(ctx.pathParam("idPromocion"));
            servicioPromocionService.deleteSP(idServicio, idPromocion);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
