package org.pi.Models;

import java.util.List;

public class CitaServicio {
    private Cita cita;
    private List<Servicio> servicio;
    private int idCita;
    private int idServicio;

    public CitaServicio() {
    }

    public CitaServicio(Cita cita, List<Servicio> servicio, int idCita, int idServicio) {
        this.cita = cita;
        this.servicio = servicio;
        this.idCita = idCita;
        this.idServicio = idServicio;
    }
    public CitaServicio(int idCita, int idServicio) {
        this.idCita = idCita;
        this.idServicio = idServicio;
    }
    public CitaServicio(Cita cita, List<Servicio> servicio) {
        this.cita = cita;
        this.servicio = servicio;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Servicio> getServicio() {
        return servicio;
    }

    public void setServicio(List<Servicio> servicio) {
        this.servicio = servicio;
    }


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
