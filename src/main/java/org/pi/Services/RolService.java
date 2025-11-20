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


    public int saveRol(Rol rol) throws SQLException {
        if (rol == null) {
            throw new IllegalArgumentException("El objeto rol no puede ser nulo");
        }
        if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        String nombreRol = rol.getNombreRol();
        List<Rol> roles = findAllRol();
        boolean existe = roles.stream().anyMatch(r -> r.getNombreRol().equalsIgnoreCase(nombreRol));
        if (existe){
            throw new IllegalArgumentException("Ya existe un rol con ese nombre");
        }
       return rolRepository.saveRol(rol);
    }
}
