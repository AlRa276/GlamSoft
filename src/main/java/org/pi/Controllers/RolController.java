package org.pi.Controllers;


import io.javalin.http.Context;
import org.pi.Models.Rol;
import org.pi.Services.RolService;
import java.sql.SQLException;
import java.util.List;

public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // GET: Obtener todos los roles
    public void findAll(Context ctx) {
        try {
            List<Rol> roles = rolService.findAllRol();
            ctx.status(200).json(roles);
        } catch (SQLException e) {
            ctx.status(404).result("No se pudieron obtener los roles: " + e.getMessage());
        }
    }

    // GET: Obtener un rol por ID
    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Rol rol = rolService.findRol(id);
            ctx.status(200).json(rol);
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID del rol debe ser un número válido");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontró el rol: " + e.getMessage());
        }
    }

    // POST: Crear un nuevo rol
    public void saveRol(Context ctx) {
        try {
            Rol rol = ctx.bodyAsClass(Rol.class);
            rolService.saveRol(rol);
            ctx.status(201).result("Rol creado exitosamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Datos inválidos: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al guardar el rol: " + e.getMessage());
        }
    }

    // PUT: Actualizar un rol existente
    public void updateRol(Context ctx) {
        try {
            Rol rol = ctx.bodyAsClass(Rol.class);
            rolService.updateRol(rol);
            ctx.status(200).result("Rol actualizado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Datos inválidos: " + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al actualizar el rol: " + e.getMessage());
        }
    }

    // DELETE: Eliminar un rol por ID
    public void deleteRol(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            rolService.deleteRol(id);
            ctx.status(200).result("Rol eliminado correctamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("El ID debe ser un número válido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar el rol: " + e.getMessage());
        }
    }
}

