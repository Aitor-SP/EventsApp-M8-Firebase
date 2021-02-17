package com.example.eventsapp;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerBusquedaFragment extends InicioFragment {
    @Override
    LiveData<List<Evento>> resultadoBusqueda() {
        return eventosViewModel.buscar();
    }
}