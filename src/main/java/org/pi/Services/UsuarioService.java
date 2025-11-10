package org.pi.Services;
import org.pi.Models.Usuario;
import org.pi.Repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsers() throws SQLException{
        return usuarioRepository.findAllUsers();
    }

    public Usuario findUser(String email) throws SQLException{
        return usuarioRepository.findUser(email);
    }

    public Usuario findByUser(Usuario usuario) throws SQLException{
        return usuarioRepository.findByUser(usuario);
    }

    public void saveUser(Usuario usuario) throws SQLException{
        usuarioRepository.saveUser(usuario);
    }

    public void deleteUser(String email) throws SQLException{
        usuarioRepository.deleteUser(email);
    }

    public void updateUser( Usuario usuario) throws SQLException{
        usuarioRepository.updateUser( usuario);
    }

}
