package org.pi.Models;

import java.time.LocalDateTime;

public class Comentario {
    private int idComentario;
    private String comentario;
    private LocalDateTime fechaComentario;
    private int idCita;
    private int idCliente;
    private Cita cita;
    private Usuario cliente;

    public Comentario() {
    }
    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, int idCita, int idCliente, Cita cita, Usuario cliente) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.cita = cita;
        this.cliente = cliente;
    }
    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, int idCita, int idCliente) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.idCita = idCita;
        this.idCliente = idCliente;
    }
    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, Cita cita, Usuario cliente) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.cita = cita;
        this.cliente = cliente;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
}
