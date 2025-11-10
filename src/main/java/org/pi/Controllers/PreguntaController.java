package org.pi.Controllers;

import io.javalin.http.Context;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.pi.Models.Pregunta;
import org.pi.Services.PortafolioService;
import org.pi.Services.PreguntaService;

import java.sql.SQLException;
import java.util.List;

public class PreguntaController {
    private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    public void findALL(Context ctx){
        try{
            List<Pregunta> preguntas = preguntaService.findAllP();
            ctx.json(preguntas);
        }catch (Exception e){
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findPregunta(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Pregunta pregunta  = preguntaService.findP(id);
            ctx.json(pregunta);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void savePregunta(Context ctx){
        try {
            Pregunta pregunta = ctx.bodyAsClass(Pregunta.class);
            preguntaService.saveP(pregunta);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deletePregunta(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            preguntaService.deleteP(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updatePregunta(Context ctx){
        try {
            Pregunta pregunta = ctx.bodyAsClass(Pregunta.class);
            preguntaService.updateP(pregunta);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
