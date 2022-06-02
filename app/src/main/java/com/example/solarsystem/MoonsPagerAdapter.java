package com.example.solarsystem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.solarsystem.ui.moons.MoonsFragment;
import com.example.solarsystem.ui.planets.PlanetsFragment;

public class MoonsPagerAdapter extends FragmentStateAdapter {
    private final SolarObject[] objectsWithMoons;

    public MoonsPagerAdapter(@NonNull FragmentActivity fragmentActivity, SolarObject[] objectsWithMoons) {
        super(fragmentActivity);
        this.objectsWithMoons = objectsWithMoons;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return PlanetsFragment.newInstance(this.objectsWithMoons[position].getMoons());
    }

    @Override
    public int getItemCount() {
        return this.objectsWithMoons.length;
    }
}
