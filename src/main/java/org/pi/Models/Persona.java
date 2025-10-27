package org.pi.Models;


public class Persona {
    private int idPersona;
    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private int idRol;
    private Rol rol;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String telefono, String email, String password, int idRol, Rol rol) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
        this.rol = rol;
    }
    public Persona(int idPersona, String nombre, String telefono, String email, String password, int idRol) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }
    public Persona(int idPersona, String nombre, String telefono, String email, String password, Rol rol) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}