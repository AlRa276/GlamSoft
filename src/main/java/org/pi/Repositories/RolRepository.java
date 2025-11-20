package org.pi.Repositories;
import org.pi.Models.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolRepository {

    public List<Rol> findAllRol() throws SQLException{
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM rol";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            while (rs.next()){
            int idRol = rs.getInt("id_rol");
            String nombreRol = rs.getString("nombre_rol");
            Rol rol = new Rol(idRol,nombreRol);
            roles.add(rol);
            }
        }
        return  roles;
    }

    public Rol findRol(int idRol) throws SQLException{
        Rol rol = null;
        String sql = "SELECT * FROM rol WHERE id_rol = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,idRol);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){
                    String nombreRol = rs.getString("nombre_rol");
                    rol = new Rol(idRol, nombreRol);
                }
            }
            return rol;
        }

    }

    public int saveRol(Rol rol) throws  SQLException {
        String sql = "INSERT INTO rol (nombre_rol)" + "VALUES(?)";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, rol.getNombreRol());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La insercion fallo");
            }
            //encontrar id
            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    int id = claves.getInt(1);
                    return id;
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
        }
    }
}
