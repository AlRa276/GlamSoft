package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Horario;
import org.pi.Services.HorarioService;

import java.sql.SQLException;
import java.util.List;

public class HorarioController {
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public void findALL(Context ctx){
        try{
            List<Horario> horarios = horarioService.findAllH();
            ctx.json(horarios);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void saveHorario(Context ctx){
        try {
            Horario horario = ctx.bodyAsClass(Horario.class);
            horarioService.saveH(horario);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteHorario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            horarioService.deleteH(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void updateHorario(Context ctx){
        try{
            Horario horario = ctx.bodyAsClass(Horario.class);
            horarioService.updateH(horario);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
