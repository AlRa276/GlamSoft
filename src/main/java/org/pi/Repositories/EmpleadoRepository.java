package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Empleado;
import org.pi.Models.Usuario;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {
    //para ver a todos los empleados de un rol
    public List<Empleado> findAll(int idRol) throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT  u.email, e.nombre, e.telefono " +
                "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario " +
                "INNER JOIN rol r ON u.id_rol = r.id_rol " +
                "WHERE r.id_rol = ? ";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idRol);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setEmail(rs.getString("email"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setTelefono(rs.getString("telefono"));
                    empleados.add(empleado);
                }
            }
        }
        return empleados;
    }


    public Empleado findById(int id) throws SQLException {
        Empleado empleado = null;
        String sql = "SELECT u.id_usuario, u.email, e.nombre, e.telefono FROM empleado  e INNER JOIN usuario u " +
                "ON e.id_usuario = u.id_usuario WHERE e.id_empleado = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setIdUsuario(rs.getInt("id_usuario"));
                    empleado.setEmail(rs.getString("email"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setTelefono(rs.getString("telefono"));

                }
            }
        }
        return empleado;
    }

    public int save(Empleado empleado) throws SQLException{
        String sql = "INSERT INTO empleado (nombre, telefono, id_usuario, imagen_perfil) VALUES (?, ?, ?, ?) " +
                "WHERE id_usuario = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
        {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2,empleado.getTelefono());
            stmt.setInt(3, empleado.getIdUsuario());
            stmt.setString(4,empleado.getImagenPerfil());
            int filasAfectadas = stmt.executeUpdate();
            //verficar
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar el registro");
            }
            //obtener las claves
            try(ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    int id = claves.getInt(1);
                    return id;
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
        }
    }

    public  void delete(int id_empleado) throws SQLException{
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, id_empleado);
            stmt.executeUpdate();
        }
    }

    public void update(Empleado empleado)throws SQLException {
        String sql = "UPDATE empleado SET nombre = ?, telefono = ? WHERE id_empleado = ? ";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getTelefono());
            stmt.setInt(3, empleado.getIdEmpleado());
            stmt.executeUpdate();
        }
    }

}
