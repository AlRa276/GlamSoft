package org.pi.Repositories;
import org.pi.Config.DBconfig;
import org.pi.Models.Cita;
import org.pi.dto.CitaDTO;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CitaRepository {

    // ... (Los métodos findCitaCliente y findCitasMes del mensaje anterior estaban bien) ...
    // Repetimos findCitaCliente para mantener contexto si copias todo el archivo
    public List<CitaDTO> findCitaCliente(int idCliente) throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud, " +
                "GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios, " +
                "SUM(s.precio) AS total_cita " +
                "FROM cita c " +
                "JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE c.id_cliente = ? " +
                "GROUP BY c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud " +
                "ORDER BY c.fecha_hora_cita";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();
                dto.setEstadoCita(rs.getString("estado_cita"));
                Timestamp fecha2 = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha2.toLocalDateTime());
                Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                if(solicitud != null) dto.setFechaSolicitudCita(solicitud.toLocalDateTime());
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                dto.setPrecioTotal(rs.getDouble("total_cita"));
                citas.add(dto);
            }
        }
        return citas;
    }

    public List<CitaDTO> findCitasMes(int mes, int year) throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.fecha_hora_cita, u.email AS cliente_email, " +
                "GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios " +
                "FROM cita c " +
                "JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE MONTH(c.fecha_hora_cita) = ? " +
                "AND YEAR(c.fecha_hora_cita) = ? " +
                "GROUP BY c.fecha_hora_cita, u.email " +
                "ORDER BY c.fecha_hora_cita";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mes);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();
                Timestamp fecha2 = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha2.toLocalDateTime());
                dto.setClienteEmail(rs.getString("cliente_email"));
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                citas.add(dto);
            }
        }
        return citas;
    }

    // --- CORRECCIÓN IMPORTANTE AQUÍ ---
    public List<CitaDTO> findCitasSemana(int year, int semana) throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();
        // Usamos WEEK(date, 1) para modo lunes-primero, compatible con la mayoría de calendarios ISO
        String sql = "SELECT c.fecha_hora_cita, u.email AS cliente_email, " +
                "GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios " +
                "FROM cita c " +
                "JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE YEAR(c.fecha_hora_cita) = ? AND WEEK(c.fecha_hora_cita, 1) = ? " +
                "GROUP BY c.fecha_hora_cita, u.email " +
                "ORDER BY c.fecha_hora_cita";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, semana);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();
                Timestamp fecha2 = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha2.toLocalDateTime());
                dto.setClienteEmail(rs.getString("cliente_email"));
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                citas.add(dto);
            }
        }
        return citas;
    }

    public List<CitaDTO> findCitasDia(LocalDate fecha) throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.id_cita, c.fecha_hora_cita, u.email AS cliente_email, e.nombre AS estilista_nombre, " +
                "GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios " +
                "FROM cita c " +
                "JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN empleado e ON c.id_estilista = e.id_empleado " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE DATE(c.fecha_hora_cita) = ? " +
                "GROUP BY c.id_cita, u.email, e.nombre, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud " +
                "ORDER BY c.fecha_hora_cita";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();
                Timestamp fecha2 = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha2.toLocalDateTime());
                dto.setClienteEmail(rs.getString("cliente_email"));
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                citas.add(dto);
            }
        }
        return citas;
    }

    public List<CitaDTO> findAllCitas() throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT "
                + "    c.id_cita, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud, "
                + "    u.email AS cliente_email, e.nombre AS estilista_nombre, "
                + "    GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios "
                + "FROM cita c "
                + "JOIN usuario u ON c.id_cliente = u.id_usuario "
                + "JOIN empleado e ON c.id_estilista = e.id_empleado "
                + "JOIN cita_servicio cs ON c.id_cita = cs.id_cita "
                + "JOIN servicio s ON cs.id_servicio = s.id_servicio "
                + "GROUP BY c.id_cita, u.email, e.nombre, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud "
                + "ORDER BY c.fecha_hora_cita DESC";

        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();
                dto.setIdCita(rs.getInt("id_cita"));
                dto.setEstadoCita(rs.getString("estado_cita"));
                Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha.toLocalDateTime());
                Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                if(solicitud != null) dto.setFechaSolicitudCita(solicitud.toLocalDateTime());
                dto.setClienteEmail(rs.getString("cliente_email"));
                dto.setEstilistaNombre(rs.getString("estilista_nombre"));
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                citas.add(dto);
            }
        }
        return citas;
    }

    public CitaDTO findCitaById(int id) throws SQLException {
        CitaDTO cita = null;
        String sql = "SELECT "
                + "    c.id_cita, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud, "
                + "    u.email AS cliente_email, e.nombre AS estilista_nombre, "
                + "    GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios "
                + "FROM cita c " +
                "JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN empleado e ON c.id_estilista= e.id_empleado " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE c.id_cita = ? " +
                "GROUP BY c.id_cita, u.email, e.nombre, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud";

        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cita = new CitaDTO();
                    cita.setIdCita(rs.getInt("id_cita"));
                    cita.setEstadoCita(rs.getString("estado_cita"));
                    Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                    cita.setFechaCita(fecha.toLocalDateTime());
                    Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                    if(solicitud != null) cita.setFechaSolicitudCita(solicitud.toLocalDateTime());
                    cita.setClienteEmail(rs.getString("cliente_email"));
                    cita.setEstilistaNombre(rs.getString("estilista_nombre"));
                    cita.setNombresServicios(rs.getString("nombres_servicios"));
                }
            }
        }
        return cita;
    }

    public int save(Cita cita) throws SQLException {
        List<Integer> servicioIds = cita.getServicios();
        String sqlCita = "INSERT INTO cita(estado_cita, fecha_hora_cita, id_cliente, id_estilista, id_horario) VALUES(?,?,?,?,?)";
        String sqlRelacion = "INSERT INTO cita_servicio(id_cita, id_servicio) VALUES(?,?)";

        try (Connection conn = DBconfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            int id;
            try (PreparedStatement stmtCita = conn.prepareStatement(sqlCita, Statement.RETURN_GENERATED_KEYS)) {
                stmtCita.setString(1, cita.getEstadoCita());
                stmtCita.setTimestamp(2, Timestamp.valueOf(cita.getFechaCita()));
                stmtCita.setInt(3, cita.getIdCliente());
                stmtCita.setInt(4, cita.getIdEstilista());
                stmtCita.setInt(5, cita.getIdHorario());

                if (stmtCita.executeUpdate() == 0) {
                    throw new SQLException("La insercion fallo");
                }
                try (ResultSet rs = stmtCita.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    } else {
                        throw new NoSuchElementException("No se encontro id");
                    }
                }
                if (servicioIds != null && !servicioIds.isEmpty()) {
                    try (PreparedStatement stmtRelacion = conn.prepareStatement(sqlRelacion)) {
                        for (int idServicio : servicioIds) {
                            stmtRelacion.setInt(1, id);
                            stmtRelacion.setInt(2, idServicio);
                            stmtRelacion.addBatch();
                        }
                        stmtRelacion.executeBatch();
                    }
                }
                conn.commit();
                return id;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void delete(int idCita) throws SQLException {
        String sql = "DELETE FROM cita WHERE id_cita = ?";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCita);
            stmt.executeUpdate();
        }
    }

    public void updateStatus(Cita cita) throws SQLException {
        String sql = "UPDATE cita SET estado_cita = ? WHERE id_cita = ?";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cita.getEstadoCita());
            stmt.setInt(2, cita.getIdCita());
            stmt.executeUpdate();
        }
    }

    public void updateFecha(Cita cita) throws SQLException {
        String sql = "UPDATE cita SET fecha_hora_cita = ? WHERE id_cita = ?";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaCita()));
            stmt.setInt(2, cita.getIdCita());
            stmt.executeUpdate();
        }
    }

    public int calcularDuracionTotal(List<Integer> servicioIds) throws SQLException {
        if (servicioIds == null || servicioIds.isEmpty()) return 0;
        String placeholders = servicioIds.stream().map(id -> "?").collect(Collectors.joining(", "));
        String sql = "SELECT SUM(duracion_minutos) FROM servicio WHERE id_servicio IN (" + placeholders + ")";
        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < servicioIds.size(); i++) {
                stmt.setInt(i + 1, servicioIds.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public boolean existeTraslape(int idEstilista, LocalDateTime nuevaHoraInicio, LocalDateTime nuevaHoraFin) throws SQLException {
        String sql = "SELECT COUNT(c.id_cita) FROM cita c "
                + "WHERE c.id_estilista = ? "
                + "AND c.estado_cita IN ('PENDIENTE', 'CONFIRMADA') "
                + "AND c.fecha_hora_cita < ? "
                + "AND DATE_ADD(c.fecha_hora_cita, INTERVAL ("
                + "  SELECT IFNULL(SUM(s2.duracion_minutos), 30) "
                + "  FROM cita_servicio cs2 JOIN servicio s2 ON cs2.id_servicio = s2.id_servicio "
                + "  WHERE cs2.id_cita = c.id_cita"
                + ") MINUTE) > ?";

        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEstilista);
            stmt.setTimestamp(2, Timestamp.valueOf(nuevaHoraFin));
            stmt.setTimestamp(3, Timestamp.valueOf(nuevaHoraInicio));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}