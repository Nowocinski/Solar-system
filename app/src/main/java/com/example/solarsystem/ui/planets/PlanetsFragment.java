package com.example.solarsystem.ui.planets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.solarsystem.SolarObject;
import com.example.solarsystem.SolarObjectsAdapter;
import com.example.solarsystem.databinding.FragmentPlanetsBinding;

public class PlanetsFragment extends Fragment implements SolarObjectsAdapter.ISolarObjectClickedListener {
    private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
    public static final String OBJECT_TYPE = "planets";
    public static final String OBJECTS_KEY = "planets";
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
        SolarObject[] objects;
        if (getArguments() != null && getArguments().containsKey(this.OBJECTS_KEY)) {
            objects = (SolarObject[]) getArguments().getSerializable(this.OBJECTS_KEY);
        } else {
            objects = SolarObject.loadArrayFromJSON(getContext(), this.OBJECT_TYPE);
        }

        this.binding.objectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        SolarObjectsAdapter adapter = new SolarObjectsAdapter(objects);
        adapter.setSolarObjectClickedListener(this);
        this.binding.objectsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        Log.d(this.LOG_KEY, "Clicked: " + solarObject.getName());
    }

    public static PlanetsFragment newInstance(SolarObject[] objects) {
        PlanetsFragment fragment = new PlanetsFragment();
        Bundle args = new Bundle();
        args.putSerializable(OBJECTS_KEY, objects);
        fragment.setArguments(args);
        return fragment;
    }
}