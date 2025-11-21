package org.pi.Controllers;


import io.javalin.http.Context;
import org.pi.Models.Cita;
import org.pi.Models.Estilista;
import org.pi.Models.Horario;
import org.pi.Models.Servicio;
import org.pi.Services.EstilistaService;
import org.pi.dto.EstilistaDTO;
import java.util.Map;
import java.sql.SQLException;
import java.util.List;

public class EstilistaController {
    private final EstilistaService estilistaService;

    public EstilistaController(EstilistaService estilistaService) {
        this.estilistaService = estilistaService;
    }

    public void fidnEstilistaServicio(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cita fecha = ctx.bodyAsClass(Cita.class);
            List<Estilista> estilistas = estilistaService.findEstilistaServicio(id, fecha);
            ctx.status(200).json(estilistas);
        } catch (IllegalArgumentException e) {
            // Error de formato o ID inválido
            ctx.status(400).json(Map.of("error", "Datos de solicitud inválidos: " + e.getMessage()));
        } catch (Exception e) {
            // Errores internos de BD (SQLException, etc.)
            ctx.status(500).json(Map.of("error", "Error interno al filtrar estilistas: " + e.getMessage()));
        }
    }
    public void findAll(Context ctx) {
        try {
            List<EstilistaDTO> estilistas = estilistaService.findAllEstilistas();
            ctx.status(200).json(estilistas);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener los estilistas: " + e.getMessage());
        }
    }


    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            EstilistaDTO estilista = estilistaService.findEstilistaById(id);
            ctx.status(200).json(estilista);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el estilista: " + e.getMessage());
        }
    }

    public void findHorarios(Context ctx) {
        try {
            int idEstilista = Integer.parseInt(ctx.pathParam("id"));
            List<Horario> horarios = estilistaService.findHorarios(idEstilista);
            ctx.status(200).json(horarios);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener horarios: " + e.getMessage());
        }
    }

    public void findServicios(Context ctx) {
        try {
            int idEstilista = Integer.parseInt(ctx.pathParam("id"));
            List<Servicio> servicios = estilistaService.findServicios(idEstilista);
            ctx.status(200).json(servicios);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener servicios: " + e.getMessage());
        }
    }


    public void saveHorario(Context ctx) {
        try {
            Estilista estilista = ctx.bodyAsClass(Estilista.class);
            estilistaService.saveHorario(estilista);
            ctx.status(201).result("Horario asignado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al asignar horario: " + e.getMessage());
        }
    }


    public void saveServicios(Context ctx) {
        try {
            Estilista estilista = ctx.bodyAsClass(Estilista.class);
            estilistaService.saveServicio(estilista);
            ctx.status(201).result("Servicio asignado correctamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (SQLException e) {
            ctx.status(500).result("Error al asignar servicio: " + e.getMessage());
        }
    }
}

