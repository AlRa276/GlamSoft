package org.pi.Models;

public class Valoracion {
    private int idValoracion;
    private double puntuacion;
    private Cita cita;
    private int idCita;
    private Usuario cliente;
    private int idCliente;
    private int idServicio;
    private Servicio servicio;

    public Valoracion() {
    }
    public Valoracion(int id, double puntuacion, int idCita, int idCliente, int idServicio){
        this.idValoracion = id;
        this.puntuacion = puntuacion;
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.idServicio = idServicio;
    }
    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Valoracion(int idValoracion, double puntuacion, Cita cita, int idCita, Usuario cliente, int idCliente, int idServicio, Servicio servicio) {
        this.idValoracion = idValoracion;
        this.puntuacion = puntuacion;
        this.cita = cita;
        this.idCita = idCita;
        this.cliente = cliente;
        this.idCliente = idCliente;
        this.idServicio = idServicio;
        this.servicio = servicio;
    }


}
