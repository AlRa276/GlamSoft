package org.pi.Repositories;
import org.pi.Config.DBconfig;
import org.pi.Models.Horario;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioRepository {
    public List<Horario> findAll()throws SQLException{
        List<Horario> horarios = new ArrayList<>();
        String sql = "SELECT * FROM horario";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
            while(rs.next()){
                int id = rs.getInt("id_horario");
                Time inicio = rs.getTime("hora_inicio");
                LocalTime horaInicio = inicio.toLocalTime();
                Time fin = rs.getTime("hora_fin");
                LocalTime horaFin = fin.toLocalTime();
                String diaSemana = rs.getString("dia_semana");
                Horario horario = new Horario(id, horaInicio, horaFin, diaSemana);
                horarios.add(horario);
            }
        }
        return horarios;
    }
    public int save(Horario horario)throws SQLException{
        String sql = "INSERT INTO horario(hora_inicio, hora_fin, dia_semana) VALUES(?,?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            LocalTime horaInicio = horario.getHoraInicio();
            stmt.setTime(1,(horaInicio!=null)? Time.valueOf(horaInicio) : null);
            LocalTime horaFin = horario.getHoraFin();
            stmt.setTime(2,(horaFin!=null)? Time.valueOf(horaFin):null);
            stmt.setString(3, horario.getDiaSemana());
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("falla en la insercion");
            }else {
                System.out.println("creacion exitosa");
            }
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
    public void delete(int id)throws SQLException{
        String sql = "DELETE FROM horario WHERE id_horario = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("eliminacion exitosa");
            }
        }
    }
    public void update(Horario horario)throws SQLException{
        String sql = "UPDATE horario SET hora_inicio = ?, hora_fin = ?, dia_semana = ? WHERE id_horario = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            LocalTime horaInicio = horario.getHoraInicio();
            stmt.setTime(1,Time.valueOf(horaInicio));
            LocalTime horaFin = horario.getHoraFin();
            stmt.setTime(2, Time.valueOf(horaFin));
            stmt.setString(3, horario.getDiaSemana());
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
