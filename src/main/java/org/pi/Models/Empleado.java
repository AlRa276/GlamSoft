package org.pi.Models;

public class Empleado extends Usuario {
    private int idUsuario;//id_usuario es pk
    private String nombre;
    private String telefono;
    private int idEmpleado;
    public Empleado() {
    }

    public Empleado( int idRol,String email, String nombre, String telefono) {
        super(idRol, email);
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado( String email, String nombre, String telefono) {
        super(email);
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(int idUsuario, String email, String password, int idRol, String nombre, String telefono) {
        super(idUsuario, email, password, idRol);
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(int idUsuario, String nombre, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(int idUsuario, String email, String password, int idRol, int idUsuario1, String nombre, String telefono) {
        super(idUsuario, email, password, idRol);
        this.idUsuario = idUsuario1;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
}
