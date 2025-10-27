package org.pi.Models;

public class CitaServicio {
    private Cita cita;
    private Servicio servicio;
    private double precioTotal;
    private int idCita;
    private int idServicio;

    public CitaServicio() {
    }

    public CitaServicio(Cita cita, Servicio servicio, double precioTotal, int idCita, int idServicio) {
        this.cita = cita;
        this.servicio = servicio;
        this.precioTotal = precioTotal;
        this.idCita = idCita;
        this.idServicio = idServicio;
    }
    public CitaServicio(double precioTotal, int idCita, int idServicio) {
        this.precioTotal = precioTotal;
        this.idCita = idCita;
        this.idServicio = idServicio;
    }
    public CitaServicio(Cita cita, Servicio servicio, double precioTotal) {
        this.cita = cita;
        this.servicio = servicio;
        this.precioTotal = precioTotal;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
