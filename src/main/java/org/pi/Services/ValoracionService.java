package org.pi.Services;

import org.pi.Models.Valoracion;
import org.pi.Repositories.ValoracionRepository;

import java.sql.SQLException;
import java.util.List;

public class ValoracionService {
    private final ValoracionRepository valoracionRepository;

    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<Valoracion> findAllV() throws SQLException{
        return valoracionRepository.findAll();
    }

    public int saveS(Valoracion valoracion) throws SQLException{
        return valoracionRepository.save(valoracion);
    }

    public void deleteS(int id)throws SQLException{
        valoracionRepository.delete(id);
    }
}
