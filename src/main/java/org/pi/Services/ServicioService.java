package org.pi.Services;

import org.pi.Models.Servicio;
import org.pi.Repositories.ServicioRepository;

import java.sql.SQLException;
import java.util.List;

public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> findAllS() throws SQLException{
        return servicioRepository.findAll();
    }

    public Servicio findS(int id) throws SQLException{
        return servicioRepository.find(id);
    }

    public int saveS(Servicio servicio) throws SQLException{
        return servicioRepository.save(servicio);
    }

    public void deleteS(int id)throws SQLException{
        servicioRepository.delete(id);
    }

    public void updateS(Servicio servicio)throws SQLException{
        servicioRepository.update(servicio);
    }
}
