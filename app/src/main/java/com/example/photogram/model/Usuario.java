package com.example.photogram.model;

import java.util.ArrayList;

public class Usuario {
    java.lang.String rutaFotoPerfil;
    java.lang.String nombreUsuario;
    java.lang.String idUsuario;

    ArrayList<String> listaContactos = new ArrayList<>();
    ArrayList<Publicacion> listaPublicaciones = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(java.lang.String rutaFotoPerfil, java.lang.String nombreUsuario, java.lang.String idUsuario) {
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
    }

    public Usuario(java.lang.String rutaFotoPerfil, java.lang.String nombreUsuario, java.lang.String idUsuario, ArrayList<String> listaStrings) {
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.listaContactos = listaStrings;
    }

    public Usuario(java.lang.String rutaFotoPerfil, java.lang.String nombreUsuario, ArrayList<Publicacion> listaPublicaciones, java.lang.String idUsuario) {
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.listaPublicaciones = listaPublicaciones;
    }


    public Usuario(java.lang.String rutaFotoPerfil, java.lang.String nombreUsuario, java.lang.String idUsuario, ArrayList<String> listaContactos, ArrayList<Publicacion> listaPublicaciones) {
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.listaContactos = listaContactos;
        this.listaPublicaciones = listaPublicaciones;
    }

    public java.lang.String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }

    public void setRutaFotoPerfil(java.lang.String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
    }

    public java.lang.String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(java.lang.String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public java.lang.String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void agregarContecto(String string){
        listaContactos.add(string);
    }

    public ArrayList<String> getListaContactos(){
        return this.listaContactos;
    }

    public void setListaContactos(ArrayList<String> listaContactos){
        this.listaContactos = listaContactos;
    }

    public ArrayList<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(ArrayList<Publicacion> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public void addPublicacion(Publicacion publicacion){
        listaPublicaciones.add(publicacion);
    }

}
