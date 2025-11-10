package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.CitaServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaServicioRepository {


    public List<CitaServicio> findAll() throws SQLException {
        List<CitaServicio> relaciones = new ArrayList<>();
        String sql = "SELECT * FROM cita_servicio";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int idCita = rs.getInt("id_cita");
                int idServicio = rs.getInt("id_servicio");
                CitaServicio relacion = new CitaServicio(idCita, idServicio);
                relaciones.add(relacion);
            }
        }
        return relaciones;
    }


    public CitaServicio find(int idCita, int idServicio) throws SQLException {
        CitaServicio relacion = null;
        String sql = "SELECT * FROM cita_servicio WHERE id_cita = ? AND id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idCita);
            stmt.setInt(2, idServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    relacion = new CitaServicio(idCita, idServicio);
                }
            }
        }
        return relacion;
    }


    public void save(CitaServicio relacion) throws SQLException {
        String sql = "INSERT INTO cita_servicio(id_cita, id_servicio) VALUES(?, ?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, relacion.getIdCita());
            stmt.setInt(2, relacion.getIdServicio());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La inserción de la relación Cita-Servicio falló");
            } else {
                System.out.println("Relación Cita-Servicio creada con éxito.");
            }
        }
    }


    public void delete(int idCita, int idServicio) throws SQLException {
        String sql = "DELETE FROM cita_servicio WHERE id_cita = ? AND id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idCita);
            stmt.setInt(2, idServicio);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la relación Cita-Servicio para eliminar.");
            } else {
                System.out.println("Eliminación exitosa de la relación Cita-Servicio.");
            }
        }
    }
}
