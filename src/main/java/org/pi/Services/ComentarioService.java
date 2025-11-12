package org.pi.Services;

import org.pi.Models.Comentario;
import org.pi.Repositories.ComentarioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class ComentarioService {
    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> findAllC()throws SQLException{
        return comentarioRepository.findAll();
    }

    public Comentario findC(int id)throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        return   comentarioRepository.findById(id);

    }

    public int saveC(Comentario comentario)throws SQLException{
        String texto = comentario.getComentario();
        if (texto != null){
            return comentarioRepository.save(comentario);
        }
       return 0;
    }

    public void deleteC(int id)throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        Comentario existe = comentarioRepository.findById(id);
        if (existe == null){
            throw new NoSuchElementException("No se puede eliminar: comentario inexistente");
        }
        comentarioRepository.delete(id);
    }

    public void updateC(Comentario comentario)throws SQLException{
        if (comentario.getIdComentario() <= 0){
            throw new IllegalArgumentException("El id debe ser un numero entero positivo");
        }
        Comentario existe = comentarioRepository.findById(comentario.getIdComentario());
        if (existe == null){
            throw new NoSuchElementException("No se puede actualizar: comentario inexistente");
        }
        comentarioRepository.update(comentario);
    }
}
