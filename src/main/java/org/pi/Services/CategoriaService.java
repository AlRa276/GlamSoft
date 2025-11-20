package org.pi.Services;
import org.pi.Models.Categoria;
import org.pi.Repositories.CategoriaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;


public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository cr) {
        this.categoriaRepository = cr;
    }

    public List<Categoria> findAllCategoria() throws SQLException {
        return categoriaRepository.findAllCategoria();
    }
    public Categoria findCategoria(int id) throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        Categoria categoria = categoriaRepository.findCategoria(id);
        if (categoria == null){
            throw new NoSuchElementException("La categoria no existe");
        }
        return categoria;
    }

    public int saveCategoria(Categoria categoria) throws SQLException{
        String nombre = categoria.getNombreCategoria();

        List<Categoria> categorias = findAllCategoria();
        boolean existe = categorias.stream().anyMatch(c -> c.getNombreCategoria().equalsIgnoreCase(nombre));

        if (existe){
            throw new IllegalArgumentException("Ya existe una categoria con ese nombre");
        }
        return  categoriaRepository.saveCategoria(categoria);
    }

    public void deleteCategoria(int id) throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        Categoria categoria = categoriaRepository.findCategoria(id);
        if (categoria == null){
            throw new NoSuchElementException("No se puede eliminar la categoria. No se encontro la categoria");
        }
        categoriaRepository.deleteCategoria(id);
    }

}
