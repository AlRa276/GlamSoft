package org.pi.models;

public class Formulario {
    private String[] preguntas;
    private String[] respuestas;

    public Formulario() {
    }


    public Formulario(String[] preguntas, String[] respuestas) {
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }

    public String[] getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String[] preguntas) {
        this.preguntas = preguntas;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }


}
