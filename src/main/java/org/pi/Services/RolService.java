package org.pi.Services;


import org.pi.Models.Rol;
import org.pi.Repositories.RolRepository;
import java.sql.SQLException;
import java.util.List;

public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    public List<Rol> findAllRol() throws SQLException {
        List<Rol> roles = rolRepository.findAllRol();
        if (roles.isEmpty()) {
            throw new SQLException("No se encontraron roles registrados");
        }
        return roles;
    }

    public Rol findRol(int idRol) throws SQLException {
        if (idRol <= 0) {
            throw new IllegalArgumentException("El ID del rol debe ser mayor a cero");
        }
        Rol rol = rolRepository.findRol(idRol);
        if (rol == null) {
            throw new SQLException("No se encontró el rol con el ID especificado");
        }
        return rol;
    }


    public void saveRol(Rol rol) throws SQLException {
        if (rol == null) {
            throw new IllegalArgumentException("El objeto rol no puede ser nulo");
        }
        if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        rolRepository.saveRol(rol);
    }

    public void updateRol(Rol rol) throws SQLException {
        if (rol == null || rol.getIdRol() <= 0) {
            throw new IllegalArgumentException("El rol o su ID no son válidos");
        }
        if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        rolRepository.updateRol(rol);
    }

    public void deleteRol(int idRol) throws SQLException {
        if (idRol <= 0) {
            throw new IllegalArgumentException("El ID del rol debe ser válido");
        }
        rolRepository.deleteRol(idRol);
    }
}
