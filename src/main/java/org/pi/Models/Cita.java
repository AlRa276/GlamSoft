package org.pi.Models;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class Cita {
    private int idCita;
    private String estadoCita;
    private LocalDateTime fechaCita;
    private LocalDateTime fechaSolicitudCita;
    private int idCliente;
    private int idEstilista;
    private int idHorario;
    private List<Integer> servicios;

    //CONSTRUCTORES
    public Cita() {
    }

    public Cita(int idCita, String estadoCita, LocalDateTime fechaCita,
                LocalDateTime fechaSolicitudCita, int idCliente, int idEstilista, int idHorario, List<Integer> servicios) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.idCliente = idCliente;
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
        this.servicios = servicios;
    }

    //GETTERS AND SETTERS


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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEstilista() {
        return idEstilista;
    }

    public void setIdEstilista(int idEstilista) {
        this.idEstilista = idEstilista;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public List<Integer> getServicios() {
        return servicios;
    }

    public void setServicios(List<Integer> servicios) {
        this.servicios = servicios;
    }
}