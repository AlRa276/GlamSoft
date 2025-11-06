package org.pi.Models;
import java.time.LocalDate;


public class Promocion {
    private int idPromocion;
    private String nombrePromocion;
    private String tipoDescuento;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Promocion(int idPromocion, String nombrePromocion,
                     String tipoDescuento, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.tipoDescuento = tipoDescuento;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Promocion() {
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
