package com.example.solarsystem.ui.planets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.solarsystem.SolarObject;
import com.example.solarsystem.databinding.FragmentPlanetsBinding;

public class PlanetsFragment extends Fragment {
    private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
    private FragmentPlanetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlanetsViewModel planetsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PlanetsViewModel.class);

        binding = FragmentPlanetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        planetsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        planetsViewModel.setText("test");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SolarObject[] objects = SolarObject.loadArrayFromJSON(getContext(),"planets");

        for (SolarObject object : objects) {
            Log.d(this.LOG_KEY, object.getVideo());
        }
    }
}