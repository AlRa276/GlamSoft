package org.pi.Models;

public class Valoracion {
    private int idValoracion;
    private int puntuacion;
    private Cita cita;
    private int idCita;
    private Persona cliente;
    private int idCliente;

    public Valoracion() {
    }

    public Valoracion(int idValoracion, int puntuacion, Cita cita, int idCita, Persona cliente, int idCliente) {
        this.idValoracion = idValoracion;
        this.puntuacion = puntuacion;
        this.cita = cita;
        this.idCita = idCita;
        this.cliente = cliente;
        this.idCliente = idCliente;
    }

    public Valoracion(int idValoracion, int puntuacion, int idCliente, int idCita){
        this.idValoracion = idValoracion;
        this.puntuacion = puntuacion;
        this.idCliente = idCliente;
        this.idCita = idCita;
    }

    public Valoracion(int idValoracion, int puntuacion, Persona cliente, Cita cita) {
        this.idValoracion = idValoracion;
        this.puntuacion = puntuacion;
        this.cliente = cliente;
        this.cita = cita;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
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

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
