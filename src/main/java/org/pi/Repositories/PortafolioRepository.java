package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Portafolio;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortafolioRepository {
    public List<Portafolio> findAll()throws SQLException{
        List<Portafolio> portafolios = new ArrayList<>();
        String sql = "SELECT * FROM portafolio";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
            while (rs.next()){
                int idImagen = rs.getInt("id_imagen");
                String imagenURL = rs.getString("url");
                String nombreImagen = rs.getString("nombre_imagen");
                Portafolio portafolio = new Portafolio(idImagen, imagenURL, nombreImagen);
                portafolios.add(portafolio);
            }
        }
        return portafolios;
    }

    public int save(Portafolio portafolio)throws SQLException{
        String sql = "INSERT INTO portafolio(url, nombre_imagen) VALUES(?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            stmt.setString(1, portafolio.getImageURL());
            stmt.setString(2, portafolio.getNombreImagen());
            //verificacion
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas == 0){
                throw new SQLException("ERROR");
            }
            //obtener id
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

    public void delete(int idImagen)throws SQLException{
        String sql = "DELETE FROM portafolio WHERE id_imagen = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, idImagen);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0){
                throw new SQLException("ERROR");
            }
        }
    }

    public void update(Portafolio portafolio)throws SQLException{
        String sql = "UPDATE portafolio SET nombre_imagen = ? WHERE id_imagen = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, portafolio.getNombreImagen());
            stmt.setInt(2, portafolio.getIdImagen());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("ERROR");
            }
        }
    }
}
