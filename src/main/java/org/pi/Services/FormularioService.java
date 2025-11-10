package org.pi.Services;

import org.pi.Repositories.FormularioRepository;
import org.pi.Models.Formulario;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.List;

public class FormularioService {
    private final FormularioRepository formularioRepository;

    public FormularioService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    public List<Formulario> findAllF()throws SQLException{
        return formularioRepository.findAll();
    }

    public int saveF(Formulario formulario) throws SQLException{
        return formularioRepository.save(formulario);
    }

    public void deleteF(int id)throws SQLException{
        formularioRepository.delete(id);
    }

    public void update(Formulario formulario)throws SQLException{
        formularioRepository.update(formulario);
    }
}
