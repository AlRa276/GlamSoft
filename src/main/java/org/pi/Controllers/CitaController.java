package org.pi.Controllers;
import io.javalin.http.Context;
import org.pi.Models.Categoria;
import org.pi.Models.Cita;
import org.pi.Services.CitaService;
import org.pi.dto.CitaDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    public void findALL(Context ctx){
        try {
            List<CitaDTO> citas = citaService.findAllCitas();
            if (citas == null || citas.isEmpty()){
                ctx.status(204).result("No se encontraron elementos");
            }else {
            ctx.json(citas);
            ctx.status(200);
            }
        }catch (Exception e){
            ctx.status(500).result("Error del sistema");
        }
    }

    public void findCita(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            CitaDTO cita= citaService.findCita(id);
            if (cita == null){
                ctx.status(204).result("No se encontro el elemento");
            }else {
            ctx.json(cita);
            ctx.status(200);
            }
        }catch (SQLException e){
            ctx.status(500).result("Error del sistema");
        }catch (NumberFormatException en){
            ctx.status(400).result("El id debe ser un numero entero");
        }
    }

    public void saveCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            if (cita != null && cita.getFechaCita() != null){
                int idGenerado =   citaService.saveCita(cita);
                ctx.status(201).json(Map.of(
                        "success", true,
                        "id_categoria", idGenerado
                ));
            }

        }catch (Exception e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deleteCita(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            citaService.deleteCita(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }catch (NumberFormatException en){
            ctx.status(400).result("El id debe ser un numero entero");
        }
    }

    public void statusCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            if (cita.getEstadoCita() == null || cita.getFechaCita() == null){
                ctx.status(400).result("faltan datos necesarios para actualizar la cita");
            }else {
                citaService.StatusCita(cita);
                ctx.status(204).result("Se actualizo el recurso con exito");
            }
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }

    public void fechaCita(Context ctx){
        try{
            Cita cita = ctx.bodyAsClass(Cita.class);
            if (cita.getEstadoCita() == null || cita.getFechaCita() == null){
                ctx.status(400).result("faltan datos necesarios para actualizar la cita");
            }else {
                citaService.fechaCita(cita);
                ctx.status(204).result("Se actualizo el recurso con exito");
            }
        } catch (Exception e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
