package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Servicio;
import org.pi.Services.ServicioService;

import java.sql.SQLException;
import java.util.List;

public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    public void findALL(Context ctx){
        try {
            List<Servicio> servicios = servicioService.findAllS();
            ctx.json(servicios);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void findServicio(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Servicio servicio = servicioService.findS(id);
            ctx.json(servicio);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void saveServicio(Context ctx){
        try {
            Servicio servicio = ctx.bodyAsClass(Servicio.class);
            servicioService.saveS(servicio);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteServicio(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            servicioService.deleteS(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updateServicio(Context ctx){
        try {
            Servicio servicio = ctx.bodyAsClass(Servicio.class);
            servicioService.updateS(servicio);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
