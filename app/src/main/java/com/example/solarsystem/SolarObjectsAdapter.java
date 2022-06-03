package com.example.solarsystem;

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
    private ISolarObjectClickedListener solarObjectClickedListener;

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

    private void itemClick(SolarObject solarObject) {
        if (this.solarObjectClickedListener != null) {
            this.solarObjectClickedListener.solarObjectClicked(solarObject);
        }
    }

    public void setSolarObjectClickedListener(ISolarObjectClickedListener solarObjectClickedListener) {
        this.solarObjectClickedListener = solarObjectClickedListener;
    }

    class SolarObjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String LOG_KEY = "LOG_KEY@" + this.getClass().getSimpleName();
        @BindView(R.id.itemImageView)
        ImageView itemImageView;
        @BindView(R.id.itemTextView)
        TextView itemTextView;

        private SolarObject solarObject;

        public SolarObjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setSolarObject(SolarObject solarObject) {
            this.solarObject = solarObject;
            this.itemTextView.setText(this.solarObject.getName());
            Glide.with(this.itemImageView.getContext())
                    .load(this.solarObject.getImagePath())
                    .into(this.itemImageView);
        }

        public SolarObject getSolarObject() {
            return solarObject;
        }

        @Override
        public void onClick(View v) {
            itemClick(solarObject);
        }
    }

    public interface ISolarObjectClickedListener {
        void solarObjectClicked(SolarObject solarObject);
    }
}
