package org.pi.Models;

public class Pregunta {
    private int idPregunta;
    private String pregunta;
    private String respuesta;
    private int idFormulario;
    private Formulario formulario;

    public Pregunta(int idPregunta, String pregunta, String respuesta, int idFormulario) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.idFormulario = idFormulario;
    }

    public Pregunta() {
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
}
