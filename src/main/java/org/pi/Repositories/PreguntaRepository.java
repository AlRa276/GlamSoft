package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Pregunta;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreguntaRepository {
    //encontrar preguntas de un formulario
    public List<Pregunta> FindFormulario(int id) throws SQLException{
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT id_pregunta, texto_pregunta FROM pregunta WHERE id_formulario = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    int idPregunta = rs.getInt("id_pregunta");
                    String pregunta = rs.getString("texto_pregunta");
                    Pregunta pregunta1 = new Pregunta(idPregunta, pregunta);
                    preguntas.add(pregunta1);
                }
            }
        }
        return preguntas;
    }
    //enontrar preguntas de un formulario asociado a un servicio
    public List<Pregunta> FindFormularioServicio(int id) throws SQLException{
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT p.texto_pregunta FROM pregunta p " +
                "JOIN servicio s ON p.id_formulario = s.id_formulario " +
                "WHERE s.id_servicio = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    String pregunta = rs.getString("texto_pregunta");
                    Pregunta pregunta1 = new Pregunta(pregunta);
                    preguntas.add(pregunta1);
                }
            }
        }
        return preguntas;
    }
    //encontrar todas las preguntas
    public List<Pregunta> findAll() throws SQLException{
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM pregunta";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            while(rs.next()){
                int idPregunta = rs.getInt("id_pregunta");
                String pregunta = rs.getString("texto_pregunta");
                String respuesta = rs.getString("texto_respuesta");
                int idFormulario = rs.getInt("id_formulario");
                Pregunta pre = new Pregunta(idPregunta, pregunta, respuesta, idFormulario);
                preguntas.add(pre);
            }
        }
        return preguntas;
    }
    //encontrar una pregunta por id
    public Pregunta find(int id) throws SQLException {
        Pregunta pregunta = null;
        String sql = "SELECT * FROM pregunta WHERE id_pregunta = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String preguntaTexto = rs.getString("texto_pregunta");
                    String respuestaTexto = rs.getString("texto_respuesta");
                    int idFormulario = rs.getInt("id_formulario");
                    pregunta = new Pregunta(id, preguntaTexto, respuestaTexto, idFormulario);
                }
            }
        }
        return pregunta;
    }
    //guardar una nueva pregunta
    public int save(Pregunta pregunta) throws SQLException {
        // En el modelo de DB, la respuesta puede ser NULL. Asumo que el modelo Pregunta tiene el campo 'respuesta'
        String sql = "INSERT INTO pregunta(texto_pregunta, id_formulario) VALUES(?, ?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, pregunta.getPregunta());
        
            stmt.setInt(2, pregunta.getIdFormulario());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La insercion de pregunta falló");
            }
            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                } else {
                    throw new SQLException("No se encontró id generado para pregunta");
                }
            }
        }
    }
    //eliminar una pregunta por id
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pregunta WHERE id_pregunta = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la pregunta con id: " + id);
            } else {
                System.out.println("Eliminación exitosa de la pregunta");
            }
        }
    }
    //actualizar una pregunta
    public void update(Pregunta pregunta) throws SQLException {
        String sql = "UPDATE pregunta SET texto_pregunta = ?, texto_respuesta = ? WHERE id_pregunta = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, pregunta.getPregunta());
            // Manejar campo opcional (respuesta)
            if (pregunta.getRespuesta() == null || pregunta.getRespuesta().isEmpty()) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, pregunta.getRespuesta());
            }

            stmt.setInt(3, pregunta.getIdPregunta());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró la pregunta con id: " + pregunta.getIdPregunta());
            } else {
                System.out.println("Se actualizó la pregunta con éxito");
            }
        }
    }
}
