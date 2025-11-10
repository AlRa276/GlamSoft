package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.CitaServicio;
import org.pi.Services.CitaService;
import org.pi.Services.CitaServicioServices;

import java.sql.SQLException;
import java.util.List;

public class CitaServicioController {
    private final CitaServicioServices citaServicioServices;

    public CitaServicioController(CitaServicioServices citaServicioServices) {
        this.citaServicioServices = citaServicioServices;
    }

    public void findALL(Context ctx){
        try{
            List<CitaServicio> relaciones = citaServicioServices.findALLCS();
            ctx.json(relaciones);
        }catch (Exception e){
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findRelacion(Context ctx){
        try{
            int idCita = Integer.parseInt(ctx.pathParam("idCita"));
            int idServicio = Integer.parseInt(ctx.pathParam("idServicio"));
            CitaServicio relacion = citaServicioServices.findCS(idCita, idServicio);
            ctx.json(relacion);
        } catch (Exception e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void saveRelacion(Context ctx){
        try{
            CitaServicio relacion = ctx.bodyAsClass(CitaServicio.class);
            citaServicioServices.saveCS(relacion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        }catch (SQLException e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteRelacion(Context ctx){
        try{
            int idCita = Integer.parseInt(ctx.pathParam("idCita"));
            int idServicio = Integer.parseInt(ctx.pathParam("idServicio"));
            citaServicioServices.deleteCS(idCita, idServicio);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
