package com.example.eventsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegistroFragment extends Fragment {

    Button btncrear,btnback;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        btncrear = view.findViewById(R.id.buttoncc);

        btncrear.setOnClickListener(v -> navController.navigate(R.id.action_registroFragment_to_firstFragment));

        btnback = view.findViewById(R.id.backlogin);
        btnback.setOnClickListener(v -> {
            // Navegar al login de nuevo al pulsar en el boton Login(back)
            navController.navigate(R.id.action_registroFragment_to_iniciarSesionFragment);
        });
    }
}