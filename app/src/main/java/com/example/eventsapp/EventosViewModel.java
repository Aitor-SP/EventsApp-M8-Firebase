package com.example.eventsapp;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class EventosViewModel extends AndroidViewModel {

    private final EventoRepository eventoRepository;

    MutableLiveData<Evento> eventoSeleccionado = new MutableLiveData<>();
    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    public EventosViewModel(@NonNull Application application) {
        super(application);

        eventoRepository = new EventoRepository(application);
    }

    public LiveData<List<Evento>> obtenerEventos(){
        return eventoRepository.obtenerEventos();
    }

    void seleccionar(Evento evento){
        eventoSeleccionado.setValue(evento);
    }

    MutableLiveData<Evento> seleccionado(){
        return eventoSeleccionado;
    }

    public void insertarEvento(String evento, String fecha, String descripcion, Uri imagenSeleccionada) {
        eventoRepository.insertarEvento(evento, fecha, descripcion, imagenSeleccionada);
    }

    void establecerImagenSeleccionada(Uri uri){
        imagenSeleccionada.setValue(uri);
    }

    void eliminar(Evento evento){
        eventoRepository.eliminar(evento);
    }
}
