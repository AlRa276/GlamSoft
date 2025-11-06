package org.pi.Models;
import java.time.LocalDateTime;
public class Cita {
    private int idCita;
    private String estadoCita;
    private LocalDateTime fechaCita;
    private LocalDateTime fechaSolicitudCita;
    private int idCliente;
    private int idEstilista;
    private int idHorario;
    private Usuario cliente;
    private Empleado estilista;
    private Horario horario;

    public Cita(int idCita, String estadoCita, LocalDateTime fechaCita , LocalDateTime fechaSolicitud,
                int idCliente, int idEstilista, int idHorario){
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitud;
        this.idCliente = idCliente;
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
    }
    public Cita(int idCita, String estadoCita, LocalDateTime fechaCita, LocalDateTime fechaSolicitudCita,
                int idCliente, int idEstilista, int idHorario, Usuario cliente, Empleado estilista, Horario horario) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.fechaSolicitudCita = fechaSolicitudCita;
        this.idCliente = idCliente;
        this.idEstilista = idEstilista;
        this.idHorario = idHorario;
        this.cliente = cliente;
        this.estilista = estilista;
        this.horario = horario;
    }

    public Cita() {
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Empleado getEstilista() {
        return estilista;
    }

    public void setEstilista(Empleado estilista) {
        this.estilista = estilista;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
