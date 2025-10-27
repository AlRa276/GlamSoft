package org.pi.Repositories;

import org.pi.Models.Valoracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValoracionRepository {
    public List<Valoracion> findAllValoracion() throws SQLException {
        List<Valoracion> valoracions = new ArrayList<>();
        String sql = "SELEC * FROM valoracion";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            int idValoracion = rs.getInt("id_valoracion");
            int puntuacion = rs.getInt("puntuacion");
            int idCliente = rs.getInt("id_cliente");
            int idCita = rs.getInt("id_cita");
            Valoracion valoracion = new Valoracion(idValoracion,puntuacion,idCliente,idCita);
            valoracions.add(valoracion);
        }
        return  valoracions;
    }

    public Valoracion findValoracion(int idValoracion) throws SQLException{
        Valoracion valoracion = null;
        String sql = "SELEC * FROM valoracion WHERE id_valoracion = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,idValoracion);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){
                    int puntuacion = rs.getInt("puntuacion");
                    int idCliente = rs.getInt("id_cliente");
                    int idCita = rs.getInt("id_cita");
                    valoracion = new Valoracion(idValoracion,puntuacion,idCliente,idCita);
                }
            }
            return valoracion;
        }

    }

    public void saveValoracion(Valoracion valoracion) throws  SQLException {
        String sql = "INSERT INTO valoracion(id_valoracion,puntuacion,id_cliente,id_cita)"+"VALUES(?,?,?,?)";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )  {
            stmt.setInt(1, valoracion.getIdValoracion());
            stmt.setInt(2,valoracion.getPuntuacion());
            stmt.setInt(3,valoracion.getIdCliente());
            stmt.setInt(4,valoracion.getIdCita());
            stmt.executeUpdate();

        }
    }

    public void deletevaloracion(int idValoracion) throws SQLException{
        String sql = "DELETE FROM valoracion WHERE id_valoracion = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idValoracion);
            stmt.executeUpdate();
        }
    }

    public void updatevaloracion(int idValoracion, Valoracion valoracion) throws SQLException {
        String sql = "UPDATE valoracion SET id_valoracion = ?, puntuacion = ?, id_cliente = ?, id_cita = ? WHERE id_valoracion = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1,valoracion.getIdValoracion());
            stmt.setInt(2,valoracion.getPuntuacion());
            stmt.setInt(3,valoracion.getIdCliente());
            stmt.setInt(4,valoracion.getIdCita());
            stmt.setInt(5, idValoracion);
            stmt.executeUpdate();

        }
    }
}
