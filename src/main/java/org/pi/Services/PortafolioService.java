package org.pi.Services;

import org.pi.Models.Portafolio;
import org.pi.Repositories.PortafolioRepository;

import java.sql.SQLException;
import java.util.List;

public class PortafolioService {
    private final PortafolioRepository portafolioRepository;

    public PortafolioService(PortafolioRepository portafolioRepository) {
        this.portafolioRepository = portafolioRepository;
    }

    public List<Portafolio> findAllP()throws SQLException{
        return portafolioRepository.findAll();
    }

    public int saveP(Portafolio portafolio)throws SQLException{
        return portafolioRepository.save(portafolio);
    }

    public void deleteP(int id)throws SQLException{
        portafolioRepository.delete(id);
    }

    public void updateP(Portafolio portafolio)throws SQLException{
        portafolioRepository.update(portafolio);
    }
}
