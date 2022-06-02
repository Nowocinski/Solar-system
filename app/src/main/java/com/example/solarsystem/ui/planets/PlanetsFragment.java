package com.example.solarsystem.ui.planets;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solarsystem.SolarObject;
import com.example.solarsystem.SolarObjectsAdapter;
import com.example.solarsystem.databinding.FragmentPlanetsBinding;

import butterknife.BindView;

public class PlanetsFragment extends Fragment {
    private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
    public static final String OBJECT_KEY = "planets";
    private FragmentPlanetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlanetsViewModel planetsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PlanetsViewModel.class);

        binding = FragmentPlanetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //planetsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //planetsViewModel.setText("test");
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
        SolarObject[] objects = SolarObject.loadArrayFromJSON(getContext(), this.OBJECT_KEY);
        this.binding.objectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.binding.objectsRecyclerView.setAdapter(new SolarObjectsAdapter(objects));
    }
}