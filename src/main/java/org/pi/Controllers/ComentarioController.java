package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Comentario;
import org.pi.Services.ComentarioService;

import java.sql.SQLException;
import java.util.List;

public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    public void findALL(Context ctx){
        try{
            List<Comentario> comentarios = comentarioService.findAllC();
            ctx.json(comentarios);
        }catch (Exception e){
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findComentario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("idRol"));
            Comentario comentario = comentarioService.findC(id);
            ctx.json(comentario);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void saveComentario(Context ctx) {
        try {
            Comentario comentario = ctx.bodyAsClass(Comentario.class);
            comentarioService.saveC(comentario);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteComentario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            comentarioService.deleteC(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public  void updateComentario(Context ctx){
        try{
            Comentario comentario = ctx.bodyAsClass(Comentario.class);
            comentarioService.updateC(comentario);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
