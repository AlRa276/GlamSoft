package org.pi.Services;
import org.pi.Models.EstilistaHorario;
import org.pi.Models.Horario;
import org.pi.Repositories.EstilistaHorarioRepository;

import java.sql.SQLException;
import java.util.List;

public class EstilistaHorarioService {
    private final EstilistaHorarioRepository estilistaHorarioRepository;

    public EstilistaHorarioService(EstilistaHorarioRepository estilistaHorarioRepository) {
        this.estilistaHorarioRepository = estilistaHorarioRepository;
    }

    public List<EstilistaHorario> findAllEH()throws SQLException{
        return estilistaHorarioRepository.findAll();
    }

    public List<Horario> findEH(int idEstilista)throws SQLException{
        return estilistaHorarioRepository.find(idEstilista);
    }

    public void saveEH(EstilistaHorario relacion)throws SQLException{
        estilistaHorarioRepository.save(relacion);
    }

    public void deleteEH(int idEstilista, int idHorario)throws SQLException{
        estilistaHorarioRepository.delete(idEstilista, idHorario);
    }
}
