package org.pi.Services;

import org.pi.Models.Comentario;
import org.pi.Repositories.ComentarioRepository;

import java.sql.SQLException;
import java.util.List;

public class ComentarioService {
    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> findAllC()throws SQLException{
        return comentarioRepository.findAll();
    }

    public Comentario findC(int id)throws SQLException{
        return comentarioRepository.findById(id);
    }

    public int saveC(Comentario comentario)throws SQLException{
        return comentarioRepository.save(comentario);
    }

    public void deleteC(int id)throws SQLException{
        comentarioRepository.delete(id);
    }

    public void updateC(Comentario comentario)throws SQLException{
        comentarioRepository.update(comentario);
    }
}
