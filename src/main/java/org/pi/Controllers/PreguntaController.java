package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Pregunta;
import org.pi.Services.PreguntaService;

import java.sql.SQLException;
import java.util.List;

public class PreguntaController {

    private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    public void findPreFormulario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Pregunta> preguntas = preguntaService.findPreFormulario(id);
            ctx.status(200).json(preguntas);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener las preguntas: " + e.getMessage());
        }
    }
    public void findAll(Context ctx) {
        try {
            List<Pregunta> preguntas = preguntaService.findAll();
            ctx.status(200).json(preguntas);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener las preguntas: " + e.getMessage());
        }
    }

    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Pregunta pregunta = preguntaService.find(id);
            ctx.status(200).json(pregunta);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            ctx.status(404).result(e.getMessage());
        }
    }

    public void savePregunta(Context ctx) {
        try {
            Pregunta pregunta = ctx.bodyAsClass(Pregunta.class);
            int idGenerado = preguntaService.save(pregunta);
            ctx.status(201).result("Pregunta creada con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Error de validación: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al guardar la pregunta: " + e.getMessage());
        }
    }

    public void updatePregunta(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Pregunta pregunta = ctx.bodyAsClass(Pregunta.class);
            pregunta.setIdPregunta(id);
            preguntaService.update(pregunta);
            ctx.status(200).result("Pregunta actualizada correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            ctx.status(404).result(e.getMessage());
        }
    }

    public void deletePregunta(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            preguntaService.delete(id);
            ctx.status(200).result("Pregunta eliminada correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar la pregunta: " + e.getMessage());
        } catch (Exception e) {
            ctx.status(404).result(e.getMessage());
        }
    }
}

