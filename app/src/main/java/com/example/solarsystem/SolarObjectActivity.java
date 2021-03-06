package com.example.solarsystem;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.solarsystem.databinding.ActivitySolarObjectBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import butterknife.ButterKnife;

public class SolarObjectActivity extends AppCompatActivity implements SolarObjectsAdapter.ISolarObjectClickedListener {

    public static final String OBJECT_KEY = "object";
    private ActivitySolarObjectBinding binding;
    private SolarObject solarObject;
    private TextView objectTextView;
    private ImageView objectImageView;
    private RecyclerView moonsRecyclerView;
    private TextView moonsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        binding = ActivitySolarObjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.objectTextView = binding.objectContentScrolling.objectTextView;
        this.objectImageView = binding.objectImageView;
        this.moonsRecyclerView = this.binding.objectContentScrolling.moonsRecyclerView;
        this.moonsLabel = this.binding.objectContentScrolling.moonsLabel;

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        this.solarObject = (SolarObject) getIntent().getSerializableExtra(this.OBJECT_KEY);

        FloatingActionButton fab = binding.fab;
        boolean hasMovie = !TextUtils.isEmpty(this.solarObject.getVideo());
        if (hasMovie) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            //.setAction("Action", null).show();
                    showYoutubeVideo(solarObject.getVideo());
                }
            });
        } else {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            lp.setAnchorId(View.NO_ID);
            fab.setVisibility(View.GONE);
        }

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

        this.moonsLabel.setVisibility(this.solarObject.hasMoons() ? View.VISIBLE : View.GONE);
        this.moonsRecyclerView.setVisibility(this.solarObject.hasMoons() ? View.VISIBLE : View.GONE);

        if (this.solarObject.hasMoons()) {
            SolarObjectsAdapter solarObjectsAdapter = new SolarObjectsAdapter(this.solarObject.getMoons());
            solarObjectsAdapter.setSolarObjectClickedListener(this);
            this.moonsRecyclerView.setAdapter(solarObjectsAdapter);
            this.moonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            this.moonsRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void showYoutubeVideo(String video) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + video));
            startActivity(intent);
        }
    }

    public static void start(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT_KEY, solarObject);
        activity.startActivity(intent);
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        SolarObjectActivity.start(this, solarObject);
    }
}