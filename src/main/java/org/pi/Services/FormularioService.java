package org.pi.Services;

import org.pi.Models.Formulario;
import org.pi.Repositories.FormularioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class FormularioService {
    private final FormularioRepository formularioRepository;

    public FormularioService(FormularioRepository fr) {
        this.formularioRepository = fr;
    }

    public List<Formulario> findAllFormulario() throws SQLException {
        return formularioRepository.findAll();
    }

    public Formulario findFormulario(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del formulario debe ser mayor a cero");
        }
        List<Formulario> formularios = formularioRepository.findAll();
        return formularios.stream()
                .filter(f -> f.getIdFormulario() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró el formulario con ID: " + id));
    }

    public int saveFormulario(Formulario formulario) throws SQLException {
        String nombre = formulario.getNombreFormulario();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del formulario no puede estar vacío");
        }

        List<Formulario> formularios = findAllFormulario();
        boolean existe = formularios.stream()
                .anyMatch(f -> f.getNombreFormulario().equalsIgnoreCase(nombre));

        if (existe) {
            throw new IllegalArgumentException("Ya existe un formulario con ese nombre");
        }

        return formularioRepository.save(formulario);
    }

    public void deleteFormulario(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero");
        }

        List<Formulario> formularios = findAllFormulario();
        boolean existe = formularios.stream().anyMatch(f -> f.getIdFormulario() == id);

        if (!existe) {
            throw new NoSuchElementException("No se puede eliminar el formulario. No existe un formulario con ese ID");
        }

        formularioRepository.delete(id);
    }


    public void updateFormulario(Formulario formulario) throws SQLException {
        if (formulario.getIdFormulario() <= 0) {
            throw new IllegalArgumentException("El ID del formulario debe ser mayor a cero");
        }

        String nombre = formulario.getNombreFormulario();
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del formulario no puede estar vacío");
        }

        List<Formulario> formularios = findAllFormulario();
        boolean existe = formularios.stream().anyMatch(f -> f.getIdFormulario() == formulario.getIdFormulario());
        if (!existe) {
            throw new NoSuchElementException("No se puede actualizar el formulario. No existe un formulario con ese ID");
        }

        boolean nombreDuplicado = formularios.stream()
                .anyMatch(f -> f.getNombreFormulario().equalsIgnoreCase(nombre)
                        && f.getNombreFormulario() != formulario.getNombreFormulario());
        if (nombreDuplicado) {
            throw new IllegalArgumentException("Otro formulario ya tiene ese nombre");
        }

        formularioRepository.update(formulario);
    }
}

