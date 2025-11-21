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

    public List<Pregunta> findPreFormulario(int id) throws SQLException{
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero.");
        }
        return preguntaRepository.FindFormulario(id);
    }
    public List<Pregunta> findFormularioServicio(int id) throws SQLException{
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero.");
        }
        return preguntaRepository.FindFormularioServicio(id);
    }

    public List<Pregunta> findAll() throws SQLException {
        return preguntaRepository.findAll();
    }


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


    public int save(Pregunta pregunta) throws SQLException {
        String textoPregunta = pregunta.getPregunta();
        int idFormulario = pregunta.getIdFormulario();

        List<Pregunta> preguntasExistentes = preguntaRepository.FindFormulario(idFormulario);
        boolean existe = preguntasExistentes.stream().anyMatch(p -> p.getPregunta().equalsIgnoreCase(textoPregunta));
        if (existe) {
            throw new IllegalArgumentException("Ya existe una pregunta con ese texto en el formulario.");
        }
        if (pregunta.getPregunta() == null || pregunta.getPregunta().isBlank()) {
            throw new IllegalArgumentException("El texto de la pregunta es obligatorio.");
        }
        if (pregunta.getIdFormulario() <= 0) {
            throw new IllegalArgumentException("Debe asignarse un formulario válido.");
        }


        return preguntaRepository.save(pregunta);
    }

    public void update(Pregunta pregunta) throws SQLException {
        // 1. Validaciones básicas
        if (pregunta.getIdPregunta() <= 0) {
            throw new IllegalArgumentException("El ID de la pregunta debe ser mayor a cero.");
        }
        if (pregunta.getPregunta() == null || pregunta.getPregunta().isBlank()) {
            throw new IllegalArgumentException("El texto de la pregunta no puede estar vacío.");
        }

        // 2. Obtener la pregunta ORIGINAL de la BD para saber su formulario real
        Pregunta existente = preguntaRepository.find(pregunta.getIdPregunta());
        if (existente == null) {
            throw new NoSuchElementException("No se puede actualizar, la pregunta no existe.");
        }

        // 3. Usar el idFormulario de la BD (el real), no el que viene (o falta) en el JSON
        int idFormularioReal = existente.getIdFormulario();
        String nuevoTexto = pregunta.getPregunta();

        // 4. Buscar duplicados en el formulario correcto
        List<Pregunta> formulario = preguntaRepository.FindFormulario(idFormularioReal);
        boolean duplicada = formulario.stream()
        .anyMatch(p -> 
            p.getPregunta().equalsIgnoreCase(nuevoTexto) 
            && p.getIdPregunta() != pregunta.getIdPregunta() // Ignorar la misma pregunta que estamos editando
        );

        if (duplicada) {
            throw new IllegalArgumentException("Ya existe una pregunta con ese texto en este formulario.");
        }

        // 5. Ejecutar actualización
        preguntaRepository.update(pregunta);
    }


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
