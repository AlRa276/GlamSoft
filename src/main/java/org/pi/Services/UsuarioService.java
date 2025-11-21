package org.pi.Services;
import org.pi.Models.Usuario;
import org.pi.Models.Empleado;
import org.pi.Repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.*;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // MODIFICADO: Buscar por ID en lugar de Email
    public Usuario findById(int id) throws SQLException{
        if(id <= 0){
            throw new IllegalArgumentException("El ID del usuario debe ser mayor a cero");
        }
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() throws SQLException {
        return usuarioRepository.findAll();
    }

    public Usuario findByUser(Usuario usuario) throws SQLException{
        return usuarioRepository.findByUser(usuario);
    }

    public int saveUser(Usuario usuario) throws SQLException{
        // Validación básica de unicidad de email debería ir aquí o en DB (Unique key)
        return usuarioRepository.saveUser(usuario);
    }

    public int saveEmpleadoCompleto(Empleado empleado) throws SQLException {
        if (empleado.getEmail() == null || empleado.getEmail().isBlank())
            throw new IllegalArgumentException("El email del empleado es obligatorio");
        if (empleado.getPassword() == null || empleado.getPassword().isBlank())
            throw new IllegalArgumentException("La contraseña es obligatoria");
        if (empleado.getNombre() == null || empleado.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        if (empleado.getTelefono() == null || empleado.getTelefono().isBlank())
            throw new IllegalArgumentException("El teléfono es obligatorio");
        if (empleado.getIdRol() <= 0)
            throw new IllegalArgumentException("Debe tener un rol válido");

        if(usuarioRepository.findByUser(new Usuario(0, empleado.getEmail(), null, 0)) != null){
            throw new IllegalArgumentException("Ya existe un usuario con ese correo electrónico");
        }

        return usuarioRepository.saveEmpleadoCompleto(empleado);
    }

    public void deleteUser(int id) throws SQLException{
        if(id <= 0){
            throw new IllegalArgumentException("El id del usuario debe ser mayor a cero");
        }
        usuarioRepository.deleteUser(id);
    }

    public void updateUser(Usuario usuario) throws SQLException{
        if(usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email del usuario es obligatorio");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if(usuario.getIdUsuario() <= 0){
            throw new IllegalArgumentException("El id del usuario debe ser mayor a cero");
        }
        // Verificamos existencia antes de actualizar
        if(usuarioRepository.findById(usuario.getIdUsuario()) == null){
            throw new NoSuchElementException("No existe un usuario con ese id");
        }

        usuarioRepository.updateUser(usuario);
    }

    public void updateEmpleadoCompleto(Usuario usuario, Empleado empleado) throws Exception {
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (empleado.getNombre() == null || empleado.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        }
        if (empleado.getTelefono() == null || empleado.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (empleado.getIdEmpleado() <= 0 || usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("IDs inválidos");
        }

        usuarioRepository.updateEmpleado(usuario, empleado);
    }
}