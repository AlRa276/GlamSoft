package org.pi.Models;

public class Portafolio {
    private int idImagen;
    private String imageURL;
    private String nombreImagen;

    public Portafolio(int idImagen, String imageURL, String nombreImagen) {
        this.idImagen = idImagen;
        this.imageURL = imageURL;
        this.nombreImagen = nombreImagen;
    }

    public Portafolio() {
    }

    public Portafolio(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
