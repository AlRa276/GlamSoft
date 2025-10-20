package org.pi.models;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class Cita {
    private int idCita;
    private LocalDateTime fechaCita;
    private String estado;
    private String locacion;
    private ZonedDateTime fechaSolicitud;
    //Una cita la hace una clienta
    private Clienta cliente;
    //Una cita puede tener n servicios
    private List<Servicio> servicios;

    public Cita() {
    }

    public Cita(int idCita, LocalDateTime fechaCita, String estado, String locacion, ZonedDateTime fechaSolicitud, Clienta cliente, List<Servicio> servicios) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.estado = estado;
        this.locacion = locacion;
        this.fechaSolicitud = fechaSolicitud;
        this.cliente = cliente;
        this.servicios = servicios;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public ZonedDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(ZonedDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Clienta getCliente() {
        return cliente;
    }

    public void setCliente(Clienta cliente) {
        this.cliente = cliente;
    }


    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
