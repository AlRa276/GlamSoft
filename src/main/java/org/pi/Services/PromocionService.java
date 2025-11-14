package org.pi.Services;
import org.pi.Models.Promocion;
import org.pi.Models.Servicio;
import org.pi.Repositories.PromocionRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class PromocionService {
    private final PromocionRepository promocionRepository;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> findAll() throws SQLException {
        return promocionRepository.findAll();
    }


    public Promocion findById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser mayor a cero");
        }

        Promocion promocion = promocionRepository.find(id);
        if (promocion == null) {
            throw new NoSuchElementException("No se encontró la promoción con el ID especificado");
        }
        return promocion;
    }


    public int save(Promocion promocion) throws SQLException {
        validarPromocion(promocion);
        return promocionRepository.save(promocion);
    }

    public void update(Promocion promocion) throws SQLException {
        if (promocion.getIdPromocion() <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser válido para actualizar");
        }
        validarPromocion(promocion);
        promocionRepository.update(promocion);
    }

    public void delete(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser mayor a cero");
        }
        promocionRepository.delete(id);
    }

    public List<Servicio> findServicios(int idPromocion) throws SQLException {
        if (idPromocion <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser mayor a cero");
        }

        List<Servicio> servicios = promocionRepository.findServicios(idPromocion);
        if (servicios.isEmpty()) {
            throw new NoSuchElementException("La promoción no tiene servicios asignados");
        }

        return servicios;
    }

    public void saveServicio(Promocion promocion) throws SQLException {
        if (promocion.getIdPromocion() <= 0) {
            throw new IllegalArgumentException("El ID de la promoción debe ser mayor a cero");
        }
        if (promocion.getIdServicio() <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser mayor a cero");
        }

        // Evitar duplicados
        List<Servicio> serviciosExistentes = promocionRepository.findServicios(promocion.getIdPromocion());
        boolean yaAsignado = serviciosExistentes.stream()
                .anyMatch(s -> s.getIdServicio() == promocion.getIdServicio());

        if (yaAsignado) {
            throw new IllegalArgumentException("El servicio ya está asignado a esta promoción");
        }

        promocionRepository.saveServicios(promocion);
    }


    //Validación interna de campos de promoción
    private void validarPromocion(Promocion p) {
        if (p.getNombrePromocion() == null || p.getNombrePromocion().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la promoción es obligatorio");
        }
        if (p.getTipoDescuento() == null || p.getTipoDescuento().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de descuento es obligatorio");
        }
        if (p.getDescuento() <= 0) {
            throw new IllegalArgumentException("El valor del descuento debe ser mayor a cero");
        }
        if (p.getFechaInicio() == null || p.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (p.getFechaFin().isBefore(p.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
        if (p.getFechaFin().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La promoción no puede tener una fecha de fin en el pasado");
        }
    }
}

