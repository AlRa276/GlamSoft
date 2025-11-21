package org.pi.Repositories;
import org.pi.Config.DBconfig;
import org.pi.Models.Formulario;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormularioRepository {
    //mostrar todos los formularios
    public List<Formulario> findAll() throws SQLException {
        List<Formulario> formularios = new ArrayList<>();
        String sql = "SELECT * FROM formulario";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Formulario formulario = new Formulario();
                // CORREGIDO: Faltaba asignar el ID
                formulario.setIdFormulario(rs.getInt("id_formulario"));
                formulario.setNombreFormulario(rs.getString("nombre_formulario"));
                formularios.add(formulario);
            }
        }
        return formularios;
    }

    public int save(Formulario formulario) throws SQLException {
        String sql = "INSERT INTO formulario(nombre_formulario) VALUES(?)";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, formulario.getNombreFormulario());
            int filasAfectadas = stmt.executeUpdate();
            // CORREGIDO: Manejo de error real
            if (filasAfectadas == 0) {
                throw new SQLException("Error al guardar formulario, no se afectaron filas");
            }

            try (ResultSet claves = stmt.getGeneratedKeys()) {
                if (claves.next()) {
                    int id = claves.getInt(1);
                    return id;
                } else {
                    throw new SQLException("No se encontro id");
                }
            }
        }
    }

    public void delete(int id_formulario) throws SQLException {
        String sql = "DELETE FROM formulario WHERE id_formulario = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id_formulario);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontro el id para eliminar");
            }
        }
    }

    public void update(Formulario formulario) throws SQLException {
        String sql = "UPDATE formulario SET nombre_formulario = ? WHERE id_formulario = ?";
        try (
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, formulario.getNombreFormulario());
            stmt.setInt(2, formulario.getIdFormulario());
            int filasAfectadas = stmt.executeUpdate();
            //verificacion
            if (filasAfectadas == 0) {
                System.out.println("No se encontro el id");
            } else {
                System.out.println("actualizacion exitosa");
            }
        }
    }
}