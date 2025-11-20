package org.pi.Models;

public class Usuario {
    private int idUsuario;
    private String email;
    private String password;
    private int idRol;

    public Usuario() {
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public Usuario(int idRol, String email) {
        this.idRol = idRol;
        this.email = email;
    }

    public Usuario(int idUsuario, String email, String password, int idRol) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
}

