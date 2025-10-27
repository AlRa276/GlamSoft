package org.pi.Services;


import org.pi.Models.Persona;
import org.pi.Repositories.PersonaRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository pr) {
        this.personaRepository = pr;
    }

    public List<Persona> findAllPersona() throws SQLException {
        return personaRepository.findAllPersona();
    }
    public Persona findPersona(int id) throws SQLException{
        return personaRepository.findPersona(id);
    }

    public void savePersona(Persona persona) throws SQLException{
        personaRepository.savePersona(persona);
    }

    public void deletePersona(int id) throws SQLException{
        personaRepository.deletePersona(id);
    }

    public void updatePersona(int idPersona, Persona persona) throws SQLException{
        personaRepository.updatePersona(idPersona,persona);
    }
}
