package org.pi.Services;

import org.pi.Models.Cita;
import org.pi.Repositories.CitaRepository;

import java.sql.SQLException;
import java.util.List;

public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> findAllCitas() throws SQLException{
        return citaRepository.findAll();
    }

    public Cita findCita(int id) throws SQLException{
        return citaRepository.findById(id);
    }

    public int saveCita(Cita cita)throws SQLException{
        return citaRepository.save(cita);
    }

    public void deleteCita(int id) throws SQLException{
        citaRepository.delete(id);
    }

    public void StatusCita(Cita cita) throws SQLException{
        citaRepository.updateStatus(cita);
    }

    public void fechaCita(Cita cita)throws SQLException{
        citaRepository.updateFecha(cita);
    }
}