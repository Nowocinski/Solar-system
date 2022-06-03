package com.example.solarsystem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import com.example.solarsystem.databinding.ActivityMainBinding;
import com.example.solarsystem.ui.moons.MoonsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements MoonsFragment.ITabCallback {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    TabLayout moonsTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_planets, R.id.nav_sunshine, R.id.nav_moons, R.id.nav_other)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setCheckedItem(R.id.nav_planets);

        this.moonsTabLayout = binding.appBarMain.moonsTabLayout;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void showTabs(ViewPager2 viewPager2, SolarObject[] solarObjects) {
        this.moonsTabLayout.setVisibility(View.VISIBLE);
        new TabLayoutMediator(this.moonsTabLayout, viewPager2, (tab, position) -> {
            tab.setText(solarObjects[position].getName());
        }).attach();
    }

    @Override
    public void hideTabs() {
        this.moonsTabLayout.removeAllTabs();
        // this.moonsTabLayout.setOnTabSelectedListener(null);
        // this.moonsTabLayout.removeOnTabSelectedListener();
        // TODO: Usuwanie listenerów nie działa. Metoda setOnTabSelectedListener jest przestarzała,
        //  a w removeOnTabSelectedListener nie jak pobrać wszystkich listenerów
        this.moonsTabLayout.setVisibility(View.GONE);
    }
}