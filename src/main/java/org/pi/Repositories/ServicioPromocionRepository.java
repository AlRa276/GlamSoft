package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.ServicioPromocion;

import java.sql.*;
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

    public ServicioPromocion find(int idServicio, int idPromocion) throws SQLException {
        ServicioPromocion relacion = null;
        String sql = "SELECT * FROM servicio_promocion WHERE id_servicio = ? AND id_promocion = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idServicio);
            stmt.setInt(2, idPromocion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    relacion = new ServicioPromocion(idServicio, idPromocion);
                }
            }
        }
        return relacion;
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
    // Se omite el método update.
}