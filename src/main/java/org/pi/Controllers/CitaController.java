package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Categoria;
import org.pi.Models.Cita;
import org.pi.Services.CitaService;

import java.sql.SQLException;
import java.util.List;

public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    public void findALL(Context ctx){
        try {
            List<Cita> citas = citaService.findAllCitas();
            ctx.json(citas);
        }catch (Exception e){
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findCita(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cita cita= citaService.findCita(id);
            ctx.json(cita);
        }catch (SQLException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void saveCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            citaService.saveCita(cita);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        }catch (SQLException e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteCita(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            citaService.deleteCita(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void statusCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            citaService.StatusCita(cita);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void fechaCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            citaService.fechaCita(cita);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
