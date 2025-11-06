package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Comentario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComentarioRepository {

    public List<Comentario> findAll() throws SQLException{
        List<Comentario> comentarios = new ArrayList<>();
        String  sql = "SELECT * FROM comentario";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()) {
                int id = rs.getInt("id_comentario");
                String comentario = rs.getString("comentario");
                Timestamp fecha = rs.getTimestamp("fecha_comentario");
                LocalDateTime fechaComentario = fecha.toLocalDateTime();
                int idCita = rs.getInt("id_cita");
                int idCliente = rs.getInt("id_cliente");
                Comentario comen = new Comentario(id,comentario,fechaComentario,idCita, idCliente);
                comentarios.add(comen);
            }
        }
        return comentarios;
    }

    public Comentario findById(int id) throws SQLException{
        Comentario comen = null;
        String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    String comentario = rs.getString("comentario");
                    Timestamp fecha = rs.getTimestamp("fecha_comentario");
                    LocalDateTime fechaComentario = fecha.toLocalDateTime();
                    int idCita = rs.getInt("id_cita");
                    int idCliente = rs.getInt("id_cliente");
                    comen = new Comentario(id,comentario,fechaComentario,idCita, idCliente);
                }
            }
        }
        return comen;
    }

    public int save(Comentario comentario)throws SQLException{
        String sql = "INSERT INTO comentario(comentario, id_cita, id_cliente) VALUES(?,?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            stmt.setString(1, comentario.getComentario());
            stmt.setInt(2,comentario.getIdCita());
            stmt.setInt(3,comentario.getIdCliente());
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

    public  void delete(int id) throws SQLException{
        String sql = "DELETE FROM comentario WHERE id_comentario = ?";
        try(
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
            };
        }
    }
    //no se puede actualizar un comentario
    public void update(Comentario comentario) throws SQLException{
        String sql = "UPDATE comentario SET comentario = ? WHERE id_comentario = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1,comentario.getComentario());
            stmt.setInt(2,comentario.getIdComentario());
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("actualizacion exitosa");
            };
        }
    }
}
