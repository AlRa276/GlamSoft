package org.pi.Services;
import org.pi.Models.Categoria;
import org.pi.Models.Rol;
import org.pi.Repositories.CategoriaRepository;
import org.pi.Repositories.RolRepository;
import java.sql.SQLException;
import java.util.List;

public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rr) {
        this.rolRepository = rr;
    }

    public List<Rol> findAllRol() throws SQLException {
        return rolRepository.findAllRol();
    }
    public Rol findRol(int idRol) throws SQLException{
        return rolRepository.findRol(idRol);
    }

    public void saveRol(Rol rol) throws SQLException{
        rolRepository.saveRol(rol);
    }

    public void deleteRol(int idRol) throws SQLException{
        rolRepository.deleteRol(idRol);
    }

    public void updateRol(int idRol, Rol rol) throws SQLException{
        rolRepository.updateRol(idRol,rol);
    }
}
