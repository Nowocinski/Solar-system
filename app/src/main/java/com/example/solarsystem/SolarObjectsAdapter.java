package com.example.solarsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SolarObjectsAdapter extends RecyclerView.Adapter<SolarObjectsAdapter.SolarObjectsViewHolder>{
    private final SolarObject[] solarObjects;

    public SolarObjectsAdapter(SolarObject[] solarObjects) {
        this.solarObjects = solarObjects;
    }

    @NonNull
    @Override
    public SolarObjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_object, parent, false);
        return new SolarObjectsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.solarObjects.length;
    }

    @Override
    public void onBindViewHolder(@NonNull SolarObjectsViewHolder holder, int position) {

    }

    class SolarObjectsViewHolder extends RecyclerView.ViewHolder {

        public SolarObjectsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
