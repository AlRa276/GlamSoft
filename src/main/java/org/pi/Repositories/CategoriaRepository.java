package org.pi.Repositories;
import org.pi.Models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {

    public List<Categoria> findAllCategoria() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while(rs.next()){
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre_categoria");
                Categoria cat = new Categoria(id, nombre);
                categorias.add(cat);
            }
        }
        return categorias;
    }
    public Categoria findCategoria(int id) throws SQLException {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try (
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){
                    String nombre = rs.getString("nombre_categoria");
                    categoria = new Categoria(id, nombre);
                }
            }
        }
        return categoria;
    }

    public int saveCategoria(Categoria categoria) throws  SQLException {
        String sql = "INSERT INTO categoria(nombre_categoria) VALUES(?)";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        )  {
        stmt.setString(1,categoria.getNombreCategoria());
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

    public void deleteCategoria(int id) throws SQLException{
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
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

    public void updateCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1,categoria.getNombreCategoria());
            stmt.setInt(2,categoria.getIdCategoria());
            //verificacion
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0){
                System.out.println("No se encontro el id");
            }else {
                System.out.println("se actualizo el elemento con exito");
            }

        }
    }
}
