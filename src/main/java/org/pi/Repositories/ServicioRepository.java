package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioRepository {

    public List<Servicio> findAll() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM servicio";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int idServicio = rs.getInt("id_servicio");
                String imagen = rs.getString("imagen");
                String nombreServicio = rs.getString("nombre_servicio");
                int duracionMinutos = rs.getInt("duracion_minutos");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                int categoriaId = rs.getInt("categoria_id");
                Integer formularioId = rs.getObject("formulario_id") != null ? rs.getInt("formulario_id") : null;

                Servicio servicio = new Servicio(idServicio, imagen, nombreServicio, duracionMinutos, precio, descripcion, categoriaId, formularioId);
                servicios.add(servicio);
            }
        }
        return servicios;
    }

    public Servicio find(int id) throws SQLException {
        Servicio servicio = null;
        String sql = "SELECT * FROM servicio WHERE id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String imagen = rs.getString("imagen");
                    String nombreServicio = rs.getString("nombre_servicio");
                    int duracionMinutos = rs.getInt("duracion_minutos");
                    double precio = rs.getDouble("precio");
                    String descripcion = rs.getString("descripcion");
                    int categoriaId = rs.getInt("categoria_id");
                    Integer formularioId = rs.getObject("formulario_id") != null ? rs.getInt("formulario_id") : null;

                    servicio = new Servicio(id, imagen, nombreServicio, duracionMinutos, precio, descripcion, categoriaId, formularioId);
                }
            }
        }
        return servicio;
    }

    public int save(Servicio servicio) throws SQLException {
        String sql = "INSERT INTO servicio(imagen, nombre_servicio, duracion_minutos, precio, descripcion, categoria_id, formulario_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, servicio.getImagenURL());
            stmt.setString(2, servicio.getNombreServicio());
            stmt.setInt(3, servicio.getDuracionMinutos());
            stmt.setDouble(4, servicio.getPrecio());
            stmt.setString(5, servicio.getDescripcion());
            stmt.setInt(6, servicio.getIdCategoria());

            // Manejo de FK nullable (formulario_id)
            if (servicio.getIdFormulario() == null) {
                stmt.setNull(7, Types.INTEGER);
            } else {
                stmt.setInt(7, servicio.getIdFormulario());
            }

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La insercion de servicio falló");
            }
            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                } else {
                    throw new SQLException("No se encontró id generado para servicio");
                }
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM servicio WHERE id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró el servicio con id: " + id);
            } else {
                System.out.println("Eliminación exitosa del servicio");
            }
        }
    }

    public void update(Servicio servicio) throws SQLException {
        String sql = "UPDATE servicio SET imagen = ?, nombre_servicio = ?, duracion_minutos = ?, precio = ?, descripcion = ?, categoria_id = ?, formulario_id = ? WHERE id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, servicio.getImagenURL());
            stmt.setString(2, servicio.getNombreServicio());
            stmt.setInt(3, servicio.getDuracionMinutos());
            stmt.setDouble(4, servicio.getPrecio());
            stmt.setString(5, servicio.getDescripcion());
            stmt.setInt(6, servicio.getIdCategoria());

            // Manejo de FK nullable (formulario_id)
            if (servicio.getIdFormulario() == null) {
                stmt.setNull(7, Types.INTEGER);
            } else {
                stmt.setInt(7, servicio.getIdFormulario());
            }

            stmt.setInt(8, servicio.getIdServicio());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró el servicio con id: " + servicio.getIdServicio());
            } else {
                System.out.println("Se actualizó el servicio con éxito");
            }
        }
    }
}