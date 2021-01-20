package com.example.eventsapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class EventosViewModel extends AndroidViewModel {

    EventoRepository eventoRepository;

    MutableLiveData<Evento> eventoSeleccionado = new MutableLiveData<>();

    public EventosViewModel(@NonNull Application application) {
        super(application);

        eventoRepository = new EventoRepository();
    }

    LiveData<List<Evento>> eventos(){
        return eventoRepository.eventos();
    }

    void seleccionar(Evento evento){
        eventoSeleccionado.setValue(evento);
    }

    MutableLiveData<Evento> seleccionado(){
        return eventoSeleccionado;
    }
}
