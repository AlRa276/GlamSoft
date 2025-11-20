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

    public List<Valoracion> findAll() throws SQLException {
        List<Valoracion> valoraciones = valoracionRepository.findAll();
        if (valoraciones.isEmpty()) {
            throw new SQLException("No se encontraron valoraciones registradas");
        }
        return valoraciones;
    }

    public int save(Valoracion valoracion) throws SQLException {
        if (valoracion == null) {
            throw new IllegalArgumentException("La valoración no puede ser nula");
        }

        if (valoracion.getPuntuacion() < 0 || valoracion.getPuntuacion() > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 0 y 5");
        }

        if (valoracion.getIdCita() <= 0 || valoracion.getIdCliente() <= 0 || valoracion.getIdServicio() <= 0) {
            throw new IllegalArgumentException("Los IDs de cita, cliente y servicio deben ser válidos");
        }

        return valoracionRepository.save(valoracion);
    }

    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la valoración debe ser válido");
        }
        valoracionRepository.delete(id);
    }
}

