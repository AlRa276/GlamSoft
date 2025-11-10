package org.pi.Services;
import org.pi.Models.Categoria;
import org.pi.Repositories.CategoriaRepository;

import java.sql.SQLException;
import java.util.List;
//falta la logica de las reglas del negocio
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository cr) {
        this.categoriaRepository = cr;
    }

    public List<Categoria> findAllCategoria() throws SQLException {
        return categoriaRepository.findAllCategoria();
    }
    public Categoria findCategoria(int id) throws SQLException{
        return categoriaRepository.findCategoria(id);
    }

    public void saveCategoria(Categoria categoria) throws SQLException{
        categoriaRepository.saveCategoria(categoria);
    }

    public void deleteCategoria(int id) throws SQLException{
        categoriaRepository.deleteCategoria(id);
    }

    public void updateCategoria( Categoria categoria) throws SQLException{
        categoriaRepository.updateCategoria(categoria);
    }
}
