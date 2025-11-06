package org.pi.Models;

import java.util.List;

public class EstilistaServicio {
    private int idServicio;
    private int idEstilista;
    private Empleado estilista;
    private List<Servicio> servicios;

    public EstilistaServicio() {
    }

    public EstilistaServicio(int idServicio, int idEstilista, Empleado estilista, List<Servicio> servicios) {
        this.idServicio = idServicio;
        this.idEstilista = idEstilista;
        this.estilista = estilista;
        this.servicios = servicios;
    }

    public EstilistaServicio(int idServicio, int idEstilista) {
        this.idServicio = idServicio;
        this.idEstilista = idEstilista;
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

    public Empleado getEstilista() {
        return estilista;
    }

    public void setEstilista(Empleado estilista) {
        this.estilista = estilista;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}