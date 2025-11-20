package org.pi.Controllers;


import io.javalin.http.Context;
import org.pi.Models.Valoracion;
import org.pi.Services.ValoracionService;
import java.sql.SQLException;
import java.util.List;

public class ValoracionController {
    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }


    public void findAll(Context ctx) {
        try {
            List<Valoracion> valoraciones = valoracionService.findAll();
            ctx.status(200).json(valoraciones);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontraron valoraciones: " + e.getMessage());
        }
    }


    public void saveValoracion(Context ctx) {
        try {
            Valoracion valoracion = ctx.bodyAsClass(Valoracion.class);
            int idGenerado = valoracionService.save(valoracion);
            ctx.status(201).result("Valoración creada exitosamente con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Datos inválidos: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al guardar la valoración: " + e.getMessage());
        }
    }

    public void deleteValoracion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            valoracionService.delete(id);
            ctx.status(200).result("Valoración eliminada correctamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar la valoración: " + e.getMessage());
        }
    }
}
