package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Horario;
import org.pi.Models.Servicio;
import org.pi.Models.ServicioPromocion;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioPromocionRepository {

    public List<ServicioPromocion> findAll() throws SQLException {
        List<ServicioPromocion> relaciones = new ArrayList<>();
        String sql = "SELECT * FROM servicio_promocion";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int idServicio = rs.getInt("id_servicio");
                int idPromocion = rs.getInt("id_promocion");
                ServicioPromocion relacion = new ServicioPromocion(idServicio, idPromocion);
                relaciones.add(relacion);
            }
        }
        return relaciones;
    }

    //lista de servicios que conforman una promocion
    public List<Servicio> find(int idPromocion) throws SQLException {
       List<Servicio> servicios = new ArrayList<>();
       String sql = " SELECT s.nombre_servicio, s.precio, s.descripcion " +
               "FROM servicio s INNER JOIN servicio_promocion sp " +
               "ON s.id_servicio = sp.id_servicio " +
               "INNER JOIN promocion p ON sp.id_promocion = p.id_promocion " +
               "WHERE p.id_promocion";
       try(
               Connection conn = DBconfig.getDataSource().getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql);
               ){
           stmt.setInt(1,idPromocion);
           try (ResultSet rs = stmt.executeQuery()) {
               while (rs.next()) {
                   String nombreServicio = rs.getString("nombre_servicio");
                   double precio = rs.getDouble("precio");
                   String descripcion = rs.getString("descripcion");
                   Servicio servicio = new Servicio(nombreServicio,precio,descripcion);
                   servicios.add(servicio);

               }
           }

       }
       return servicios;
    }

    public void save(ServicioPromocion relacion) throws SQLException {
        String sql = "INSERT INTO servicio_promocion(id_servicio, id_promocion) VALUES(?, ?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, relacion.getIdServicio());
            stmt.setInt(2, relacion.getIdPromocion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La inserción de la relación Servicio-Promoción falló");
            }
        }
    }

    public void delete(int idServicio, int idPromocion) throws SQLException {
        String sql = "DELETE FROM servicio_promocion WHERE id_servicio = ? AND id_promocion = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idServicio);
            stmt.setInt(2, idPromocion);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la relación Servicio-Promoción para eliminar.");
            } else {
                System.out.println("Eliminación exitosa de la relación Servicio-Promoción.");
            }
        }
    }

}