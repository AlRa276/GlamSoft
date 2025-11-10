package org.pi.Controllers;

import io.javalin.http.Context;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.pi.Models.EstilistaHorario;
import org.pi.Models.Horario;
import org.pi.Services.ComentarioService;
import org.pi.Services.EstilistaHorarioService;

import java.sql.SQLException;
import java.util.List;

public class EstilistaHorarioController {
    private final EstilistaHorarioService estilistaHorarioService;

    public EstilistaHorarioController(EstilistaHorarioService estilistaHorarioService) {
        this.estilistaHorarioService = estilistaHorarioService;
    }

    public void findALL(Context ctx){
        try{
            List<EstilistaHorario> relaciones = estilistaHorarioService.findAllEH();
            ctx.json(relaciones);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void findHorarios(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Horario> horarios = estilistaHorarioService.findEH(id);
            ctx.json(horarios);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void saveRelaciones(Context ctx){
        try{
            EstilistaHorario relacion = ctx.bodyAsClass(EstilistaHorario.class);
            estilistaHorarioService.saveEH(relacion);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteRelacion(Context ctx){
        try{
            int idEstilista = Integer.parseInt(ctx.pathParam("idEstilista"));
            int idHorario = Integer.parseInt(ctx.pathParam("idHorario"));
            estilistaHorarioService.deleteEH(idEstilista, idHorario);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
