package com.example.eventsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.eventsapp.databinding.FragmentIniciarSesionBinding;
import com.royrodriguez.transitionbutton.TransitionButton;

public class IniciarSesionFragment extends Fragment {

    private FragmentIniciarSesionBinding binding;
    private TransitionButton transitionButton;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentIniciarSesionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final TextView registro = view.findViewById(R.id.crearcuenta);
        registro.setOnClickListener(v -> {
            // Navegar a registro al clicar en el texto crear cuenta
            navController.navigate(R.id.action_iniciarSesionFragment_to_registroFragment);
        });

        transitionButton = view.findViewById(R.id.transition_button);
        transitionButton.setOnClickListener(v -> {
            // Start the loading animation when the user tap the button
            transitionButton.startAnimation();

            // Do your networking task or background work here.
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                boolean isSuccessful = true;

                // Choose a stop animation if your call was succesful or not
                if (isSuccessful) {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, () -> navController.navigate(R.id.action_iniciarSesionFragment_to_firstFragment));
                } else {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                }
            }, 2000);
        });
    }
}