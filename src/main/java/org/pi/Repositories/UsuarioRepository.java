package org.pi.Repositories;

import org.pi.Models.Usuario;
import org.pi.Models.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    // Para Login (Buscar por email para verificar pass)
    public Usuario findByUser(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Usuario user = null;
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, usuario.getEmail());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdRol(rs.getInt("id_rol"));
            }
        }
        return user;
    }

    // CRUD: Leer Todos
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdRol(rs.getInt("id_rol"));
                usuarios.add(user);
            }
        }
        return usuarios;
    }

    // CRUD: Leer Uno por ID (MODIFICADO)
    public Usuario findById(int id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setIdRol(rs.getInt("id_rol"));
                }
            }
        }
        return usuario;
    }

    // CRUD: Crear
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

            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar el registro");
            }

            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
        }
    }

    // CRUD: Crear Empleado Completo
    public int saveEmpleadoCompleto(Empleado empleado) throws SQLException {
        String sqlUsuario = "INSERT INTO usuario (email, password, id_rol) VALUES (?, ?, ?)";
        String sqlEmpleado = "INSERT INTO empleado (nombre, telefono, imagen_perfil, id_usuario) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = org.pi.Config.DBconfig.getDataSource().getConnection();
            conn.setAutoCommit(false);

            int idUsuario;
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, empleado.getEmail());
                stmt.setString(2, empleado.getPassword());
                stmt.setInt(3, empleado.getIdRol());
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (!rs.next()) throw new SQLException("No se generó id_usuario");
                    idUsuario = rs.getInt(1);
                }
            }

            try (PreparedStatement stmt2 = conn.prepareStatement(sqlEmpleado)) {
                stmt2.setString(1, empleado.getNombre());
                stmt2.setString(2, empleado.getTelefono());
                stmt2.setString(3, empleado.getImagenPerfil());
                stmt2.setInt(4, idUsuario);
                stmt2.executeUpdate();
            }

            conn.commit();
            return idUsuario;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    // CRUD: Eliminar
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // CRUD: Actualizar Usuario
    public void updateUser(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email = ?, password = ? WHERE id_usuario = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    // CRUD: Actualizar Empleado Completo
    public void updateEmpleado(Usuario usuario, Empleado empleado) throws SQLException {
        String sqlUsuario = "UPDATE usuario SET email = ?, password = ? WHERE id_usuario = ?";
        String sqlEmpleado = "UPDATE empleado SET nombre = ?, telefono = ?, imagen_perfil = ? WHERE id_empleado = ?";

        Connection conn = null;
        try {
            conn = org.pi.Config.DBconfig.getDataSource().getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {
                stmt.setString(1, usuario.getEmail());
                stmt.setString(2, usuario.getPassword());
                stmt.setInt(3, usuario.getIdUsuario());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt2 = conn.prepareStatement(sqlEmpleado)) {
                stmt2.setString(1, empleado.getNombre());
                stmt2.setString(2, empleado.getTelefono());
                stmt2.setString(3, empleado.getImagenPerfil());
                stmt2.setInt(4, empleado.getIdEmpleado());
                stmt2.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}