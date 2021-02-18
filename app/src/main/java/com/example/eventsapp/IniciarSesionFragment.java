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
import android.widget.Toast;

import com.example.eventsapp.databinding.FragmentIniciarSesionBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.royrodriguez.transitionbutton.TransitionButton;

public class IniciarSesionFragment extends Fragment {

    private FragmentIniciarSesionBinding binding;
    private NavController navController;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentIniciarSesionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();


        binding.crearcuenta.setOnClickListener(v -> {
            // Navegar a registro al clicar en el texto crear cuenta
            navController.navigate(R.id.action_iniciarSesionFragment_to_registroFragment);
        });

        binding.transitionButton.setOnClickListener(v -> {
            // Start the loading animation when the user tap the button
            binding.transitionButton.startAnimation();
            // Do your networking task or background work here.
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                boolean isSuccessful = true;
                // Choose a stop animation if your call was succesful or not
                if (isSuccessful) {
                    binding.transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, () -> {
                        String email = binding.emailLogin.getText().toString();
                        String password = binding.passwordLogin.getText().toString();

                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        navController.navigate(R.id.action_iniciarSesionFragment_to_firstFragment);
                                    } else {
                                        Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        binding.transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                                    }
                                });
                    });
                } else {
                    binding.transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                }
            }, 2000);
        });
    }
}