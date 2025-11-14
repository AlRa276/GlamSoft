package org.pi.Services;

import org.pi.Models.Cita;
import org.pi.Repositories.CitaRepository;
import org.pi.dto.CitaDTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<CitaDTO> citasPorMes(int mes, int año) throws SQLException {
        return citaRepository.findCitasMes(mes, año);
    }

    public List<CitaDTO> citasPorSemana(int año, int semana) throws SQLException {
        return citaRepository.findCitasSemana(año, semana);
    }

    public List<CitaDTO> citasPorDia(LocalDate fecha) throws SQLException {
        return citaRepository.findCitasDia(fecha);
    }

    public List<CitaDTO> findAllCitas() throws SQLException{
        return citaRepository.findAllCitas();
    }
    public List<CitaDTO> historialClienteCitas(int idCliente) throws SQLException{
        return citaRepository.findCitaCliente(idCliente);
    }
    public CitaDTO findCita(int id) throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        CitaDTO cita = citaRepository.findCitaById(id);
        if (cita == null){
            throw new NoSuchElementException("La cita no existe");
        }
        return cita;
    }

    public int saveCita(Cita cita)throws Exception{
        LocalDateTime fechaSolicitud = cita.getFechaSolicitudCita();
        LocalDateTime fechaCita = cita.getFechaCita();
        List<Integer> servicioIds = cita.getServicios();
        //validar fecha
        if (!fechaCita.isAfter(fechaSolicitud)) {
            throw new Exception("La fecha de la cita debe ser despues de la fecha de solicitud.");
        }

        long diferenciaMinutos = Duration.between(fechaSolicitud, fechaCita).toMinutes();

        if (diferenciaMinutos < 30) {
            throw new Exception("La reserva de la cita debe hacerce con media hora de anticipacion");
        }
        if (cita.getServicios() == null || cita.getServicios().isEmpty()) {
            throw new IllegalArgumentException("La cita debe tener al menos un servicio asociado.");
        }
        int duracionTotalMinutos = citaRepository.calcularDuracionTotal(servicioIds);
        LocalDateTime horaFinCita = fechaCita.plusMinutes(duracionTotalMinutos);

        //evitar citas sobrepuesta
        if (citaRepository.existeTraslape(cita.getIdEstilista(), fechaCita, horaFinCita)) {
            throw new IllegalStateException("El estilista no está disponible en el horario seleccionado (Traslape).");
        }

        if (cita.getEstadoCita() == null || cita.getEstadoCita().trim().isEmpty()) {
            cita.setEstadoCita("PENDIENTE");
        }
        return citaRepository.save(cita);
    }

    public void deleteCita(int id) throws SQLException{
        if (id <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        CitaDTO cita = citaRepository.findCitaById(id);
        if (cita == null){
            throw new NoSuchElementException("No se puede eliminar la cita no existe");
        }
        citaRepository.delete(id);
    }

    public void StatusCita(Cita cita) throws SQLException{
        if (cita.getIdCita() <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        if (!cita.getEstadoCita().equalsIgnoreCase("FINALIZADA")){
            citaRepository.updateStatus(cita);
        }
    }

    public void fechaCita(Cita cita)throws Exception{
        if (cita.getIdCita() <= 0){
            throw new IllegalArgumentException("La id debe ser mayor a cero");
        }
        if (cita.getEstadoCita().equalsIgnoreCase("CANCELADA") || cita.getEstadoCita().equalsIgnoreCase("CONFIRMADA")){
            LocalDateTime fechaSolicitud = cita.getFechaSolicitudCita();
            LocalDateTime fechaCita = cita.getFechaCita();
            if (!fechaCita.isAfter(fechaSolicitud)) {
                throw new Exception("La fecha de la cita debe ser despues de la fecha de solicitud.");
            }
            //falta logica para evitar citas sobrePuestas
            citaRepository.updateFecha(cita);
        }

    }
}