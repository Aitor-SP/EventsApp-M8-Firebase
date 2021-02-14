package com.example.eventsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerBusquedaFragment extends InicioFragment {
    @Override
    LiveData<List<Evento>> obtenerEventos() {
        return eventosViewModel.buscar();
    }
}