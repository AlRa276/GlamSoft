package org.pi.Controllers;

import io.javalin.http.Context;
import org.pi.Models.Persona;
import org.pi.Services.PersonaService;

import java.sql.SQLException;
import java.util.List;

public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService ps) {
        this.personaService = ps;
    }

    public void findAllpersona(Context ctx) {
        try {
            List<Persona> personas = personaService.findAllPersona();
            ctx.json(personas);
        } catch (SQLException e) {
            ctx.status(404).result("Elementos no encontrados");
        }
    }
    public void findPersona(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Persona persona = personaService.findPersona(id);
            ctx.json(persona);
        }catch (SQLException e){
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void savePersona(Context ctx){
        try{
            Persona persona = ctx.bodyAsClass(Persona.class);
            personaService.savePersona(persona);
            ctx.status(201).result("Se ha creado un nuevo recurso con exito");
        }catch (SQLException e){
            ctx.status(400).result("El recurso no se puede crear");
        }
    }
    public void deletePersona(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            personaService.deletePersona(id);
            ctx.status(204).result("Se elimino el recurso con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
    }
    public void updatePersona(Context ctx){
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Persona persona = ctx.bodyAsClass(Persona.class);
            personaService.updatePersona(id,persona);
            ctx.status(204).result("Se creo el elemento con exito");
        } catch (SQLException e) {
            ctx.status(404).result("No se encontro el elemento");
        }
}}
