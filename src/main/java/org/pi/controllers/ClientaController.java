package org.pi.controllers;
import org.pi.models.Clienta;
import org.pi.services.ClientaService;
import java.util.*;
import io.javalin.http.Context;

    public class ClientaController {

        private final ClientaService servicio =  new ClientaService();

        public void crearClient(Context ctx){
            Clienta nuevo = ctx.bodyAsClass(Clienta.class);
            ctx.json(servicio.crarClienta(nuevo));
        }
        public void verClients(Context ctx){
            List<Clienta> lista = servicio.verClientas();
            ctx.json(lista);
        }
        public void obtenerClienta(Context ctx){
            int id = Integer.parseInt(ctx.pathParam("id"));
            Clienta clienta = servicio.obtenerPorId(id);
            if (clienta != null){
                ctx.json(clienta);
            }else {
                ctx.result("Error. Clienta no encontarda.");
            }
        }

        public void actualizarDatos(Context ctx){
            int id = Integer.parseInt(ctx.pathParam("id"));
            Clienta datos = ctx.bodyAsClass(Clienta.class);
            Clienta actualizada = servicio.actualizarClienta(id, datos);
            if (actualizada != null){
                ctx.json(actualizada);
            }else {
                ctx.result("Error. Usuario no encontrado");
            }
        }

        public void eliminarClient(Context ctx){
            int id = Integer.parseInt(ctx.pathParam("id"));
            Boolean eliminada = servicio.eliminarClienta(id);
            if (eliminada){
                ctx.result("Clienta eliminada");
            }else {
                ctx.result("No se encontro a la clienta");
            }
        }

    }
