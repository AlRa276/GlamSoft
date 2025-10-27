package org.pi.Repositories;
import org.pi.Models.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolRepository {

    public List<Rol> findAllRol() throws SQLException{
        List<Rol> roles = new ArrayList<>();
        String sql = "SELEC * FROM rol";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            int idRol = rs.getInt("id_rol");
            String nombreRol = rs.getString("nombre_rol");
            Rol rol = new Rol(idRol,nombreRol);
            roles.add(rol);
        }
        return  roles;
    }

    public Rol findRol(int idRol) throws SQLException{
        Rol rol = null;
        String sql = "SELEC * FROM rol WHERE id_rol = ?";
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

    public void saveRol(Rol rol) throws  SQLException {
        String sql = "INSERT INTO rol(id_rol, nombre_rol)"+"VALUES(?,?)";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )  {
            stmt.setInt(1, rol.getIdRol());
            stmt.setString(2,rol.getNombreRol());
            stmt.executeUpdate();

        }
    }

    public void deleteRol(int idRol) throws SQLException{
        String sql = "DELETE FROM rol WHERE id_rol = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idRol);
            stmt.executeUpdate();
        }
    }

    public void updateRol(int idRol, Rol rol) throws SQLException {
        String sql = "UPDATE rol SET nombre_rol WHERE id_rol = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1,rol.getNombreRol());
            stmt.setInt(2,idRol);
            stmt.executeUpdate();

        }
    }
}
