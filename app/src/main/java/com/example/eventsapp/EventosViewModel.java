package com.example.eventsapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventosViewModel extends AndroidViewModel {
    EventoRepository eventoRepository;

    public EventosViewModel(@NonNull Application application) {
        super(application);

        eventoRepository = new EventoRepository();
    }

    LiveData<List<Evento>> eventos(){
        return eventoRepository.eventos();
    }
}
