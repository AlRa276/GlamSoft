package org.pi.models;
import java.time.Duration;
import java.util.List;

public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private float precioServicio;
    private Duration duracionServicio;
    private String categoria;
    private float puntuacion;
    //El servicio tiene un formulario en especifico
    private Formulario encuesta;
    //Un servicio lo pueden realizar n estilistas.
    private List<Estilista> estilistas;
    public Servicio() {
    }

    public Servicio(int idServicio, String nombreServicio, String descripcionServicio, float precioServicio, Duration duracionServicio, String categoria, float puntuacion, Formulario encuesta, List<Estilista> estilistas) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.precioServicio = precioServicio;
        this.duracionServicio = duracionServicio;
        this.categoria = categoria;
        this.puntuacion = puntuacion;
        this.encuesta = encuesta;
        this.estilistas = estilistas;
    }

    public Formulario getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Formulario encuesta) {
        this.encuesta = encuesta;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public float getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(float precioServicio) {
        this.precioServicio = precioServicio;
    }

    public Duration getDuracionServicio() {
        return duracionServicio;
    }

    public void setDuracionServicio(Duration duracionServicio) {
        this.duracionServicio = duracionServicio;
    }

    public List<Estilista> getEstilistas() {
        return estilistas;
    }

    public void setEstilistas(List<Estilista> estilistas) {
        this.estilistas = estilistas;
    }
}
