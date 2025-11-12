package org.pi.Services;

import org.pi.Models.Servicio;
import org.pi.Repositories.ServicioRepository;

import java.sql.SQLException;
import java.util.List;

public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    // 🔹 Listar todos los servicios
    public List<Servicio> findAllServicios() throws SQLException {
        return servicioRepository.findAll();
    }

    // 🔹 Buscar un servicio por ID
    public Servicio findServicio(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del servicio debe ser mayor a cero");
        }

        Servicio servicio = servicioRepository.find(id);
        if (servicio == null) {
            throw new IllegalArgumentException("No se encontró el servicio con ID: " + id);
        }

        return servicio;
    }

    // 🔹 Guardar nuevo servicio
    public int saveServicio(Servicio servicio) throws SQLException {
        validarServicio(servicio);
        return servicioRepository.save(servicio);
    }

    // 🔹 Actualizar servicio existente
    public void updateServicio(int id, Servicio servicio) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero");
        }
        validarServicio(servicio);

        // Asegurar que el ID sea consistente
        servicio.setIdServicio(id);

        servicioRepository.update(servicio);
    }

    // 🔹 Eliminar servicio
    public void deleteServicio(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a cero");
        }

        servicioRepository.delete(id);
    }

    // 🔹 Validaciones comunes
    private void validarServicio(Servicio servicio) {
        if (servicio.getNombreServicio() == null || servicio.getNombreServicio().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío");
        }
        if (servicio.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (servicio.getDuracionMinutos() <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a cero");
        }
        if (servicio.getIdCategoria() <= 0) {
            throw new IllegalArgumentException("Debe asignarse una categoría válida");
        }
    }
}

