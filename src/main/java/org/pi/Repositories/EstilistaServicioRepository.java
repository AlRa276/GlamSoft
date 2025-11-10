package org.pi.Repositories;

import org.pi.Config.DBconfig;
import org.pi.Models.EstilistaServicio;
import org.pi.Models.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstilistaServicioRepository {
    public List<EstilistaServicio> findAll() throws SQLException{
        List<EstilistaServicio> estilistaServicios = new ArrayList<>();
        String sql = "SELECT * FROM estilista_servicio";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
            while(rs.next()){
                int idEstilista = rs.getInt("id_estilista");
                int idServicio = rs.getInt("id_servicio");
                EstilistaServicio es = new EstilistaServicio(idEstilista, idServicio);
                estilistaServicios.add(es);
            }
        }
        return estilistaServicios;
    }
    //servicios que puede hacer un estilista
    public List<Servicio> find(int idEstilista) throws SQLException{
        List<Servicio> servicios = new ArrayList<>();
        String sql = " SELECT s.id_servicio, s.nombre_servicio, s.descripcion " +
                "FROM empleado e INNER JOIN estilista_servicio es ON e.id_empleado = es.id_estilista " +
                "INNER JOIN servicio s ON es.id_servicio = s.id_servicio " +
                "WHERE e.id_empleado = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, idEstilista);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int idServicio = rs.getInt("id_servicio");
                    String nombreServicio = rs.getString("nombre_servicio");
                    String descripcion = rs.getString("descripcion");
                    Servicio servicio = new Servicio(idServicio, nombreServicio, descripcion);
                    servicios.add(servicio);
                }
            }
        }
        return servicios;
    }
    public void save(EstilistaServicio relacion) throws SQLException{
        String sql = "INSERT INTO estilista_servicio(id_estilista, id_servicio) VALUES(?,?)";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1,relacion.getIdEstilista());
            stmt.setInt(2,relacion.getIdServicio());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La insercion fallo");
            }else {
                System.out.println("se completo la insercion");
            }
        }
    }
    public void delete(int idEstilista, int idServicio) throws SQLException{
        String sql = "DELETE FROM estilista_servicio WHERE id_estilista = ? AND id_servicio = ?";
        try(
                Connection conn = DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,idEstilista);
            stmt.setInt(2, idServicio);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                throw new SQLException("La eliminacion fallo o no se encontaron los elementos");
            }else{
                System.out.println("se elimino exitosamente");
            }
        }
    }
}
