package org.pi.Models;

public class EstilistaServicio {
    private Servicio servicio;
    private int idServicio;
    private int idEstilista;
    private Persona estilista;

    public EstilistaServicio() {
    }

    public EstilistaServicio(Servicio servicio, int idServicio, int idEstilista, Persona estilista) {
        this.servicio = servicio;
        this.idServicio = idServicio;
        this.idEstilista = idEstilista;
        this.estilista = estilista;
    }
    public EstilistaServicio(int idServicio, int idEstilista) {
        this.idServicio = idServicio;
        this.idEstilista = idEstilista;
    }
    public EstilistaServicio(Servicio servicio, Persona estilista) {
        this.servicio = servicio;
        this.estilista = estilista;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdEstilista() {
        return idEstilista;
    }

    public void setIdEstilista(int idEstilista) {
        this.idEstilista = idEstilista;
    }

    public Persona getEstilista() {
        return estilista;
    }

    public void setEstilista(Persona estilista) {
        this.estilista = estilista;
    }
}