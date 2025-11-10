package org.pi.Services;

import org.pi.Models.CitaServicio;
import org.pi.Repositories.CitaServicioRepository;

import java.sql.SQLException;
import java.util.List;

public class CitaServicioServices {
    private final CitaServicioRepository citaServicioRepository;

    public CitaServicioServices(CitaServicioRepository citaServicioRepository) {
        this.citaServicioRepository = citaServicioRepository;
    }

    public List<CitaServicio> findALLCS()throws SQLException{
        return citaServicioRepository.findAll();
    }

    public CitaServicio findCS(int idCita, int idServicio)throws SQLException {
        return citaServicioRepository.find(idCita, idServicio);
    }

    public void saveCS(CitaServicio relacion)throws SQLException{
        citaServicioRepository.save(relacion);
    }

    public void deleteCS(int idCita, int idServicio)throws SQLException{
        citaServicioRepository.delete(idCita, idServicio);
    }

}
