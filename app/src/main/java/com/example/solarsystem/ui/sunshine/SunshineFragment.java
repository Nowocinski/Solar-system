package com.example.solarsystem.ui.sunshine;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solarsystem.databinding.FragmentSunshineBinding;

public class SunshineFragment extends Fragment {

    private FragmentSunshineBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SunshineViewModel sunshineViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SunshineViewModel.class);

        binding = FragmentSunshineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.testSunshine;
        sunshineViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}