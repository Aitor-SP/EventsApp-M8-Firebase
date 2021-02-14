package com.example.eventsapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.example.eventsapp.databinding.FragmentAgendaBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.HashSet;

public class AgendaFragment extends Fragment {

    private FragmentAgendaBinding binding;
    private Dialog mDialog;
    private FloatingActionButton floatingActionButton;
    private Button bclose;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentAgendaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cosmoCalendar.setSelectionType(SelectionType.MULTIPLE);

        binding.cosmoCalendar.setSelectedDayBackgroundColor(Color.parseColor("#EB5757"));

        binding.cosmoCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        binding.cosmoCalendar.setCalendarOrientation(0);

        binding.cosmoCalendar.setWeekendDayTextColor(Color.parseColor("#EB5757"));

        binding.cosmoCalendar.setCurrentDayIconRes(R.drawable.ic_triangle_red);

        binding.cosmoCalendar.setWeekendDays(new HashSet(){{
            add(Calendar.SATURDAY);
            add(Calendar.SUNDAY);
        }});

        floatingActionButton = view.findViewById(R.id.navegarAInsertar);
        mDialog = new Dialog(getActivity());

        floatingActionButton.setOnClickListener(v -> {
            mDialog.setContentView(R.layout.popup_anadir);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.show();
            bclose = mDialog.findViewById(R.id.btn_add_evento);
            bclose.setOnClickListener(v1 -> mDialog.dismiss());
        });
    }
}