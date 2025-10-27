package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Categoria;
import org.pi.Services.CategoriaService;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService cs) {
        this.categoriaService = cs;
    }

    public void findAllCategoria(Context ctx) {
        try {
            List<Categoria> categorias = categoriaService.findAllCategoria();
            ctx.json(categorias);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void findCategoria(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = categoriaService.findCategoria(id);
            ctx.json(categoria);
        }catch (SQLException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void saveCategoria(Context ctx){
        try{
            Categoria categoria = ctx.bodyAsClass(Categoria.class);
            categoriaService.saveCategoria(categoria);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        }catch (SQLException e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deleteCategoria(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            categoriaService.deleteCategoria(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updateCategoria(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = ctx.bodyAsClass(Categoria.class);
            categoriaService.updateCategoria(id,categoria);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }


}
