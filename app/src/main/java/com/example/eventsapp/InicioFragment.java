package com.example.eventsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.eventsapp.databinding.FragmentInicioBinding;
import com.example.eventsapp.databinding.ViewholderEventoBinding;
import java.util.List;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private NavController navController;
    EventosViewModel eventosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentInicioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventosViewModel = new ViewModelProvider(requireActivity()).get(EventosViewModel.class);
        navController = Navigation.findNavController(view);

        binding.insertarEvento.setOnClickListener(v -> navController.navigate(R.id.action_inicioFragment_to_insertarEventoFragment));

        EventosAdapter eventosAdapter = new EventosAdapter();
        binding.recyclerView.setAdapter(eventosAdapter);

        eventosViewModel.obtenerEventos().observe(getViewLifecycleOwner(), eventos -> eventosAdapter.setEventoList(eventos));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Evento evento = eventosAdapter.obtenerEvento(posicion);
                eventosViewModel.eliminar(evento);

            }
        }).attachToRecyclerView(binding.recyclerView);

        // Cuando se busque algo se establece el TerminoBusqueda
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Cuando aun no se ha buscado se establece un TerminoBusqueda vacio
                eventosViewModel.establecerTerminoBusqueda("");
                return false;
            }
        });
        // Se observa la variable resultadoBusqueda para mostrarla en el RecyclerView
        eventosViewModel.resultadoBusqueda.observe(getViewLifecycleOwner(), eventos -> {
            eventosAdapter.setEventoList(eventos);
        });
    }

    // NO CONSIGO QUE FUNCIONEEEFOEWNJOFEWJFOEWJIEOFIJEWOIJ
    LiveData<List<Evento>> resultadoBusqueda() {
        return resultadoBusqueda();
    }

    class EventosAdapter extends RecyclerView.Adapter<EventoViewHolder> {

        public Evento obtenerEvento(int posicion){
            return eventoList.get(posicion);
        }

        List<Evento> eventoList;

        @NonNull
        @Override
        public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EventoViewHolder(ViewholderEventoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
            Evento evento = eventoList.get(position);

            holder.binding.evento.setText(evento.evento);
            holder.binding.fecha.setText(evento.fecha);
            holder.binding.descripcion.setText(evento.descripcion);

            Glide.with(holder.itemView)
                    .load(evento.imagenEvento)
                    .into(holder.binding.imagenEvento);

            holder.itemView.setOnClickListener(v -> {
                eventosViewModel.seleccionar(evento);
                navController.navigate(R.id.mostrarEventoFragment);
            });
        }

        @Override
        public int getItemCount() {
            return eventoList == null ? 0 : eventoList.size();
        }

        public void setEventoList(List<Evento> eventoList) {
            this.eventoList = eventoList;
            notifyDataSetChanged();
        }
    }

    static class EventoViewHolder extends RecyclerView.ViewHolder {
        ViewholderEventoBinding binding;

        public EventoViewHolder(@NonNull ViewholderEventoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}