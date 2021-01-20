package com.example.eventsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
    private EventosViewModel eventosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentInicioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        eventosViewModel = new ViewModelProvider(requireActivity()).get(EventosViewModel.class);

        EventosAdapter eventosAdapter = new EventosAdapter();

        binding.recyclerView.setAdapter(eventosAdapter);

        eventosViewModel.eventos().observe(getViewLifecycleOwner(), eventosAdapter::setEventoList);
    }

    class EventosAdapter extends RecyclerView.Adapter<EventoViewHolder> {
        List<Evento> eventoList;


        @NonNull
        @Override // Aqui se crea el ViewHolder vacio
        public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EventoViewHolder(ViewholderEventoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override // Aqui se recibe el ViewHolder vacio y lo rellena
        public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
            Evento evento = eventoList.get(position);

            holder.binding.evento.setText(evento.evento);
            holder.binding.fecha.setText(evento.fecha);
            holder.binding.descripcion.setText(evento.descripcion);

            Glide.with(InicioFragment.this).load(evento.imagenEvento).into(holder.binding.imagenEvento);

            holder.itemView.setOnClickListener(v -> {
                eventosViewModel.seleccionar(evento);
                navController.navigate(R.id.mostrarEventoFragment);
            });
        }

        @Override // El recyclerview llama para saber cuantos viewholder ha de generar en pantalla
        public int getItemCount() {
            return eventoList == null ? 0 : eventoList.size();
        }

        void setEventoList(List<Evento> eventoList){
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