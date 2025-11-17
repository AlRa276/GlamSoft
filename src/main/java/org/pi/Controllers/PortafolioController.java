package org.pi.Controllers;


import io.javalin.http.Context;
import org.pi.Models.Portafolio;
import org.pi.Services.PortafolioService;

import java.sql.SQLException;
import java.util.List;

public class PortafolioController {

    private final PortafolioService portafolioService;

    public PortafolioController(PortafolioService portafolioService) {
        this.portafolioService = portafolioService;
    }

    public void find4(Context ctx){
        try{
            List<Portafolio> portafolios = portafolioService.find4();
            ctx.status(200).json(portafolios);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el portafolio: " + e.getMessage());
        }
    }
    public void findAll(Context ctx) {
        try {
            List<Portafolio> portafolios = portafolioService.findAll();
            ctx.status(200).json(portafolios);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el portafolio: " + e.getMessage());
        }
    }
    //GuardarImagen
    public void savePortafolio(Context ctx) {
        try {
            Portafolio portafolio = ctx.bodyAsClass(Portafolio.class);
            int idGenerado = portafolioService.save(portafolio);
            ctx.status(201).result("Imagen agregada al portafolio con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Error de validación: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }


    public void updatePortafolio(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Portafolio portafolio = ctx.bodyAsClass(Portafolio.class);
            portafolio.setIdImagen(id);
            portafolioService.update(portafolio);
            ctx.status(200).result("Imagen actualizada correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }


    public void deletePortafolio(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            portafolioService.delete(id);
            ctx.status(200).result("Imagen eliminada correctamente.");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido.");
        } catch (SQLException e) {
            ctx.status(500).result("Error en la base de datos: " + e.getMessage());
        }
    }
}

