package org.pi.Controllers;
import io.javalin.http.Context;
import java.util.Map;
import org.pi.Models.Categoria;
import org.pi.Services.CategoriaService;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService cs) {
        this.categoriaService = cs;
    }

    public void findAllCategoria(Context ctx) {
        try {
            List<Categoria> categorias = categoriaService.findAllCategoria();
            if (categorias == null || categorias.isEmpty()){
                ctx.status(204).result("No se encontraron elementos");
            }else{
                ctx.json(categorias).status(200);
            }
        } catch (Exception e) {
            ctx.status(500).result("Error del sistema");
        }
    }
    public void findCategoria(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria categoria = categoriaService.findCategoria(id);
            ctx.json(categoria).status(200);

        }catch (SQLException e){
            ctx.status(500).result("Error del sistema");
        }catch (NumberFormatException e){
            ctx.status(400).result("El id debe ser un numero entero");
        }catch (IllegalArgumentException e){
            ctx.status(400).result("id invalido: " + e.getMessage());
        }catch (NoSuchElementException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void saveCategoria(Context ctx){
        try{
            Categoria categoria = ctx.bodyAsClass(Categoria.class);
            String nombre = categoria.getNombreCategoria();
            if (nombre == null || nombre.trim().isEmpty()){
                ctx.status(400).result("El nombre de la categoria es obligatorio");
                return;
            }
            int idGenerado = categoriaService.saveCategoria(categoria);
            ctx.status(201).json(Map.of(
                    "success", true,
                    "id_categoria", idGenerado
            ));

        }catch (IllegalArgumentException e) {
            if (e.getMessage().contains("nombre")) {
                ctx.status(409).result("Conflicto: " + e.getMessage());
            } else {
                ctx.status(400).result("ID inválido: " + e.getMessage());
            }
        } catch (SQLException e) {
            ctx.status(500).result("Error del sistema");
        }catch (NoSuchElementException e){
            ctx.status(404).result("No se encontro el elemento");
        } catch (Exception e){
            ctx.status(400).result("Error, " + e.getMessage());
        }
    }
    public void deleteCategoria(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            categoriaService.deleteCategoria(id);
            ctx.status(200);
        } catch (SQLException e){
            ctx.status(500).result("Error del sistema");
        }catch (NumberFormatException e){
            ctx.status(400).result("El id debe ser un numero entero");
        }catch (IllegalArgumentException e){
            ctx.status(400).result("id invalido: " + e.getMessage());
        }catch (NoSuchElementException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }

  }
