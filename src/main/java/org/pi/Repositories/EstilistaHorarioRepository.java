package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Empleado;
import org.pi.Models.EstilistaHorario;
import org.pi.Models.Horario;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EstilistaHorarioRepository {
    //todos los horarios
    public List<EstilistaHorario> findAll()throws SQLException{
        List<EstilistaHorario> estilistaHorarios = new ArrayList<>();
        String sql = "SELECT * FROM estilista_horario";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){
            while (rs.next()){
                int idEstilista = rs.getInt("id_estilista");
                int idHorario = rs.getInt("id_horario");
                EstilistaHorario estilistaHorario = new EstilistaHorario(idEstilista, idHorario);
                estilistaHorarios.add(estilistaHorario);
            }
        }
        return estilistaHorarios;
    }
    //lista de horarios de un estilista
    public List<Horario> find(int idEstilista) throws SQLException{
        List<Horario> horarios = new ArrayList<>();
        String sql = "SELECT h.id_horario, h.dia_semana, h.hora_inicio, h.hora_final " +
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

    public void save(EstilistaHorario relacion) throws SQLException{
        String sql = "INSERT INTO estilista_horario(id_estilista, id_horario) VALUES (?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, relacion.getIdEstilista());
            stmt.setInt(2, relacion.getIdHorario());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La insercion fallo");
            }

        }
    }

    public void delete(int idEstilista, int idHorario) throws SQLException{
        String sql = "DELETE FROM estilista_horario WHERE id_estilista = ? AND id_horario = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, idEstilista);
            stmt.setInt(2,idHorario);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La eliminacion fallo o no se encontaron los elementos");
            }else{
                System.out.println("se elimino exitosamente");
            }
        }
    }
}
