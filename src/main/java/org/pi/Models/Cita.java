package org.pi.Models;
import java.time.LocalDateTime;
public class Cita {
    private int idCita;
    private String estadoCita;
    private String locacion;
    private LocalDateTime fechaCita;
    private LocalDateTime fechaSolicitudCita;
    private int idCliente;
    private int idEstilista;
    private int idHorario;
    private Persona cliente;
    private Persona estilista;
    private Horario horario;

    public Cita() {
    }

    public Cita(int idCita, String estadoCita, String locacion, LocalDateTime fechaCita, LocalDateTime fechaSolicitudCita, int idCliente, int idEstilista, int idHorario, Persona cliente, Persona estilista, Horario horario) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.locacion = locacion;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.idCliente = idCliente;
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
        this.cliente = cliente;
        this.estilista = estilista;
        this.horario = horario;
    }
    public Cita(int idCita, String estadoCita, String locacion, LocalDateTime fechaCita, LocalDateTime fechaSolicitudCita, int idCliente, int idEstilista, int idHorario) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.locacion = locacion;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.idCliente = idCliente;
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
    }
    public Cita(int idCita, String estadoCita, String locacion, LocalDateTime fechaCita, LocalDateTime fechaSolicitudCita, Persona cliente, Persona estilista, Horario horario) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.locacion = locacion;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.cliente = cliente;
        this.estilista = estilista;
        this.horario = horario;
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

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
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

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Persona getEstilista() {
        return estilista;
    }

    public void setEstilista(Persona estilista) {
        this.estilista = estilista;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
