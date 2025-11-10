package org.pi.Services;
import org.pi.Models.Promocion;
import org.pi.Repositories.PromocionRepository;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.List;

public class PromocionService {
    private final PromocionRepository promocionRepository;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> findAllP()throws SQLException{
        return promocionRepository.findAll();
    }

    public Promocion findP(int id)throws SQLException{
        return promocionRepository.find(id);
    }

    public int saveP(Promocion promocion)throws SQLException{
        return promocionRepository.save(promocion);
    }

    public void deleteP(int id)throws SQLException{
        promocionRepository.delete(id);
    }

    public void updateP(Promocion promocion)throws SQLException{
        promocionRepository.update(promocion);
    }
}
