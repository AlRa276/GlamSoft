package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Empleado;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {

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
                    String email = rs.getString("email");
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    Empleado empleado = new Empleado(idRol, email, nombre, telefono);
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
                    int idUsuario = rs.getInt("id_usuario");
                    String email = rs.getString("email");
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    empleado = new Empleado(email, nombre, telefono);
                }
            }
        }
        return empleado;
    }


    public int save(Empleado empleado) throws SQLException {
        String sqlUsuario = "INSERT INTO usuario (email, password, rol_id) VALUES (?, ?, ?)";
        String sqlEmpleado = "INSERT INTO empleado (nombre, telefono, id_usuario) VALUES (?, ?, ?)";

        try (
                Connection conn = DBconfig.getDataSource().getConnection()) {
                conn.setAutoCommit(false);

            int id;

            // usuario
            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmtUsuario.setString(1, empleado.getEmail());
                stmtUsuario.setString(2, empleado.getPassword());
                stmtUsuario.setInt(3, empleado.getIdRol());
                //verificacion
                if (stmtUsuario.executeUpdate() == 0) {
                    throw new SQLException("La inserción de usuario falló.");
                }

                // encontrar id
                try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    } else {
                        throw new SQLException("No se encontro id");
                    }
                }
            }
            // empleado
            try (PreparedStatement stmtEmpleado = conn.prepareStatement(sqlEmpleado)) {
                stmtEmpleado.setString(1, empleado.getNombre());
                stmtEmpleado.setString(2, empleado.getTelefono());
                stmtEmpleado.setInt(3, id);
                //verificacion
                if (stmtEmpleado.executeUpdate() == 0) {
                    throw new SQLException("La inserción de empleado falló.");
                }
            }
            conn.commit();
            return id;
        }
    }

    public  void delete(int id_empleado) throws SQLException{
        String sqlIdUser = "SELECT id_usuario FROM empleado WHERE id_empleado = ?";
        String sqlEmpleado = "DELETE FROM empleado WHERE id_empleado = ?";
        String sqlDeleteUser = "DELETE FROM usuario WHERE id_usuario";

        try(
                Connection conn = DBconfig.getDataSource().getConnection();){
                conn.setAutoCommit(false);

                int idUsuario = -1;
                //encontrar id usuario
                try(PreparedStatement stmtUser = conn.prepareStatement(sqlIdUser)){
                    stmtUser.setInt(1,id_empleado);
                    try(ResultSet rs = stmtUser.executeQuery()){
                        if (rs.next()){
                            idUsuario = rs.getInt("id_usuario");
                        }else {
                            throw new SQLException("No se encontro el id");
                        }
                    }
                }
                //eliminar empleado
            try(PreparedStatement stmtDelEmp = conn.prepareStatement(sqlEmpleado)){
                stmtDelEmp.setInt(1,id_empleado);
                stmtDelEmp.executeQuery();
            }
            //eliminar usuario
           if (idUsuario != -1){
               try(PreparedStatement stmtDelUser = conn.prepareStatement(sqlDeleteUser)){
                   stmtDelUser.setInt(1,idUsuario);
                   stmtDelUser.executeQuery();
               }
           }
            conn.commit();
        }
    }

    public void update(Empleado empleado)throws SQLException{
        String sqlIdUser = "SELECT id_usuario FROM empleado WHERE id_empleado = ? ";
        String sqlEmpleado = "UPDATE empleado SET nombre = ?, telefono = ? WHERE id_empleado = ? ";
        String sqlUsuario = "UPDATE usuario SET email = ?, password = ? WHERE id_usuario = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();){
            conn.setAutoCommit(false);
            //encontrar id
            int idUsuario = -1;
            try(PreparedStatement stmtIdU = conn.prepareStatement(sqlIdUser)){
                stmtIdU.setInt(1,empleado.getIdEmpleado());
                try(ResultSet rs = stmtIdU.executeQuery()){
                    if (rs.next()){
                        idUsuario = rs.getInt("id_usuario");
                    }else {
                        throw new SQLException("No se encontro el id");
                    }
                }

            }

            try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmpleado)){
                stmtEmp.setString(1,empleado.getNombre());
                stmtEmp.setString(2,empleado.getTelefono());
                stmtEmp.setInt(3,empleado.getIdEmpleado());
                stmtEmp.executeUpdate();
            }

            if (idUsuario != -1){
                try(PreparedStatement stmtUser = conn.prepareStatement(sqlUsuario)){
                    stmtUser.setString(1, empleado.getEmail());
                    stmtUser.setString(2, empleado.getPassword());
                    stmtUser.setInt(3,idUsuario);
                    stmtUser.executeUpdate();
                }
            }

            conn.commit();
        }
    }


}
