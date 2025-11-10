package org.pi.Services;

import org.pi.Models.EstilistaServicio;
import org.pi.Models.Servicio;
import org.pi.Repositories.EstilistaHorarioRepository;
import org.pi.Repositories.EstilistaServicioRepository;

import java.sql.SQLException;
import java.util.List;

public class EstilistaServicioService {
    private final EstilistaServicioRepository estilistaServicioRepository;

    public EstilistaServicioService(EstilistaServicioRepository estilistaServicioRepository) {
        this.estilistaServicioRepository = estilistaServicioRepository;
    }

    public List<EstilistaServicio> findAllES()throws SQLException{
        return estilistaServicioRepository.findAll();
    }

    public List<Servicio> findES(int idEstilista)throws SQLException{
        return estilistaServicioRepository.find(idEstilista);
    }

    public void saveES(EstilistaServicio relacion) throws SQLException{
        estilistaServicioRepository.save(relacion);
    }

    public void deleteES(int idEstilista, int idServicio) throws SQLException{
        estilistaServicioRepository.delete(idEstilista, idServicio);
    }
}
