package org.pi.dto;

import org.slf4j.IMarkerFactory;
import java.time.LocalDateTime;

public class CitaDTO {
        private int idCita;
        private String estadoCita;
        private LocalDateTime fechaCita;
        private LocalDateTime fechaSolicitudCita;
        private String clienteEmail;
        private String estilistaNombre;
        private String nombresServicios;
        private double precioTotal;


    public CitaDTO() {
    }

    public CitaDTO(int idCita, String estadoCita, LocalDateTime fechaCita, LocalDateTime fechaSolicitudCita, String clienteEmail, String estilistaNombre, String nombresServicios, double precioTotal) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.clienteEmail = clienteEmail;
        this.estilistaNombre = estilistaNombre;
        this.nombresServicios = nombresServicios;
        this.precioTotal = precioTotal;
    }

    public CitaDTO(int idCita, String estadoCita, LocalDateTime fechaCita,
                   LocalDateTime fechaSolicitudCita, String clienteEmail, String estilistaNombre, String nombresServicios) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.clienteEmail = clienteEmail;
        this.estilistaNombre = estilistaNombre;
        this.nombresServicios = nombresServicios;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalDateTime getFechaSolicitudCita() {
        return fechaSolicitudCita;
    }

    public void setFechaSolicitudCita(LocalDateTime fechaSolicitudCita) {
        this.fechaSolicitudCita = fechaSolicitudCita;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getEstilistaNombre() {
        return estilistaNombre;
    }

    public void setEstilistaNombre(String estilistaNombre) {
        this.estilistaNombre = estilistaNombre;
    }

    public String getNombresServicios() {
        return nombresServicios;
    }

    public void setNombresServicios(String nombresServicios) {
        this.nombresServicios = nombresServicios;
    }
}
