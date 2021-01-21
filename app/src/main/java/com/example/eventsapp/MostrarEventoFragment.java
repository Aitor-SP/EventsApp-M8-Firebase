package com.example.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventsapp.databinding.FragmentMostrarEventoBinding;

public class MostrarEventoFragment extends Fragment {

    private FragmentMostrarEventoBinding binding;
    private NavController navController;
    private EventosViewModel eventosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentMostrarEventoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventosViewModel = new ViewModelProvider(requireActivity()).get(EventosViewModel.class);
        navController = Navigation.findNavController(view);

        // Para volver atras al cliclar en la flecha
        binding.flechaAtras.setOnClickListener(v ->{
            navController.popBackStack();
        });

        // Para compartir al cliclar en el share
        binding.share.setOnClickListener(v ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Vas a compartir este evento.");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });


        eventosViewModel.seleccionado().observe(getViewLifecycleOwner(), evento -> {
            binding.titEvento.setText(evento.evento);
            Glide.with(requireContext()).load(evento.imagenEvento).into(binding.imgEvento);
        });
    }
}