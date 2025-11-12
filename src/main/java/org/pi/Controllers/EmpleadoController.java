package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Empleado;
import org.pi.Services.EmpleadoService;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // 🔹 GET: listar empleados por rol
    public void findAll(Context ctx) {
        try {
            int idRol = Integer.parseInt(ctx.pathParam("idRol"));
            List<Empleado> empleados = empleadoService.findAll(idRol);
            ctx.status(200).json(empleados);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener empleados: " + e.getMessage());
        }
    }

    // 🔹 GET: buscar empleado por id
    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Empleado empleado = empleadoService.findById(id);
            ctx.status(200).json(empleado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el empleado: " + e.getMessage());
        }
    }

    // 🔹 POST: guardar empleado
    public void save(Context ctx) {
        try {
            Empleado empleado = ctx.bodyAsClass(Empleado.class);
            int idGenerado = empleadoService.save(empleado);
            ctx.status(201).result("Empleado creado con ID: " + idGenerado);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al guardar el empleado: " + e.getMessage());
        }
    }

    // 🔹 PUT: actualizar empleado
    public void update(Context ctx) {
        try {
            Empleado empleado = ctx.bodyAsClass(Empleado.class);
            empleadoService.update(empleado);
            ctx.status(200).result("Empleado actualizado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    // 🔹 DELETE: eliminar empleado
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            empleadoService.delete(id);
            ctx.status(200).result("Empleado eliminado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al eliminar el empleado: " + e.getMessage());
        }
    }
}
