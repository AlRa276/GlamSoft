package org.pi.Repositories;
import org.pi.Models.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void saveCategoria(Categoria categoria) throws  SQLException {
        String sql = "INSERT INTO categoria(id_categoria, nombre_categoria)"+"VALUES(?,?)";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )  {
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2,categoria.getNombreCategoria());
            stmt.executeUpdate();

        }
    }

    public void deleteCategoria(int id) throws SQLException{
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateCategoria(int id, Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nombre_categoria WHERE id_categoria = ?";
        try(
                Connection conn = org.pi.Config.DBconfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1,categoria.getNombreCategoria());
            stmt.setInt(2,id);
            stmt.executeUpdate();

        }
    }
}
