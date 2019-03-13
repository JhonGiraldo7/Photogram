package com.example.photogram.model;

public class Publicacion {
    String urlImage;
    String nombrePersona;
    int contLikes = 0;

    public Publicacion(){
    }

    public Publicacion(String nombrePersona, String urlImage, int contLikes){
        this.nombrePersona = nombrePersona;
        this.urlImage = urlImage;
        this.contLikes = contLikes;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public int getContLikes() {
        return contLikes;
    }

    public void setContLikes(int contLikes) {
        this.contLikes = contLikes;
    }

}
