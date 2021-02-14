package com.example.eventsapp;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.security.auth.callback.Callback;

public class EventoRepository {

    Executor executor = Executors.newSingleThreadExecutor();
    BaseDeDatos.EventosDao dao;

    public EventoRepository(Application application) {
        dao = BaseDeDatos.getInstance(application).obtenerEventosDao();
    }

    LiveData<List<Evento>> obtenerEventos() {
        return dao.obtenerEventos();
    }

    public void insertarEvento(String evento, String fecha, String descripcion, Uri imagenSeleccionada) {
        executor.execute(() -> dao.insertarEvento(new Evento(evento, fecha, descripcion, imagenSeleccionada.toString())));
    }

    public void eliminar(Evento evento) {
        executor.execute(() -> dao.eliminar(evento));
    }

    LiveData<List<Evento>> buscar(String t) {
        return dao.buscar(t);
    }
}