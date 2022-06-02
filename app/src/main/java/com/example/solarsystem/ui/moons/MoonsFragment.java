package com.example.solarsystem.ui.moons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.solarsystem.MoonsPagerAdapter;
import com.example.solarsystem.SolarObject;
import com.example.solarsystem.databinding.FragmentMoonsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MoonsFragment extends Fragment {
    private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
    public static final String OBJECT_TYPE = "planets";
    private FragmentMoonsBinding binding;
    private ViewPager2 moonsViewPager;
    private TabLayout moonsTabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MoonsViewModel moonsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoonsViewModel.class);

        binding = FragmentMoonsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.moonsViewPager = binding.moonsViewPager2;
        this.moonsTabLayout = binding.moonsTabLayout;

        // final TextView textView = binding.textMoons;
        // moonsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
        SolarObject[] solarObjects = this.objectsWithMoons();
        MoonsPagerAdapter moonsPagerAdapter = new MoonsPagerAdapter(getActivity(), solarObjects);
        this.moonsViewPager.setAdapter(moonsPagerAdapter);
        //this.moonsTabLayout.setupWithViewPager(this.moonsViewPager);
        new TabLayoutMediator(this.moonsTabLayout, this.moonsViewPager, (tab, position) -> {
            tab.setText(solarObjects[position].getName());
        }).attach();
    }

    private SolarObject[] objectsWithMoons() {
        SolarObject[] solarObjects = SolarObject.loadArrayFromJSON(getContext(), this.OBJECT_TYPE);
        List<SolarObject> objectsWithMoons = new ArrayList<>();
        for (SolarObject object : solarObjects) {
            if (object.getMoons() != null && object.getMoons().length != 0) {
                objectsWithMoons.add(object);
            }
        }
        return objectsWithMoons.toArray(new SolarObject[0]);
    }
}