package org.pi.models;
import java.time.Period;
import java.time.LocalDateTime;
public class Reporte {
    private int idReporte;
    private Period periodo;
    private LocalDateTime fechaReporte;
    //private String datosReporte;

    public Reporte() {
    }

    public Reporte(int idReporte, Period periodo, LocalDateTime fechaReporte) {
        this.idReporte = idReporte;
        this.periodo = periodo;
        this.fechaReporte = fechaReporte;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public Period getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Period periodo) {
        this.periodo = periodo;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}
