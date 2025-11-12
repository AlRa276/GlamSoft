package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Comentario;
import org.pi.Services.ComentarioService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    public void findALL(Context ctx){
        try{
            List<Comentario> comentarios = comentarioService.findAllC();
            if (comentarios == null || comentarios.isEmpty()){
                ctx.status(204).result("No se encontraron elementos");
            }else {
                ctx.json(comentarios);
                ctx.status(200);
            }
            ctx.json(comentarios);
        }catch (Exception e){
            ctx.status(500).result("Error del sistema");
        }
    }

    public void findComentario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("idRol"));
            Comentario comentario = comentarioService.findC(id);
            if (comentario == null){
                ctx.status(204).result("No se encontro el elemento");
            }else {
                ctx.json(comentario);
                ctx.status(200);
            }
        } catch (SQLException e) {
            ctx.status(500).result("Error del sistema");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El id debe ser un numero entero");
        }
    }

    public void saveComentario(Context ctx) {
        try {
            Comentario comentario = ctx.bodyAsClass(Comentario.class);
            String texto = comentario.getComentario();
                int idGenerado =   comentarioService.saveC(comentario);
                if (idGenerado != 0) {
                    ctx.status(201).json(Map.of(
                            "success", true,
                            "id_comentario", idGenerado
                    ));
                }

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
            ctx.status(500).result("Error del sistema");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El id debe ser un numero entero");
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
