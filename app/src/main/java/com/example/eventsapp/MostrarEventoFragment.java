package com.example.eventsapp;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.net.MalformedURLException;
import java.net.URL;

public class MostrarEventoFragment extends Fragment {

    private FragmentMostrarEventoBinding binding;
    private NavController navController;
    private EventosViewModel eventosViewModel;
    private URL url;
    {
        try {
            url = new URL("https://www.entradas.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

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
        binding.flechaAtras.setOnClickListener(v -> navController.popBackStack());

        // Para compartir al cliclar en el share
        binding.share.setOnClickListener(v ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "EVENTSAPP");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        binding.likeMostrarevento.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Snackbar.make(view, "Has añadido este evento a Favoritos.",Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Snackbar.make(view, "Has eliminado este evento de Favoritos.", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show();
            }
        });

        binding.txtCompra.setOnClickListener(v -> {
            Intent compraIntent = new Intent(Intent.ACTION_VIEW);
            compraIntent.setData(Uri.parse(String.valueOf(url)));
            startActivity(compraIntent);
        });


        eventosViewModel.seleccionado().observe(getViewLifecycleOwner(), evento -> {
            binding.titEvento.setText(evento.evento);
            binding.etiquetaEvento.setText(evento.etiqueta);
            binding.fechaEvento.setText(evento.fecha);
            binding.ubicacionEvento.setText(evento.ubicacion);
            Glide.with(requireContext()).load(evento.imagenGrande).into(binding.imgEvento);
        });
    }
}