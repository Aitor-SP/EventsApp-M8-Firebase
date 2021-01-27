package com.example.eventsapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.example.eventsapp.databinding.FragmentSplash2Binding;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SplashFragment extends Fragment {

    Executor executor = Executors.newSingleThreadExecutor();

    private NavController navController;
    private FragmentSplash2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSplash2Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        // esta variable deberia estar en un ViewModel
        MutableLiveData<Boolean> finishedLoading = new MutableLiveData<>();

        Glide.with(this).load(R.drawable.carga).into(binding.carga);

        finishedLoading.observe(getViewLifecycleOwner(), aBoolean -> navController.navigate(R.id.action_splashFragment_to_iniciarSesionFragment));

        // esto deberia estar en el Model y llamarlo a traves del ViewModel
        executor.execute(() -> {
            try {
                // simular la carga de recursos
                Thread.sleep(4000);
                finishedLoading.postValue(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}