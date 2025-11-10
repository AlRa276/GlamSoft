package org.pi.Services;
import org.pi.Models.Empleado;
import org.pi.Repositories.EmpleadoRepository;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> findAllE(int idRol)throws SQLException{
        return empleadoRepository.findAll(idRol);
    }

    public Empleado findE(int id)throws SQLException{
        return empleadoRepository.findById(id);
    }
    public int saveE(Empleado empleado)throws SQLException{
        return empleadoRepository.save(empleado);
    }

    public void deleteE(int id)throws SQLException{
        empleadoRepository.delete(id);
    }

    public void updateE(Empleado empleado)throws SQLException{
        empleadoRepository.update(empleado);
    }
}
