package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Rol;
import org.pi.Services.RolService;

import java.sql.SQLException;
import java.util.List;

public class RolController {

        private final RolService rolService;

        public RolController(RolService rs) {

            this.rolService = rs;
        }

        public void findAllRol(Context ctx) {
            try {
                List<Rol> roles = rolService.findAllRol();
                ctx.json(roles);
            } catch (SQLException e) {
                ctx.status(404).result("Elementos no encontrados");
            }
        }
        public void findRol(Context ctx){
            try{
                int idRol = Integer.parseInt(ctx.pathParam("id_rol"));
                Rol rol = rolService.findRol(idRol);
                ctx.json(rol);
            }catch (SQLException e){
                ctx.status(404).result("No se encontro el elemento");
            }
        }
        public void saveRol(Context ctx){
            try{
                Rol rol = ctx.bodyAsClass(Rol.class);
                rolService.saveRol(rol);
                ctx.status(201).result("Se ha creado un nuevo recurso con exito");
            }catch (SQLException e){
                ctx.status(400).result("El recurso no se puede crear");
            }
        }
        public void deleteRol(Context ctx){
            try {
                int idRol = Integer.parseInt(ctx.pathParam("id_rol"));
                rolService.deleteRol(idRol);
                ctx.status(204).result("Se elimino el recurso con exito");
            } catch (SQLException e) {
                ctx.status(404).result("No se encontro el elemento");
            }
        }
        public void updateRol(Context ctx){
            try{
                int id = Integer.parseInt(ctx.pathParam("id"));
                Rol rol = ctx.bodyAsClass(Rol.class);
                rolService.updateRol(id,rol);
                ctx.status(204).result("Se creo el elemento con exito");
            } catch (SQLException e) {
                ctx.status(404).result("No se encontro el elemento");
            }
        }

    }
