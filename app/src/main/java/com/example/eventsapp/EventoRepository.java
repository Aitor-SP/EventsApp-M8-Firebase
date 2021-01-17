package com.example.eventsapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class EventoRepository {

    LiveData<List<Evento>> eventosLiveData;

    EventoRepository(){
        List<Evento> eventos = Arrays.asList(
                new Evento("Manolo García - En concierto","7 de noviembre",R.drawable.manolo),
                new Evento("Goyo Jimenez","20 de mayo",R.drawable.goyo),
                new Evento("Cirque du Soleil - KÀ","10 de enero",R.drawable.circo),
                new Evento("El Mago Pop - Nada es imposible","20 de septiembre",R.drawable.magopop),
                new Evento("El Tricicle - HITS","20 de diciembre",R.drawable.tricicle),
                new Evento("J Balvin - Colores","22 de junio",R.drawable.jbalvin),
                new Evento("Fito & Fitipaldis - En concierto","15 de mayo",R.drawable.fito),
                new Evento("Unite with Tomorrowland","29 de julio",R.drawable.tomorrowland)
        );

        eventosLiveData = new MutableLiveData<>(eventos);
    }

    LiveData<List<Evento>> eventos(){
        return eventosLiveData;
    }
}
