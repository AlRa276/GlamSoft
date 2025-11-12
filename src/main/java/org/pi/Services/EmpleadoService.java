package org.pi.Services;
import org.pi.Models.Empleado;
import org.pi.Repositories.EmpleadoRepository;

import java.sql.SQLException;
import java.util.List;

import java.util.NoSuchElementException;

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // 🔹 Listar empleados por rol
    public List<Empleado> findAll(int idRol) throws SQLException {
        if (idRol <= 0) {
            throw new IllegalArgumentException("El id del rol debe ser mayor a cero");
        }
        return empleadoRepository.findAll(idRol);
    }

    // 🔹 Buscar empleado por ID
    public Empleado findById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del empleado debe ser mayor a cero");
        }
        Empleado empleado = empleadoRepository.findById(id);
        if (empleado == null) {
            throw new NoSuchElementException("No se encontró el empleado con el id: " + id);
        }
        return empleado;
    }

    // 🔹 Guardar nuevo empleado
    public int save(Empleado empleado) throws SQLException {
        // Validaciones de campos obligatorios
        if (empleado.getEmail() == null || empleado.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email del empleado es obligatorio");
        }
        if (empleado.getPassword() == null || empleado.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (empleado.getNombre() == null || empleado.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        }
        if (empleado.getTelefono() == null || empleado.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (empleado.getIdRol() <= 0) {
            throw new IllegalArgumentException("Debe tener un rol válido");
        }

        // Verificar si el email ya existe
        List<Empleado> empleados = empleadoRepository.findAll(empleado.getIdRol());
        boolean existe = empleados.stream()
                .anyMatch(e -> e.getEmail().equalsIgnoreCase(empleado.getEmail()));

        if (existe) {
            throw new IllegalArgumentException("Ya existe un empleado con ese correo electrónico");
        }

        return empleadoRepository.save(empleado);
    }

    // 🔹 Actualizar empleado
    public void update(Empleado empleado) throws SQLException {
        if (empleado.getIdEmpleado() <= 0) {
            throw new IllegalArgumentException("El id del empleado debe ser mayor a cero");
        }

        Empleado existe = empleadoRepository.findById(empleado.getIdEmpleado());
        if (existe == null) {
            throw new NoSuchElementException("No se puede actualizar. El empleado no existe");
        }

        if (empleado.getEmail() == null || empleado.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (empleado.getNombre() == null || empleado.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        empleadoRepository.update(empleado);
    }

    // 🔹 Eliminar empleado
    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a cero");
        }
        Empleado empleado = empleadoRepository.findById(id);
        if (empleado == null) {
            throw new NoSuchElementException("No se puede eliminar. El empleado no existe");
        }
        empleadoRepository.delete(id);
    }
}
