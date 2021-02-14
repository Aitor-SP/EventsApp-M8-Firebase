package com.example.eventsapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Es la tabla de la base de datos y los parametros que va a tener
@Entity
public class Evento {
    @PrimaryKey(autoGenerate = true)
    public int id;

    String evento;
    String fecha;
    String descripcion;
    String imagenEvento;

    public Evento(String evento, String fecha, String descripcion, String imagenEvento) {
        this.evento = evento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagenEvento = imagenEvento;
    }
}
