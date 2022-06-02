package com.example.solarsystem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        SolarObject solarObject = this.solarObjects[position];
        holder.setSolarObject(solarObject);
    }

    class SolarObjectsViewHolder extends RecyclerView.ViewHolder {
        private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
        @BindView(R.id.itemImageView)
        ImageView itemImageView;
        @BindView(R.id.itemTextView)
        TextView itemTextView;

        private SolarObject solarObject;

        public SolarObjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setSolarObject(SolarObject solarObject) {
            this.solarObject = solarObject;
            this.itemTextView.setText(this.solarObject.getName());
            Glide.with(this.itemImageView.getContext())
                    .load("file:///android_asset/" + this.solarObject.getImage())
                    .into(this.itemImageView);
        }

        public SolarObject getSolarObject() {
            return solarObject;
        }
    }
}
