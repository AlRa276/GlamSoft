package org.pi.Models;

import java.time.LocalDateTime;

public class Comentario {
    private int idComentario;
    private String comentario;
    private LocalDateTime fechaComentario;
    private int idCita;
    private int idCliente;
    private String emailCliente;


    public Comentario() {
    }

    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, int idCita, int idCliente, String emailCliente) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.emailCliente = emailCliente;
    }

    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, int idCita, int idCliente) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.idCita = idCita;
        this.idCliente = idCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
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
}
