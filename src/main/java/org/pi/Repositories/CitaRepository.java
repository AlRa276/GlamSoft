package org.pi.Repositories;
import org.pi.Config.DBconfig;
import org.pi.Models.Cita;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class CitaRepository {

    public List<Cita> findAll() throws SQLException{
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ) {
            while(rs.next()){
                int id = rs.getInt("id_cita");
                String estadoCita = rs.getString("estado_cita");
                Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                LocalDateTime fechaCita = fecha.toLocalDateTime();
                Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                LocalDateTime fechaSolicitud = solicitud.toLocalDateTime();
                int idCliente = rs.getInt("id_cliente");
                int idEstilista = rs.getInt("id_estilista");
                int idHorario = rs.getInt("id_horario");
                Cita cita = new Cita(id, estadoCita, fechaCita , fechaSolicitud, idCliente, idEstilista, idHorario);
                citas.add(cita);
            }
        }
        return citas;
    }

    public Cita findById(int id) throws SQLException{
        Cita cita = null;
        String sql = "SELECT * FROM cita WHERE id_cita = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    String estadoCita = rs.getString("estado_cita");
                    Timestamp fecha = rs.getTimestamp("fecha_hora_cita");
                    LocalDateTime fechaCita = fecha.toLocalDateTime();
                    Timestamp solicitud = rs.getTimestamp("fecha_solicitud");
                    LocalDateTime fechaSolicitud = solicitud.toLocalDateTime();
                    int idCliente = rs.getInt("id_cliente");
                    int idEstilista = rs.getInt("id_estilista");
                    int idHorario = rs.getInt("id_horario");
                    cita = new Cita(id, estadoCita, fechaCita , fechaSolicitud, idCliente, idEstilista, idHorario);
                }
            }
        }
        return cita;
    }

    public int save(Cita cita) throws SQLException{
        String sql = "INSERT INTO cita(estado_cita, fecha_hora_cita, id_cliente, id_estilista, id_horario) VALUES(?,?,?,?,?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            stmt.setString(1, cita.getEstadoCita());
            LocalDateTime fechaCita = cita.getFechaCita();
            stmt.setTimestamp(2, Timestamp.valueOf(fechaCita));
            stmt.setInt(3, cita.getIdCliente());
            stmt.setInt(4, cita.getIdCliente());
            stmt.setInt(5,cita.getIdHorario());
            //verificacion
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La insercion fallo");
            }
            //encontrar id
            try(ResultSet claves = stmt.getGeneratedKeys()){
                if(claves.next()){
                    int id = claves.getInt(1);
                    return id;
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
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
}
