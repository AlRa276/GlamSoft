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

    public void findALL(Context ctx){
        try{
            List<Portafolio> imagenes = portafolioService.findAllP();
            ctx.json(imagenes);
        }catch (Exception e){
        ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void savePortafolio(Context ctx){
        try{
            Portafolio portafolio = ctx.bodyAsClass(Portafolio.class);
            portafolioService.saveP(portafolio);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        } catch (SQLException e) {
            ctx.status(400).result("El recurso no se puede crear");
        }
    }

    public void deletePortafolio(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            portafolioService.deleteP(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updatePortafolio(Context ctx){
        try{
            Portafolio portafolio = ctx.bodyAsClass(Portafolio.class);
            portafolioService.updateP(portafolio);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
}
