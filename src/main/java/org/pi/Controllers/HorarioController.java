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


    public void findAll(Context ctx) {
        try {
            List<Horario> horarios = horarioService.findAll();
            ctx.status(200).json(horarios);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener los horarios: " + e.getMessage());
        }
    }


    public void saveHorario(Context ctx) {
        try {
            Horario horario = ctx.bodyAsClass(Horario.class);
            int idGenerado = horarioService.save(horario);
            ctx.status(201).result("Horario creado con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Error de validación: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }


    public void updateHorario(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Horario horario = ctx.bodyAsClass(Horario.class);
            horario.setIdHorario(id);
            horarioService.update(horario);
            ctx.status(200).result("Horario actualizado correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }


    public void deleteHorario(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            horarioService.delete(id);
            ctx.status(200).result("Horario eliminado correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }
}
