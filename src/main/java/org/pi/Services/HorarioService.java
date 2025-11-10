package org.pi.Services;

import org.pi.Models.Horario;
import org.pi.Repositories.HorarioRepository;

import java.sql.SQLException;
import java.util.List;

public class HorarioService {
    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> findAllH() throws SQLException{
        return horarioRepository.findAll();
    }

    public int saveH(Horario horario) throws SQLException{
        return horarioRepository.save(horario);
    }

    public void deleteH(int id)throws SQLException{
        horarioRepository.delete(id);
    }

    public void updateH(Horario horario)throws SQLException{
        horarioRepository.update(horario);
    }
}
