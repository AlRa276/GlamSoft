package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Promocion;
import org.pi.Models.Servicio;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PromocionRepository {
    public List<Promocion> findAll() throws SQLException {
        List<Promocion> promociones = new ArrayList<>();
        String sql = "SELECT * FROM promocion";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int idPromocion = rs.getInt("id_promocion");
                String nombrePromocion = rs.getString("nombre_promocion");
                String tipoDescuento = rs.getString("tipo_descuento");
                double descuento = rs.getDouble("valor_descuento"); // Mapeado de valor_descuento a descuento
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();

                Promocion promo = new Promocion(idPromocion, nombrePromocion, tipoDescuento, descuento, fechaInicio, fechaFin);
                promociones.add(promo);
            }
        }
        return promociones;
    }
    public Promocion find(int id) throws SQLException {
        Promocion promocion = null;
        String sql = "SELECT * FROM promocion WHERE id_promocion = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombrePromocion = rs.getString("nombre_promocion");
                    String tipoDescuento = rs.getString("tipo_descuento");
                    double descuento = rs.getDouble("valor_descuento");
                    LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                    LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();

                    promocion = new Promocion(id, nombrePromocion, tipoDescuento, descuento, fechaInicio, fechaFin);
                }
            }
        }
        return promocion;
    }

    public int save(Promocion promocion) throws SQLException {
        String sql = "INSERT INTO promocion(nombre_promocion, tipo_descuento, valor_descuento, fecha_inicio, fecha_fin) VALUES(?, ?, ?, ?, ?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, promocion.getNombrePromocion());
            stmt.setString(2, promocion.getTipoDescuento());
            stmt.setDouble(3, promocion.getDescuento());
            // Mapear LocalDate a java.sql.Date
            stmt.setDate(4, Date.valueOf(promocion.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(promocion.getFechaFin()));

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La insercion de promocion falló");
            }
            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                } else {
                    throw new SQLException("No se encontró id generado para promocion");
                }
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM promocion WHERE id_promocion = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la promoción con id: " + id);
            } else {
                System.out.println("Eliminación exitosa de la promoción");
            }
        }
    }

    public void update(Promocion promocion) throws SQLException {
        String sql = "UPDATE promocion SET nombre_promocion = ?, tipo_descuento = ?, valor_descuento = ?, fecha_inicio = ?, fecha_fin = ? WHERE id_promocion = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, promocion.getNombrePromocion());
            stmt.setString(2, promocion.getTipoDescuento());
            stmt.setDouble(3, promocion.getDescuento());
            stmt.setDate(4, Date.valueOf(promocion.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(promocion.getFechaFin()));
            stmt.setInt(6, promocion.getIdPromocion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la promoción con id: " + promocion.getIdPromocion());
            } else {
                System.out.println("Se actualizó la promoción con éxito");
            }
        }
    }

    //lista de servicios que conforman una promocion
    public List<Servicio> findServicios(int idPromocion) throws SQLException {
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

    public void saveServicios(Promocion relacion) throws SQLException {
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
    /*
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
     */
}
