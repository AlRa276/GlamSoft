package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.EstilistaServicio;
import org.pi.Models.Servicio;
import org.pi.Services.EstilistaServicioService;

import java.sql.SQLException;
import java.util.List;

public class EstilistaServicioController {
    private final EstilistaServicioService estilistaServicioService;

    public EstilistaServicioController(EstilistaServicioService estilistaServicioService) {
        this.estilistaServicioService = estilistaServicioService;
    }

    public void findALL(Context ctx){
        try{
            List<EstilistaServicio> relaciones = estilistaServicioService.findAllES();
            ctx.json(relaciones);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findServicios(Context ctx){
        try{
            int id  = Integer.parseInt(ctx.pathParam("id"));
            List<Servicio> servicios = estilistaServicioService.findES(id);
            ctx.json(servicios);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void saveRelacion(Context ctx ){
        try{
            EstilistaServicio relacion = ctx.bodyAsClass(EstilistaServicio.class);
            estilistaServicioService.saveES(relacion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteRelacion(Context ctx){
        try{
            int idEstilista = Integer.parseInt(ctx.pathParam("idEstilista"));
            int idServicio = Integer.parseInt(ctx.pathParam("idServicio"));
            estilistaServicioService.deleteES(idEstilista, idServicio);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
