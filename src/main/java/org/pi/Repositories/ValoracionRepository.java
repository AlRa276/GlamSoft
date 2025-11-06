package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.Valoracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValoracionRepository {
    public List<Valoracion> findAll()throws SQLException{
        List<Valoracion> valoraciones = new ArrayList<>();
        String sql = "SELECT * FROM valoracion";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){
            while(rs.next()){
                int id = rs.getInt("id_valoracion");
                double puntuacion = rs.getDouble("puntuacion");
                int idCita = rs.getInt("id_cita");
                int idCliente = rs.getInt("id_cliente");
                int idServicio = rs.getInt("id_servicio");
                Valoracion valoracion = new Valoracion(id, puntuacion, idCita, idCliente, idServicio);
                valoraciones.add(valoracion);
            }
        }
        return valoraciones;
    }

    public void save(Valoracion valoracion)throws SQLException{
        String sql = "INSERT INTO valoracion(puntuacion, id_cita, id_cliente, id_servicio) VALUES(?,?,?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setDouble(1,valoracion.getPuntuacion());
            stmt.setInt(2,valoracion.getIdCita());
            stmt.setInt(3,valoracion.getIdCliente());
            stmt.setInt(4,valoracion.getIdServicio());
            stmt.executeUpdate();
        }
    }
    public void delete(int id)throws SQLException{
        String sql = "DELETE FROM valoracion WHERE id_valoracion = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
