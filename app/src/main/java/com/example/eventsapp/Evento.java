package com.example.eventsapp;

public class Evento {

    String evento;
    String fecha;
    String descripcion;
    int imagenEvento;
    int imagenGrande;

    public Evento(String evento, String fecha, String descripcion, int imagenEvento, int imagenGrande) {
        this.evento = evento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagenEvento = imagenEvento;
        this.imagenGrande = imagenGrande;
    }
}
