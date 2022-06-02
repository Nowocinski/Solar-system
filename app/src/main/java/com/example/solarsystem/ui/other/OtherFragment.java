package com.example.solarsystem.ui.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.solarsystem.databinding.FragmentPlanetsBinding;

public class OtherFragment extends Fragment {

    private FragmentPlanetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OtherViewModel otherViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(OtherViewModel.class);

        binding = FragmentPlanetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //otherViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}