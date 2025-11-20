package org.pi.Models;

public class Estilista extends Empleado {
    private int idHorario;
    private int idServicio;

    public Estilista() {
    }

    public Estilista(int idUsuario, String email, String password, int idRol, String nombre, String telefono, int idEmpleado, String imagenPerfil, int idHorario, int idServicio) {
        super(idUsuario, email, password, idRol, nombre, telefono, idEmpleado, imagenPerfil);
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
