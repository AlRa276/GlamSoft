package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.*;
import org.pi.dto.EstilistaDTO;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EstilistaRepository {
    //detalles de un horario
    public List<EstilistaDTO> findAllEstilistas() throws SQLException {
        List<EstilistaDTO> estilistas = new ArrayList<>();

        String sql = "SELECT "
                + "    e.id_empleado, "
                + "    e.nombre, "
                + "    e.telefono, "
                + "    u.email AS email_usuario, "
                + "    GROUP_CONCAT(DISTINCT s.nombre_servicio SEPARATOR ', ') AS nombres_servicios, "
                + "    GROUP_CONCAT(DISTINCT CONCAT(h.dia_semana, ' ', TIME_FORMAT(h.hora_inicio, '%H:%i'), '-', TIME_FORMAT(h.hora_fin, '%H:%i')) SEPARATOR '; ') AS horarios_completos "
                + "FROM empleado e "
                + "JOIN usuario u ON e.id_usuario = u.id_usuario "
                + "LEFT JOIN estilista_servicio es ON e.id_empleado = es.id_estilista "
                + "LEFT JOIN servicio s ON es.id_servicio = s.id_servicio "
                + "LEFT JOIN estilista_horario eh ON e.id_empleado = eh.id_estilista "
                + "LEFT JOIN horario h ON eh.id_horario = h.id_horario "
                + "GROUP BY e.id_empleado, e.nombre, e.telefono, u.email "
                + "ORDER BY e.nombre";

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                EstilistaDTO dto = new EstilistaDTO();

                dto.setIdEmpleado(rs.getInt("id_empleado"));
                dto.setNombre(rs.getString("nombre"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setEmailUsuario(rs.getString("email_usuario"));

                dto.setServicios(rs.getString("nombres_servicios"));
                dto.setHorarios(rs.getString("horarios_completos"));

                estilistas.add(dto);
            }
        }
        return estilistas;
    }

    //detalles de un estilista
    public EstilistaDTO findEstilistaById(int id) throws SQLException {
        EstilistaDTO dto = null;


        String sql = "SELECT "
                + "    e.id_empleado, "
                + "    e.nombre, "
                + "    e.telefono, "
                + "    u.email AS email_usuario, "
                + "    GROUP_CONCAT(DISTINCT s.nombre_servicio SEPARATOR ', ') AS nombres_servicios, "
                + "    GROUP_CONCAT(DISTINCT CONCAT(h.dia_semana, ' ', TIME_FORMAT(h.hora_inicio, '%H:%i'), '-', TIME_FORMAT(h.hora_fin, '%H:%i')) SEPARATOR '; ') AS horarios_completos "
                + "FROM empleado e "
                + "JOIN usuario u ON e.id_usuario = u.id_usuario "
                + "LEFT JOIN estilista_servicio es ON e.id_empleado = es.id_estilista "
                + "LEFT JOIN servicio s ON es.id_servicio = s.id_servicio "
                + "LEFT JOIN estilista_horario eh ON e.id_empleado = eh.id_estilista "
                + "LEFT JOIN horario h ON eh.id_horario = h.id_horario "
                + "WHERE e.id_empleado = ? "
                + "GROUP BY e.id_empleado, e.nombre, e.telefono, u.email";

        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dto = new EstilistaDTO();

                    dto.setIdEmpleado(rs.getInt("id_empleado"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setTelefono(rs.getString("telefono"));
                    dto.setEmailUsuario(rs.getString("email_usuario"));
                    dto.setServicios(rs.getString("nombres_servicios"));
                    dto.setHorarios(rs.getString("horarios_completos"));
                }
            }
        }

        return dto;
    }
    //horarios de un estilista
    public List<Horario> findHorarios(int idEstilista) throws SQLException{
        List<Horario> horarios = new ArrayList<>();
        String sql = "SELECT h.id_horario, h.dia_semana, h.hora_inicio, h.hora_fin " +
                "FROM empleado e INNER JOIN estilista_horario eh ON e.id_empleado = eh.id_estilista " +
                "INNER JOIN horario h ON eh.id_horario = h.id_horario " +
                "WHERE e.id_empleado = ? ";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idEstilista);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idHorario = rs.getInt("id_horario");
                    String diaSemana = rs.getString("dia_semana");
                    Time horaI = rs.getTime("hora_inicio");
                    LocalTime horaInicio = horaI.toLocalTime();
                    Time horaF = rs.getTime("hora_fin");
                    LocalTime horaFin = horaF.toLocalTime();
                    Horario horario1 = new Horario(idHorario, horaInicio, horaFin, diaSemana);
                    horarios.add(horario1);

                }
            }
        }
        return horarios;
    }
    //lista de servicios de un estilista
    public List<Servicio> findServicios(int idEstilista) throws SQLException{
        List<Servicio> servicios = new ArrayList<>();
        String sql = " SELECT s.id_servicio, s.nombre_servicio, s.descripcion " +
                "FROM empleado e INNER JOIN estilista_servicio es ON e.id_empleado = es.id_estilista " +
                "INNER JOIN servicio s ON es.id_servicio = s.id_servicio " +
                "WHERE e.id_empleado = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idEstilista);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int idServicio = rs.getInt("id_servicio");
                    String nombreServicio = rs.getString("nombre_servicio");
                    String descripcion = rs.getString("descripcion");
                    Servicio servicio = new Servicio(idServicio, nombreServicio, descripcion);
                    servicios.add(servicio);
                }
            }
        }
        return servicios;
    }

   //lista de estilistas de un servicio a la hora de hacer la cita

    public List<Estilista> findEstilistasServicios(int idServicio, Cita fechaCita) throws SQLException{
        List<Estilista> estilistas = new ArrayList<>();
        String sql = "SELECT DISTINCT e.nombre AS nombre_estilistas, e.imagen_perfil " +
                "FROM empleado e " +
                "JOIN estilista_servicio es ON e.id_empleado = es.id_estilista " +
                "JOIN servicio s ON es.id_servicio = s.id_servicio " +
                "JOIN estilista_horario eh ON e.id_empleado = eh.id_estilista " +
                "JOIN horario h ON eh.id_horario = h.id_horario " +
                "WHERE s.id_servicio = ? " +
                "  AND h.dia_semana =  ELT(WEEKDAY(?) + 1, 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo') " +
                "  AND TIME(?) BETWEEN h.hora_inicio AND h.hora_fin";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1,idServicio);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaCita.getFechaCita()));
            stmt.setTimestamp(3, Timestamp.valueOf(fechaCita.getFechaCita()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Estilista estilista = new Estilista();
                estilista.setNombre(rs.getString("nombre"));
                estilista.setImagenPerfil(rs.getString("imagen_perfil"));
                estilistas.add(estilista);
            }
        }
        return estilistas;
    }

    //añadir horario a estilista
    public void saveHorarios(Estilista estilista) throws SQLException{
        String sql = "INSERT INTO estilista_horario(id_estilista, id_horario) VALUES (?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, estilista.getIdEmpleado());
            stmt.setInt(2, estilista.getIdHorario());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La insercion fallo");
            }

        }
    }
    //añadir servicio a estilista
    public void saveServicios(Estilista relacion) throws SQLException{
        String sql = "INSERT INTO estilista_servicio(id_estilista, id_servicio) VALUES(?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,relacion.getIdEmpleado());
            stmt.setInt(2,relacion.getIdServicio());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La insercion fallo");
            }else {
                System.out.println("se completo la insercion");
            }
        }
    }

}
