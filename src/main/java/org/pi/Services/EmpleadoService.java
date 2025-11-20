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

    public List<Empleado> findAll(int idRol) throws SQLException {
        if (idRol <= 0) {
            throw new IllegalArgumentException("El id del rol debe ser mayor a cero");
        }
        return empleadoRepository.findAll(idRol);
    }


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




   

}
