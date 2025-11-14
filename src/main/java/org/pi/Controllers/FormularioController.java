package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Formulario;
import org.pi.Repositories.FormularioRepository;
import org.pi.Services.FormularioService;

import java.sql.SQLException;
import java.util.List;

public class FormularioController {
    private final FormularioService formularioService;

    public FormularioController(FormularioService formularioService) {
        this.formularioService = formularioService;
    }


    public void findAll(Context ctx) {
        try {
            List<Formulario> formularios = formularioService.findAllFormulario();
            ctx.json(formularios);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener formularios: " + e.getMessage());
        }
    }


    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Formulario formulario = formularioService.findFormulario(id);
            ctx.json(formulario);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(404).result(e.getMessage());
        }
    }

    public void save(Context ctx) {
        try {
            Formulario formulario = ctx.bodyAsClass(Formulario.class);
            int idGenerado = formularioService.saveFormulario(formulario);
            ctx.status(201).result("Formulario creado con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al crear el formulario: " + e.getMessage());
        }
    }


    public void updateFormulario(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Formulario formulario = ctx.bodyAsClass(Formulario.class);
            formularioService.updateFormulario(formulario);
            ctx.status(200).result("Formulario actualizado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al actualizar el formulario: " + e.getMessage());
        }
    }


    public void deleteFormulario(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            formularioService.deleteFormulario(id);
            ctx.status(200).result("Formulario eliminado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar el formulario: " + e.getMessage());
        }
    }
}
