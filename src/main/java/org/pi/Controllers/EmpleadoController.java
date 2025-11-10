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

    public void findALL(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Empleado> empleados = empleadoService.findAllE(id);
            ctx.json(empleados);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }

    public void findEmpleado(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Empleado empleado = empleadoService.findE(id);
            ctx.json(empleado);
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void saveEmpleado(Context ctx){
        try{
            Empleado empleado = ctx.bodyAsClass(Empleado.class);
            empleadoService.saveE(empleado);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteEmpleado(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            empleadoService.deleteE(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
