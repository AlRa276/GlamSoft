package org.pi.Models;

public class Formulario {
    private int idFormulario;
    private String nombreFormulario;

    public Formulario() {
    }

    public Formulario(int idFormulario, String nombreFormulario) {
        this.idFormulario = idFormulario;
        this.nombreFormulario = nombreFormulario;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getNombreFormulario() {
        return nombreFormulario;
    }

    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }
}
