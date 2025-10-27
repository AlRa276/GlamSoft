package org.pi.Services;

import org.pi.Models.Valoracion;
import org.pi.Repositories.ValoracionRepository;

import java.sql.SQLException;
import java.util.List;

public class ValoracionService {
    private final ValoracionRepository valoracionRepository;

    public ValoracionService(ValoracionRepository vr) {
        this.valoracionRepository = vr;
    }

    public List<Valoracion> findAllValoracion() throws SQLException {
        return valoracionRepository.findAllValoracion();
    }
    public Valoracion findValoracion(int id) throws SQLException{
        return valoracionRepository.findValoracion(id);
    }

    public void saveValoracion(Valoracion valoracion) throws SQLException{
        valoracionRepository.saveValoracion(valoracion);
    }

    public void deleteValoracion(int id) throws SQLException{
        valoracionRepository.deletevaloracion(id);
    }

    public void updateValoracion(int idValoracion, Valoracion valoracion) throws SQLException{
        valoracionRepository.updatevaloracion(idValoracion,valoracion);
    }
}
