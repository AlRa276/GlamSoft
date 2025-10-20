package org.pi.models;

public class Administrador extends Persona{
    private String usuario;
    public Administrador() {
    }

    public Administrador(int idPersona, String nombre, String apellido, String email, String telefono, String rol, String contrasenia, String usuario) {
        super(idPersona, nombre, apellido, email, telefono, rol, contrasenia);
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
