package com.example.eventsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eventsapp.databinding.FragmentConciertosBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.net.MalformedURLException;
import java.net.URL;

public class ConciertosFragment extends Fragment {

    FragmentConciertosBinding binding;
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
        return (binding = FragmentConciertosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cerrar1.setOnClickListener(v -> {
            binding.cardview1.removeAllViews();
            Snackbar.make(view, "Has eliminado este evento de Favoritos.", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show();
        });

        binding.cerrar2.setOnClickListener(v -> {
            binding.cardview2.removeAllViews();
            Snackbar.make(view, "Has eliminado este evento de Favoritos.", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .show();
        });

        binding.cerrar3.setOnClickListener(v -> {
            binding.cardview3.removeAllViews();
            Snackbar.make(view, "Has eliminado este evento de Favoritos.", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .show();
        });

        binding.btnShare.setOnClickListener(v ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "EVENTSAPP");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        binding.share2.setOnClickListener(v ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "EVENTSAPP");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        binding.share3.setOnClickListener(v ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "EVENTSAPP");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        binding.carrito1.setOnClickListener(v -> {
            Intent compraIntent = new Intent(Intent.ACTION_VIEW);
            compraIntent.setData(Uri.parse(String.valueOf(url)));
            startActivity(compraIntent);
        });

        binding.carrito2.setOnClickListener(v -> {
            Intent compraIntent = new Intent(Intent.ACTION_VIEW);
            compraIntent.setData(Uri.parse(String.valueOf(url)));
            startActivity(compraIntent);
        });

        binding.carrito3.setOnClickListener(v -> {
            Intent compraIntent = new Intent(Intent.ACTION_VIEW);
            compraIntent.setData(Uri.parse(String.valueOf(url)));
            startActivity(compraIntent);
        });

    }
}