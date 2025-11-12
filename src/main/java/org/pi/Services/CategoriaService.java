package org.pi.Services;
import org.pi.Models.Categoria;
import org.pi.Repositories.CategoriaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void updateCategoria( Categoria categoria) throws SQLException{
        if (categoria.getIdCategoria() <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        Categoria existe = categoriaRepository.findCategoria(categoria.getIdCategoria());
        if (existe == null){
            throw new NoSuchElementException("No se puede actualizar la categoria. No se encontro la categoria");
        }
        String nombre = categoria.getNombreCategoria();
        List<Categoria> categorias = findAllCategoria();
        boolean nombreExiste = categorias.stream().anyMatch(c -> c.getNombreCategoria().equalsIgnoreCase(nombre)
                && c.getIdCategoria() != categoria.getIdCategoria());

        if (nombreExiste){
            throw new IllegalArgumentException("Otra categoria ya tiene ese nombre");
        }
        categoriaRepository.updateCategoria(categoria);
    }
}
