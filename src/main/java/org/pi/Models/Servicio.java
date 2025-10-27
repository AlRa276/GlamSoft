package org.pi.Models;

public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private int duracionMinutos;
    private double precio;
    private String descripcion;
    private int idCategoria;
    private Categoria categoria;

    public Servicio() {
    }

    public Servicio(int idServicio, String nombreServicio, int duracionMinutos, double precio, String descripcion, int idCategoria, Categoria categoria) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }
    public Servicio(int idServicio, String nombreServicio, int duracionMinutos, double precio, String descripcion, int idCategoria) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;

    }
    public Servicio(int idServicio, String nombreServicio, int duracionMinutos, double precio, String descripcion,  Categoria categoria) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.descripcion = descripcion;
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

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}