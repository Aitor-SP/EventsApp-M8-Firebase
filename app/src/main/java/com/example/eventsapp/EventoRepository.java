package com.example.eventsapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class EventoRepository {

    LiveData<List<Evento>> eventosLiveData;

    EventoRepository(){
        List<Evento> eventos = Arrays.asList(
                new Evento("Manolo García","10 de enero","Vive de nuevo todos los temas del artista catalán en modo acústico en su visita a Barcelona.",R.drawable.manolo),
                new Evento("Cirque du Soleil","27 de febrero","¡Ingresa al imperio de KÀ! Una aventura épica de amor y conflicto llega a Barcelona.",R.drawable.circo),
                new Evento("El Mago Pop","12 de marzo","Vive el nuevo espectáculo del extraordinario Mago Pop en un recinto único en Barcelona.",R.drawable.magopop),
                new Evento("Goyo Jiménez","15 de abril","Aiguantulivinamérica 2 - Vuelve el único e inimitable experto en asuntos americanos, Goyo Jiménez.",R.drawable.goyo),
                new Evento("Susans Red Nipples","21 de mayo","Conoce a este pintoresco grupo rock-punk en un concierto único.",R.drawable.susans),
                new Evento("J Balvin","9 de junio","J Balvin aterriza en Barcelona presentando Colores, su nuevo álbum.",R.drawable.jbalvin),
                new Evento("Unite with Tomorrowland","29 de julio","Vive la experiencia Tomorrowland al ritmo de tus djs preferidos y tops mundiales.",R.drawable.tomorrowland),
                new Evento("El Rey León","5 de agosto","Descubre todo sobre el musical que ya han disfrutado cerca de 5.000.000 de espectadores en nuestro país.",R.drawable.reyleon),
                new Evento("Love the 90's","3 de septiembre","El mayor festival de los 90 con tres escenarios con lo mejor del dance, del pop/rock y los hits del verano.",R.drawable.love90),
                new Evento("El Tricicle","21 de octubre","Lo mejor de lo mejor de Tricicle, o casi, durante tiempo limitado.",R.drawable.tricicle),
                new Evento("Martita de Graná","29 de noviembre","La humorista revoluciona el mundo de la comedia con su desparpajo y naturalidad.",R.drawable.martita),
                new Evento("Fito & Fitipaldis","10 de diciembre","Presentando el último disco llegan a Barcelona para hacer cantar y tararear sus temas a todo el mundo.",R.drawable.fito)
        );

        eventosLiveData = new MutableLiveData<>(eventos);
    }

    LiveData<List<Evento>> eventos(){
        return eventosLiveData;
    }
}
