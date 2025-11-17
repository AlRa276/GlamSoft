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
    //mostrar los ultimos 4
    public List<Portafolio> find4() throws SQLException{
        return portafolioRepository.find4();
    }

    public List<Portafolio> findAll() throws SQLException {
        return portafolioRepository.findAll();
    }

    public int save(Portafolio portafolio) throws SQLException {
        // Validaciones básicas
        String url = portafolio.getImageURL();
        List<Portafolio> imagenes = findAll();
        boolean existe = imagenes.stream().anyMatch(i ->i.getImageURL().equals(url));
        if (existe){
            throw new IllegalArgumentException("La imagen ya existe");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL de la imagen es obligatoria.");
        }
        if (portafolio.getNombreImagen() == null || portafolio.getNombreImagen().isBlank()) {
            throw new IllegalArgumentException("El nombre de la imagen es obligatorio.");
        }

        return portafolioRepository.save(portafolio);
    }

    public void delete(int idImagen) throws SQLException {
        if (idImagen <= 0) {
            throw new IllegalArgumentException("El ID de la imagen debe ser mayor a cero.");
        }
        portafolioRepository.delete(idImagen);
    }
    
    public void update(Portafolio portafolio) throws SQLException {
        if (portafolio.getIdImagen() <= 0) {
            throw new IllegalArgumentException("El ID de la imagen es inválido.");
        }
        if (portafolio.getNombreImagen() == null || portafolio.getNombreImagen().isBlank()) {
            throw new IllegalArgumentException("El nombre de la imagen no puede estar vacío.");
        }

        portafolioRepository.update(portafolio);
    }
}

