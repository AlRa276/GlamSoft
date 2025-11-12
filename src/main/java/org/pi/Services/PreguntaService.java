package org.pi.Services;

import org.pi.Models.Pregunta;
import org.pi.Repositories.PreguntaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class PreguntaService {

    private final PreguntaRepository preguntaRepository;

    public PreguntaService(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    // 🔹 Obtener todas las preguntas
    public List<Pregunta> findAll() throws SQLException {
        return preguntaRepository.findAll();
    }

    // 🔹 Obtener una pregunta por ID
    public Pregunta find(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero.");
        }

        Pregunta pregunta = preguntaRepository.find(id);
        if (pregunta == null) {
            throw new NoSuchElementException("No se encontró la pregunta con ID: " + id);
        }

        return pregunta;
    }

    // 🔹 Guardar una nueva pregunta
    public int save(Pregunta pregunta) throws SQLException {
        if (pregunta.getPregunta() == null || pregunta.getPregunta().isBlank()) {
            throw new IllegalArgumentException("El texto de la pregunta es obligatorio.");
        }
        if (pregunta.getIdFormulario() <= 0) {
            throw new IllegalArgumentException("Debe asignarse un formulario válido.");
        }

        return preguntaRepository.save(pregunta);
    }

    // 🔹 Actualizar una pregunta existente
    public void update(Pregunta pregunta) throws SQLException {
        if (pregunta.getIdPregunta() <= 0) {
            throw new IllegalArgumentException("El ID de la pregunta debe ser mayor a cero.");
        }

        Pregunta existente = preguntaRepository.find(pregunta.getIdPregunta());
        if (existente == null) {
            throw new NoSuchElementException("No se puede actualizar, la pregunta no existe.");
        }

        if (pregunta.getPregunta() == null || pregunta.getPregunta().isBlank()) {
            throw new IllegalArgumentException("El texto de la pregunta no puede estar vacío.");
        }

        preguntaRepository.update(pregunta);
    }

    // 🔹 Eliminar una pregunta
    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero.");
        }

        Pregunta existente = preguntaRepository.find(id);
        if (existente == null) {
            throw new NoSuchElementException("No se puede eliminar, la pregunta no existe.");
        }

        preguntaRepository.delete(id);
    }
}
