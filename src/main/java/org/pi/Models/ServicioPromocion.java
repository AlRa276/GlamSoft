package org.pi.Models;

public class ServicioPromocion {
    private int idServicio;
    private Servicio servicio;
    private int idPromocion;
    private Promocion promocion;

    public ServicioPromocion(int idServicio, Servicio servicio, int idPromocion, Promocion promocion) {
        this.idServicio = idServicio;
        this.servicio = servicio;
        this.idPromocion = idPromocion;
        this.promocion = promocion;
    }

    public ServicioPromocion(int idServicio, int idPromocion) {
        this.idServicio = idServicio;
        this.idPromocion = idPromocion;
    }

    public ServicioPromocion() {
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

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
}
