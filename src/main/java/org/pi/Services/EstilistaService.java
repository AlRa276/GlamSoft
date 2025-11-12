package org.pi.Services;
import org.pi.Models.Estilista;
import org.pi.Models.Horario;
import org.pi.Models.Servicio;
import org.pi.Repositories.EstilistaRepository;
import org.pi.dto.EstilistaDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class EstilistaService {
    private final EstilistaRepository estilistaRepository;

    public EstilistaService(EstilistaRepository estilistaRepository) {
        this.estilistaRepository = estilistaRepository;
    }

    // 🔹 Listar todos los estilistas con sus datos combinados
    public List<EstilistaDTO> findAllEstilistas() throws SQLException {
        return estilistaRepository.findAllEstilistas();
    }

    // 🔹 Buscar un estilista específico por ID
    public EstilistaDTO findEstilistaById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del estilista debe ser mayor a cero");
        }

        EstilistaDTO estilista = estilistaRepository.findEstilistaById(id);
        if (estilista == null) {
            throw new NoSuchElementException("No se encontró el estilista con el ID especificado");
        }
        return estilista;
    }

    // 🔹 Obtener horarios del estilista
    public List<Horario> findHorarios(int idEstilista) throws SQLException {
        if (idEstilista <= 0) {
            throw new IllegalArgumentException("El ID del estilista debe ser mayor a cero");
        }

        List<Horario> horarios = estilistaRepository.findHorarios(idEstilista);
        if (horarios.isEmpty()) {
            throw new NoSuchElementException("El estilista no tiene horarios asignados");
        }

        return horarios;
    }

    // 🔹 Obtener servicios del estilista
    public List<Servicio> findServicios(int idEstilista) throws SQLException {
        if (idEstilista <= 0) {
            throw new IllegalArgumentException("El ID del estilista debe ser mayor a cero");
        }

        List<Servicio> servicios = estilistaRepository.findServicios(idEstilista);
        if (servicios.isEmpty()) {
            throw new NoSuchElementException("El estilista no tiene servicios asignados");
        }

        return servicios;
    }

    // 🔹 Asignar un horario a un estilista
    public void saveHorario(Estilista estilista) throws SQLException {
        if (estilista.getIdEmpleado() <= 0) {
            throw new IllegalArgumentException("El ID del estilista debe ser mayor a cero");
        }
        if (estilista.getIdHorario() <= 0) {
            throw new IllegalArgumentException("El ID del horario debe ser mayor a cero");
        }

        // Evitar asignación duplicada
        List<Horario> horariosExistentes = estilistaRepository.findHorarios(estilista.getIdEmpleado());
        boolean yaAsignado = horariosExistentes.stream()
                .anyMatch(h -> h.getIdHorario() == estilista.getIdHorario());

        if (yaAsignado) {
            throw new IllegalArgumentException("El horario ya está asignado a este estilista");
        }

        estilistaRepository.saveHorarios(estilista);
    }

    // 🔹 Asignar un servicio a un estilista
    public void saveServicio(Estilista estilista) throws SQLException {
        if (estilista.getIdEmpleado() <= 0) {
            throw new IllegalArgumentException("El ID del estilista debe ser mayor a cero");
        }
        if (estilista.getIdServicio() <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser mayor a cero");
        }

        // Evitar asignación duplicada
        List<Servicio> serviciosExistentes = estilistaRepository.findServicios(estilista.getIdEmpleado());
        boolean yaAsignado = serviciosExistentes.stream()
                .anyMatch(s -> s.getIdServicio() == estilista.getIdServicio());

        if (yaAsignado) {
            throw new IllegalArgumentException("El servicio ya está asignado a este estilista");
        }

        estilistaRepository.saveServicios(estilista);
    }
}

