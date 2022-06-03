package com.example.solarsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;

import com.bumptech.glide.Glide;
import com.example.solarsystem.databinding.ActivitySolarObjectBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import butterknife.ButterKnife;

public class SolarObjectActivity extends AppCompatActivity {

    public static final String OBJECT_KEY = "object";
    private ActivitySolarObjectBinding binding;
    private SolarObject solarObject;
    private TextView objectTextView;
    private ImageView objectImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        binding = ActivitySolarObjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.objectTextView = binding.objectContentScrolling.objectTextView;
        this.objectImageView = binding.objectImageView;

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        this.solarObject = (SolarObject) getIntent().getSerializableExtra(this.OBJECT_KEY);
        binding.toolbarLayout.setTitle(this.solarObject.getName());
        try {
            String text = SolarObject.loadStringFromAssets(this, this.solarObject.getText());
            this.objectTextView.setText(Html.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Glide.with(this)
                .load(this.solarObject.getImagePath())
                .into(this.objectImageView);
    }

    public static void start(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT_KEY, solarObject);
        activity.startActivity(intent);
    }
}