package com.example.eventsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventsapp.databinding.FragmentInicioBinding;
import com.example.eventsapp.databinding.FragmentInsertarEventoBinding;

public class InsertarEventoFragment extends Fragment {

    FragmentInsertarEventoBinding binding;
    private NavController navController;
    private EventosViewModel eventosViewModel;
    private Uri imagenSeleccionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentInsertarEventoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Para volver atras al cliclar en la flecha
        binding.flechaAtras.setOnClickListener(v -> navController.popBackStack());

        eventosViewModel = new ViewModelProvider(requireActivity()).get(EventosViewModel.class);

        navController = Navigation.findNavController(view);

        binding.btnAddEvento.setOnClickListener(v -> {
            if (imagenSeleccionada == null) {
                Toast.makeText(requireContext(), "Seleccione una imagen para el evento", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.addNombreEvento.getText().toString().isEmpty()) {
                binding.addNombreEvento.setError("Introduzca el nombre del evento");
                return;
            } else if (binding.addFechaEvento.getText().toString().isEmpty()) {
                binding.addFechaEvento.setError("Introduzca la fecha del evento");
                return;
            } else if (binding.addDescripcionEvento.getText().toString().isEmpty()) {
                binding.addDescripcionEvento.setError("Introduzca una descripción del evento");
                return;
            }

            String evento = binding.addNombreEvento.getText().toString();
            String fecha = binding.addFechaEvento.getText().toString();
            String descripcion = binding.addDescripcionEvento.getText().toString();

            eventosViewModel.insertarEvento(evento,fecha,descripcion, imagenSeleccionada);

            eventosViewModel.establecerImagenSeleccionada(null);
            navController.popBackStack();

        });

        binding.addImagenEvento.setOnClickListener(v -> lanzadorGaleria.launch(new String[]{"image/*"}));

        eventosViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                imagenSeleccionada = uri;
                Glide.with(requireView()).load(uri).into(binding.addImagenEvento);
            }
        });
    }

    private final ActivityResultLauncher<String[]> lanzadorGaleria = registerForActivityResult(new ActivityResultContracts.OpenDocument(), uri -> {
        requireContext().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        eventosViewModel.establecerImagenSeleccionada(uri);
    });
}