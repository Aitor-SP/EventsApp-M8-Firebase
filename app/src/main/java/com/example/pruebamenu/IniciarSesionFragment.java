package com.example.pruebamenu;

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
import android.widget.TextView;

public class IniciarSesionFragment extends Fragment {

    NavController navController;
    Button btniniciarsesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final TextView registro = view.findViewById(R.id.crearcuenta);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a registro al clicar en el texto crear cuenta
                navController.navigate(R.id.action_iniciarSesionFragment_to_registroFragment2);
            }
        });

        btniniciarsesion = view.findViewById(R.id.buttonic);
        btniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a home/novedades al clickar en iniciar sesion
                navController.navigate(R.id.action_iniciarSesionFragment_to_firstFragment22);
            }
        });
    }
}