package com.example.eventsapp;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.example.eventsapp.databinding.FragmentThirdBinding;
import java.util.Calendar;
import java.util.HashSet;

public class AgendaFragment extends Fragment {

    private FragmentThirdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentThirdBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cosmoCalendar.setSelectionType(SelectionType.MULTIPLE);

        binding.cosmoCalendar.setSelectedDayBackgroundColor(Color.parseColor("#EB5757"));

        binding.cosmoCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        binding.cosmoCalendar.setCalendarOrientation(0);

        binding.cosmoCalendar.setWeekendDayTextColor(Color.parseColor("#EB5757"));

        binding.cosmoCalendar.setCurrentDayIconRes(R.drawable.ic_triangle_gris);

        binding.cosmoCalendar.setWeekendDays(new HashSet(){{
            add(Calendar.SATURDAY);
            add(Calendar.SUNDAY);
        }});
    }
}