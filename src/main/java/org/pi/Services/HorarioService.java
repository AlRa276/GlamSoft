package org.pi.Services;


import org.pi.Models.Horario;
import org.pi.Repositories.HorarioRepository;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    // 🔹 Obtener todos los horarios
    public List<Horario> findAll() throws SQLException {
        return horarioRepository.findAll();
    }

    // 🔹 Guardar un nuevo horario
    public int save(Horario horario) throws SQLException {
        // Validaciones
        if (horario.getHoraInicio() == null || horario.getHoraFin() == null) {
            throw new IllegalArgumentException("Las horas de inicio y fin no pueden ser nulas.");
        }
        if (horario.getHoraFin().isBefore(horario.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la de inicio.");
        }
        if (horario.getDiaSemana() == null || horario.getDiaSemana().isBlank()) {
            throw new IllegalArgumentException("El día de la semana es obligatorio.");
        }

        return horarioRepository.save(horario);
    }

    // 🔹 Eliminar horario por ID
    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero.");
        }
        horarioRepository.delete(id);
    }

    // 🔹 Actualizar horario existente
    public void update(Horario horario) throws SQLException {
        if (horario.getIdHorario() <= 0) {
            throw new IllegalArgumentException("El ID del horario es inválido.");
        }
        if (horario.getHoraInicio() == null || horario.getHoraFin() == null) {
            throw new IllegalArgumentException("Las horas de inicio y fin no pueden ser nulas.");
        }
        if (horario.getHoraFin().isBefore(horario.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la de inicio.");
        }
        if (horario.getDiaSemana() == null || horario.getDiaSemana().isBlank()) {
            throw new IllegalArgumentException("El día de la semana es obligatorio.");
        }

        horarioRepository.update(horario);
    }
}

