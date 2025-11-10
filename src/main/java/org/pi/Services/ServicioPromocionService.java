package org.pi.Services;

import org.pi.Models.Servicio;
import org.pi.Models.ServicioPromocion;
import org.pi.Repositories.ServicioPromocionRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.ServiceLoader;

public class ServicioPromocionService {
    private final ServicioPromocionRepository servicioPromocionRepository;

    public ServicioPromocionService(ServicioPromocionRepository servicioPromocionRepository) {
        this.servicioPromocionRepository = servicioPromocionRepository;
    }

    public List<ServicioPromocion> findAllSP() throws SQLException{
        return servicioPromocionRepository.findAll();
    }

    public List<Servicio> findSP(int idPromocion)throws SQLException{
        return servicioPromocionRepository.find(idPromocion);
    }

    public void save(ServicioPromocion relacion)throws SQLException{
        servicioPromocionRepository.save(relacion);
    }

    public void deleteSP(int idServicio, int idPromocion)throws SQLException{
        servicioPromocionRepository.delete(idServicio, idPromocion);
    }
}
