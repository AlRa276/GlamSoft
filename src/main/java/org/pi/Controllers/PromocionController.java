package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Promocion;
import org.pi.Models.Servicio;
import org.pi.Services.PromocionService;

import java.sql.SQLException;
import java.util.List;

public class PromocionController {
    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    // 🔹 GET: listar todas las promociones
    public void findAll(Context ctx) {
        try {
            List<Promocion> promociones = promocionService.findAll();
            ctx.status(200).json(promociones);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener promociones: " + e.getMessage());
        }
    }

    // 🔹 GET: obtener una promoción por ID
    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Promocion promocion = promocionService.findById(id);
            ctx.status(200).json(promocion);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al buscar la promoción: " + e.getMessage());
        }
    }

    // 🔹 POST: crear una nueva promoción
    public void savePromocion(Context ctx) {
        try {
            Promocion promocion = ctx.bodyAsClass(Promocion.class);
            int idGenerado = promocionService.save(promocion);
            ctx.status(201).result("Promoción creada con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al crear la promoción: " + e.getMessage());
        }
    }

    // 🔹 PUT: actualizar promoción existente
    public void updatePromocion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Promocion promocion = ctx.bodyAsClass(Promocion.class);
            promocion.setIdPromocion(id);
            promocionService.update(promocion);
            ctx.status(200).result("Promoción actualizada correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al actualizar promoción: " + e.getMessage());
        }
    }

    // 🔹 DELETE: eliminar promoción
    public void deletePromocion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            promocionService.delete(id);
            ctx.status(200).result("Promoción eliminada correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar la promoción: " + e.getMessage());
        }
    }

    // 🔹 GET: obtener servicios de una promoción
    public void getServicios(Context ctx) {
        try {
            int idPromocion = Integer.parseInt(ctx.pathParam("id"));
            List<Servicio> servicios = promocionService.findServicios(idPromocion);
            ctx.status(200).json(servicios);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener servicios: " + e.getMessage());
        }
    }

    // 🔹 POST: asignar servicio a promoción
    public void saveServicio(Context ctx) {
        try {
            Promocion relacion = ctx.bodyAsClass(Promocion.class);
            promocionService.saveServicio(relacion);
            ctx.status(201).result("Servicio asignado correctamente a la promoción");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al asignar servicio: " + e.getMessage());
        }
    }
}
