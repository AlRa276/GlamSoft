package org.pi.models;
import java.time.LocalDateTime;
public class Valoracion {
    private int idValoracion;
    private int calificacion;
    private String comentario;
    private LocalDateTime fecha;
    //La valoracion se hace al finalizar una cita
    private Cita cita;

    public Valoracion() {
    }

    public Valoracion(int idValoracion, int calificacion, String comentario, LocalDateTime fecha, Cita cita) {
        this.idValoracion = idValoracion;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cita = cita;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
