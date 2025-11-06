package org.pi.Models;

public class Empleado extends Usuario {
    private int idEmpleado;
    private String nombre;
    private String telefono;
    private int idUsuario;
    private Usuario usuario;

    public Empleado(int idEmpleado, String nombre, String telefono) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(int idEmpleado, String nombre, String telefono, int idUsuario, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Empleado(int idUsuario, String email, String password, int idRol, int idEmpleado, String nombre, String telefono, int idUsuario1, Usuario usuario) {
        super(idUsuario, email, password, idRol);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.idUsuario = idUsuario1;
        this.usuario = usuario;
    }

    public Empleado(String email, int idEmpleado, String nombre, String telefono, int idUsuario, Usuario usuario) {
        super(email);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Empleado(String email, String password, int idEmpleado, String nombre, String telefono, int idUsuario, Usuario usuario) {
        super(email, password);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Empleado(int idUsuario, String email, String nombre, String telefono, int idEmpleado) {
        super(idUsuario, email);
        this.nombre = nombre;
        this.telefono = telefono;
        this.idEmpleado = idEmpleado;
    }
    public Empleado(String email, String nombre, String telefono) {
        super(email);
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Empleado(String email, int idEmpleado, String nombre, String telefono) {
        super(email);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(String email, String password, int idEmpleado, String nombre, String telefono) {
        super(email, password);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado() {
    }

    public Empleado(int idRol, String email, int id, String nombre,String telefono){
        super(idRol, email);
        this.idEmpleado = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Empleado(String email) {
        super(email);
    }

    public Empleado(String email, String password) {
        super(email, password);
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
