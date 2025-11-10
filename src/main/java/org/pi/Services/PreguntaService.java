package org.pi.Services;

import org.pi.Models.Pregunta;
import org.pi.Repositories.PreguntaRepository;

import java.sql.SQLException;
import java.util.List;

public class PreguntaService {
    private final PreguntaRepository preguntaRepository;

    public PreguntaService(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    public List<Pregunta> findAllP()throws SQLException{
        return preguntaRepository.findAll();
    }

    public Pregunta findP(int id) throws SQLException{
        return preguntaRepository.find(id);
    }

    public int saveP(Pregunta pregunta)throws SQLException{
        return preguntaRepository.save(pregunta);
    }

    public void deleteP(int id) throws SQLException{
        preguntaRepository.delete(id);
    }

    public void updateP(Pregunta pregunta)throws SQLException{
        preguntaRepository.update(pregunta);
    }
}
