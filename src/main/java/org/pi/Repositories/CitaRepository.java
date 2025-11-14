package org.pi.Repositories;
import org.pi.Config.DBconfig;
import org.pi.Models.Cita;
import org.pi.dto.CitaDTO;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CitaRepository {

    public List<CitaDTO> findCitaCliente(int idCliente) throws SQLException{
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud, GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ' " +
                "AS nombres_servicios, SUM(s.precio) AS total_cita " +
                "FROM cita c JOIN usuario u ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE c.id_cliente = ? " +
                "GROUP BY c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud " +
                "ORDEN BY c.fecha_hora_cita";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                CitaDTO dto = new CitaDTO();
                dto.setEstadoCita(rs.getString("estado_cita"));
                Timestamp fecha2 = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha2.toLocalDateTime());
                Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                dto.setFechaSolicitudCita(solicitud.toLocalDateTime());
                dto.setNombresServicios(rs.getString("nombres_servicios"));
                dto.setPrecioTotal(rs.getDouble("total_cita"));
                citas.add(dto);
            }
        }
        return citas;
    }
    public  List<CitaDTO> findCitasMes(int mes, int year) throws SQLException{
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.fecha_hora_cita, u.email AS cliente_email, GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') " +
                "AS nombres_servicios " +
                "FROM cita c JOIN usuario ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE MONTH(c.fecha_hora_cita) = ? " +
                "AND YEAR(c.fecha_hora_cita) = ? " +
                "GROUP BY c.fecha_hora_cita, u.email " +
                "ORDEN BY c.fecha_hora_cita";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, mes);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
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

    public List<CitaDTO> findCitasSemana(int year, int semana) throws SQLException{
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.fecha_hora_cita, u.email AS cliente_email, GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') " +
                "AS nombres_servicios " +
                "FROM cita c JOIN usuario ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE YEARWEEK(c.fecha_hora_cita, 1) = YEARWEEK(STR_TO_DATE(CONCAT(?,'-','01'), '%X-%V-%w'), 1) " +
                "GROUP BY c.fecha_hora_cita, u.email " +
                "ORDER BY c.fecha_hora_cita;";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, year);
            stmt.setInt(2, semana);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
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

    public List<CitaDTO> findCitasDia(LocalDate fecha) throws SQLException{
        List<CitaDTO> citas = new ArrayList<>();
        String sql = "SELECT c.fecha_hora_cita, u.email AS cliente_email, GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') " +
                "AS nombres_servicios " +
                "FROM cita c JOIN usuario ON c.id_cliente = u.id_usuario " +
                "JOIN cita_servicio cs ON c.id_cita = cs.id_cita " +
                "JOIN servicio s ON cs.id_servicio = s.id_servicio " +
                "WHERE DATE(c.fecha_hora_cita) = ? " +
                "GROUP BY c.id_cita, u.email, e.nombre, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud " +
                "ORDER BY c.fecha_hora_cita;";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
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
    //todas las citas
    public List<CitaDTO> findAllCitas() throws SQLException {
        List<CitaDTO> citas = new ArrayList<>();

        // La consulta SQL modificada para obtener todos los campos clave
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

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                CitaDTO dto = new CitaDTO();

                dto.setIdCita(rs.getInt("id_cita"));
                dto.setEstadoCita(rs.getString("estado_cita"));

                Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                dto.setFechaCita(fecha.toLocalDateTime());

                Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                dto.setFechaSolicitudCita(solicitud.toLocalDateTime());

                // Campos obtenidos de los JOIN
                dto.setClienteEmail(rs.getString("cliente_email"));
                dto.setEstilistaNombre(rs.getString("estilista_nombre"));
                dto.setNombresServicios(rs.getString("nombres_servicios"));

                citas.add(dto);
            }
        }
        return citas;
    }
    //una cita en especifico
    public CitaDTO findCitaById(int id) throws SQLException {
        CitaDTO cita = null;

        // Consulta SQL con JOIN y WHERE
        String sql = "SELECT "
                + "    c.id_cita, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud, "
                + "    u.email AS cliente_email, e.nombre AS estilista_nombre, "
                + "    GROUP_CONCAT(s.nombre_servicio SEPARATOR ', ') AS nombres_servicios "
                + "FROM cita c "
                + "JOIN usuario u ON c.id_cliente = u.id_usuario "
                + "JOIN empleado e ON c.id_estilista= e.id_empleado "
                + "JOIN cita_servicio cs ON c.id_cita = cs.id_cita "
                + "JOIN servicio s ON cs.id_servicio = s.id_servicio "
                + "WHERE c.id_cita = ? " // Filtro por ID
                + "GROUP BY c.id_cita, u.email, e.nombre, c.estado_cita, c.fecha_hora_cita, c.fecha_solicitud";

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Establecer el parámetro de la ID de la cita
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cita = new CitaDTO();

                    // Mapeo de campos de Cita
                    cita.setIdCita(rs.getInt("id_cita"));
                    cita.setEstadoCita(rs.getString("estado_cita"));

                    Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                    cita.setFechaCita(fecha.toLocalDateTime());

                    Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                    cita.setFechaSolicitudCita(solicitud.toLocalDateTime());

                    // Mapeo de campos JOIN
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

        try(
                Connection conn = DBconfig.getDataSource().getConnection()
                ){
            conn.setAutoCommit(false);
            int id;
            try(
                    PreparedStatement stmtCita = conn.prepareStatement(sqlCita, Statement.RETURN_GENERATED_KEYS)
                    ){
                stmtCita.setString(1, cita.getEstadoCita());
                LocalDateTime fechaCita = cita.getFechaCita();
                stmtCita.setTimestamp(2, Timestamp.valueOf(fechaCita));
                stmtCita.setInt(3,cita.getIdCliente());
                stmtCita.setInt(4,cita.getIdEstilista());
                stmtCita.setInt(5,cita.getIdHorario());

                if (stmtCita.executeUpdate() == 0) {
                    throw new SQLException("La insercion fallo");
                }
                try(ResultSet rs = stmtCita.getGeneratedKeys()){
                    if (rs.next()){
                        id = rs.getInt(1);
                    }else {
                        throw new NoSuchElementException("No se encontro id");
                    }
                }
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
        }

    }

    public void delete(int idCita)throws SQLException{
        String sql = "DELETE FROM cita WHERE id_cita = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, idCita);
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("eliminacion exitosa");
            }
        }
    }

    public void updateStatus(Cita cita) throws SQLException{
        String sql = "UPDATE cita SET estado_cita = ? WHERE id_cita = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, cita.getEstadoCita());
            stmt.setInt(2,cita.getIdCita());
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("actualizacion exitosa");
            }
        }

    }
    public void updateFecha(Cita cita) throws SQLException{
        String sql = "UPDATE cita SET fecha_hora_cita = ? WHERE id_cita = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            LocalDateTime fechaCita = cita.getFechaCita();
            stmt.setTimestamp(1, Timestamp.valueOf(fechaCita));
            stmt.setInt(2,cita.getIdCita());
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("actualizacion exitosa");
            }
        }

    }

    // En ServicioRepository (Ejemplo de implementación)
    public int calcularDuracionTotal(List<Integer> servicioIds) throws SQLException {
        // Generar la cadena de '?, ?, ?' para el IN clause
        String placeholders = servicioIds.stream().map(id -> "?").collect(Collectors.joining(", "));

        String sql = "SELECT SUM(duracion_minutos) FROM servicio WHERE id_servicio IN (" + placeholders + ")";

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Asignar los valores a los placeholders
            for (int i = 0; i < servicioIds.size(); i++) {
                stmt.setInt(i + 1, servicioIds.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Devolver la suma; si la lista está vacía, devuelve 0.
                    return rs.getInt(1);
                }
            }
        }
        return 0; // Por defecto
    }


    public boolean existeTraslape(int idEstilista, LocalDateTime nuevaHoraInicio, LocalDateTime nuevaHoraFin) throws SQLException {

        // Consulta principal de traslape:
        String sql = "SELECT COUNT(c.id_cita) FROM cita c "
                + "WHERE c.id_estilista = ? "
                + "AND c.estado_cita IN ('PENDIENTE', 'CONFIRMADA') "
                // Condición 1: El inicio de una cita existente es antes de que termine la nueva cita
                + "AND c.fecha_hora_cita < ? "
                // Condición 2: El fin de una cita existente es después de que empiece la nueva cita
                + "AND DATE_ADD(c.fecha_hora_cita, INTERVAL (SELECT SUM(s2.duracion_minutos) FROM cita_servicio cs2 JOIN servicio s2 ON cs2.id_servicio = s2.id_servicio WHERE cs2.id_cita = c.id_cita) MINUTE) > ?";



        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // 1. Estilista ID
            stmt.setInt(1, idEstilista);
            // 2. Nueva Hora Fin
            stmt.setTimestamp(2, Timestamp.valueOf(nuevaHoraFin));
            // 3. Nueva Hora Inicio
            stmt.setTimestamp(3, Timestamp.valueOf(nuevaHoraInicio));


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si COUNT > 0, hay traslape
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean existeTraslapeReagendar(int idEstilista, LocalDateTime nuevaHoraInicio, LocalDateTime nuevaHoraFin, Integer idCitaAExcluir) throws SQLException {

        // Consulta principal de traslape:
        String sql = "SELECT COUNT(c.id_cita) FROM cita c "
                + "WHERE c.id_estilista = ? "
                + "AND c.estado_cita IN ('PENDIENTE', 'CONFIRMADA') "
                // Condición 1: El inicio de una cita existente es antes de que termine la nueva cita
                + "AND c.fecha_hora_cita < ? "
                // Condición 2: El fin de una cita existente es después de que empiece la nueva cita
                + "AND DATE_ADD(c.fecha_hora_cita, INTERVAL (SELECT SUM(s2.duracion_minutos) FROM cita_servicio cs2 JOIN servicio s2 ON cs2.id_servicio = s2.id_servicio WHERE cs2.id_cita = c.id_cita) MINUTE) > ?";

        // Modificar la consulta si estamos actualizando una cita
        if (idCitaAExcluir != null && idCitaAExcluir > 0) {
            sql += " AND c.id_cita <> ?"; // Excluye la cita que estamos intentando reagendar
        }

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // 1. Estilista ID
            stmt.setInt(1, idEstilista);
            // 2. Nueva Hora Fin
            stmt.setTimestamp(2, Timestamp.valueOf(nuevaHoraFin));
            // 3. Nueva Hora Inicio
            stmt.setTimestamp(3, Timestamp.valueOf(nuevaHoraInicio));

            // 4. Exclusión (si aplica)
            if (idCitaAExcluir != null && idCitaAExcluir > 0) {
                stmt.setInt(4, idCitaAExcluir);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si COUNT > 0, hay traslape
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
