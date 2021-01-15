package com.example.eventsapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.navigation.ui.NavigationUI;
import com.example.eventsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentcontainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getId() == R.id.splashFragment || destination.getId() == R.id.iniciarSesionFragment || destination.getId() == R.id.registroFragment) {
                binding.bottomNavigation.setVisibility(View.GONE);
            } else {
                binding.bottomNavigation.setVisibility(View.VISIBLE);
            }
        });
    }

    // Para no volver atras
    @Override
    public void onBackPressed(){
    }

    //Para ocultar el teclado
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}