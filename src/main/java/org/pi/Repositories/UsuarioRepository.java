package org.pi.Repositories;
import org.pi.Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class UsuarioRepository {
    public List<Usuario> findAllUsers() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT email FROM usuario";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){
            while (rs.next()) {
                String email = rs.getString("email");
                Usuario usuario = new Usuario(email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    public Usuario findByUser(Usuario usuario)throws SQLException{
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Usuario user = null;
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,usuario.getEmail());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdRol(rs.getInt("id_rol"));
            }
        }
        return user;
    }
    public Usuario findUser(String email) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT password FROM usuario WHERE email=?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1,email);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){
                    String password = rs.getString("password");
                   usuario = new Usuario(email, password);
                }
            }
        }
        return usuario;
    }

    public int saveUser(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(email, password, id_rol) VALUES(?,?,?)";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdRol());
            int filasAfectadas = stmt.executeUpdate();
            //verficar
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar el registro");
            }
            //obtener las claves
            try(ResultSet claves = stmt.getGeneratedKeys()){
                if(claves.next()){
                    int id = claves.getInt(1);
                    return id;
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
        }
    }

    public void deleteUser(String email) throws SQLException{
        String sql = "DELETE FROM usuario WHERE email = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }

    public void updateUser(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET password = ? WHERE email = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, usuario.getPassword());
            stmt.setString(2,usuario.getEmail());
            stmt.executeUpdate();
        }
    }

}
