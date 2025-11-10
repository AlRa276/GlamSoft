package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Formulario;
import org.pi.Services.FormularioService;

import java.sql.SQLException;
import java.util.List;

public class FormularioController {
    private final FormularioService formularioService;

    public FormularioController(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    public void findAll(Context ctx){
        try{
            List<Formulario> formularios = formularioService.findAllF();
            ctx.json(formularios);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void saveFormulario(Context ctx){
        try{
            Formulario formulario = ctx.bodyAsClass(Formulario.class);
            formularioService.saveF(formulario);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deleteFormulario(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            formularioService.deleteF(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updateFormulario(Context ctx){
        try{
            Formulario formulario = ctx.bodyAsClass(Formulario.class);
            formularioService.update(formulario);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
