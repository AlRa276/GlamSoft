package org.pi.Repositories;

import org.pi.Models.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository {
    public List<Persona> findAllPersona() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELEC * FROM persona";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            int idPersona = rs.getInt("id_persona");
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int idRol = rs.getInt("rol_id");
            Persona persona = new Persona(idPersona,nombre,telefono,email,password,idRol);
            personas.add(persona);
        }
        return  personas;
    }

    public Persona findPersona(int idPersona) throws SQLException{
        Persona persona = null;
        String sql = "SELEC * FROM persona WHERE id_persona = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,idPersona);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int idRol = rs.getInt("rol_id");
                    persona = new Persona(idPersona,nombre,telefono,email,password,idRol);
                }
            }
            return persona;
        }

    }

    public void savePersona(Persona persona) throws  SQLException {
        String sql = "INSERT INTO persona(id_persona,nombre,telefono,email,password,rol_id"+"VALUES(?,?,?,?,?,?)";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )  {
            stmt.setInt(1, persona.getIdPersona());
            stmt.setString(2,persona.getNombre());
            stmt.setString(3,persona.getTelefono());
            stmt.setString(4,persona.getEmail());
            stmt.setString(5,persona.getPassword());
            stmt.setInt(6,persona.getIdRol());
            stmt.executeUpdate();

        }
    }

    public void deletePersona(int idPersona) throws SQLException{
        String sql = "DELETE FROM persona WHERE id_perosna = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idPersona);
            stmt.executeUpdate();
        }
    }

    public void updatePersona(int idPersona, Persona persona) throws SQLException {
        String sql = "UPDATE persona SET nombre = ?, telefono = ?, email = ?, password = ?, rol_id = ? WHERE id_persona = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getTelefono());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4,persona.getPassword());
            stmt.setInt(5,persona.getIdRol());
            stmt.setInt(6, idPersona);
            stmt.executeUpdate();

        }
    }
}
