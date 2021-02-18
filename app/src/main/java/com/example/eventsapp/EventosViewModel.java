package com.example.eventsapp;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.List;

public class EventosViewModel extends AndroidViewModel {

    private final EventoRepository eventoRepository;

    MutableLiveData<Evento> eventoSeleccionado = new MutableLiveData<>();
    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();
    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

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

    // Cada vez que cambia la variable terminoBusqueda se ejecuta el switchMap y lo que retorna se guarda en resultadoBusqueda
    LiveData<List<Evento>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Evento>>>() {
        @Override
        public LiveData<List<Evento>> apply(String input) {
            return eventoRepository.buscar(input);
        }
    });

    LiveData<List<Evento>> buscar(){
        return resultadoBusqueda;
    }

    // Este metodo cambia el valor de la variable terminoBusqueda
    void establecerTerminoBusqueda(String t){
        terminoBusqueda.setValue(t);
    }
}
