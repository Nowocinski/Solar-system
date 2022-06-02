package com.example.solarsystem.ui.moons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.solarsystem.databinding.FragmentMoonsBinding;

public class MoonsFragment extends Fragment {

    private FragmentMoonsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MoonsViewModel moonsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoonsViewModel.class);

        binding = FragmentMoonsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMoons;
        moonsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}