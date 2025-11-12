package org.pi.Models;

public class Estilista extends Empleado {
    private int idHorario;
    private int idServicio;

    public Estilista() {
    }

    public Estilista(int idHorario, int idServicio) {
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public Estilista(int idRol, String email, String nombre, String telefono, int idHorario, int idServicio) {
        super(idRol, email, nombre, telefono);
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public Estilista(String email, String nombre, String telefono, int idHorario, int idServicio) {
        super(email, nombre, telefono);
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public Estilista(int idUsuario, String email, String password, int idRol, String nombre, String telefono, int idHorario, int idServicio) {
        super(idUsuario, email, password, idRol, nombre, telefono);
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public Estilista(int idUsuario, String nombre, String telefono, int idHorario, int idServicio) {
        super(idUsuario, nombre, telefono);
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public Estilista(int idUsuario, String email, String password, int idRol, int idUsuario1, String nombre, String telefono, int idHorario, int idServicio) {
        super(idUsuario, email, password, idRol, idUsuario1, nombre, telefono);
        this.idHorario = idHorario;
        this.idServicio = idServicio;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
