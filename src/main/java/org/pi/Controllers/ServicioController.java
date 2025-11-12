package org.pi.Controllers;


import io.javalin.http.Context;
import org.pi.Models.Servicio;
import org.pi.Services.ServicioService;

import java.sql.SQLException;
import java.util.List;

public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // 🔹 Obtener todos los servicios
    public void findAll(Context ctx) {
        try {
            List<Servicio> servicios = servicioService.findAllServicios();
            ctx.json(servicios);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener servicios: " + e.getMessage());
        }
    }

    // 🔹 Obtener un servicio por ID
    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Servicio servicio = servicioService.findServicio(id);
            ctx.json(servicio);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al buscar el servicio: " + e.getMessage());
        }
    }

    // 🔹 Crear un nuevo servicio
    public void saveServicio(Context ctx) {
        try {
            Servicio servicio = ctx.bodyAsClass(Servicio.class);
            int idGenerado = servicioService.saveServicio(servicio);
            ctx.status(201).result("Servicio creado con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al crear el servicio: " + e.getMessage());
        }
    }

    // 🔹 Actualizar servicio existente
    public void updateServicio(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Servicio servicio = ctx.bodyAsClass(Servicio.class);
            servicioService.updateServicio(id, servicio);
            ctx.status(200).result("Servicio actualizado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al actualizar el servicio: " + e.getMessage());
        }
    }

    // 🔹 Eliminar servicio
    public void deleteServicio(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            servicioService.deleteServicio(id);
            ctx.status(200).result("Servicio eliminado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar el servicio: " + e.getMessage());
        }
    }
}

