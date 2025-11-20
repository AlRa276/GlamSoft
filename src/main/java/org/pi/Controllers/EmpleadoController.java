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


    public void findAll(Context ctx) {
        

        try {
            int idRol = Integer.parseInt(ctx.pathParam("id"));
            List<Empleado> empleados = empleadoService.findAll(idRol);
            if (empleados == null || empleados.isEmpty()) {
                ctx.status(204).result("No se encontraron empleados");
                return;
            }else{
            ctx.status(200).json(empleados);
        }
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("id invalido" + e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener empleados: " + e.getMessage());
        }
    }

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
}
