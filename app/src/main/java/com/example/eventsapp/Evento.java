package com.example.eventsapp;

public class Evento {

    String evento;
    String fecha;
    String descripcion;
    int imagenEvento;

    public Evento(String evento, String fecha, String descripcion, int imagenEvento) {
        this.evento = evento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagenEvento = imagenEvento;
    }
}
