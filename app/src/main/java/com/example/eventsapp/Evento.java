package com.example.eventsapp;

public class Evento {

    String evento;
    String fecha;
    String descripcion;
    String etiqueta;
    String ubicacion;
    int imagenEvento;
    int imagenGrande;

    public Evento(String evento, String fecha, String descripcion, String etiqueta, String ubicacion, int imagenEvento, int imagenGrande) {
        this.evento = evento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.etiqueta = etiqueta;
        this.ubicacion = ubicacion;
        this.imagenEvento = imagenEvento;
        this.imagenGrande = imagenGrande;
    }
}
