package org.pi.Models;

public class Empleado extends Usuario {
    private String nombre;
    private String telefono;
    private int idEmpleado;
    private String imagenPerfil;

    public Empleado() {
    }


    public Empleado(int idUsuario, String email, String password, int idRol, String nombre, String telefono, int idEmpleado, String imagenPerfil) {
        super(idUsuario, email, password, idRol);
        this.nombre = nombre;
        this.telefono = telefono;
        this.idEmpleado = idEmpleado;
        
        this.imagenPerfil = imagenPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }


}
