package com.example.hunter.kardashevscale;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import Fragments.BattleFragment;
import Fragments.GuideFragment;
import Fragments.ProductionFragment;
import Fragments.ResearchFragment;
import Fragments.SettingsFragment;
import Fragments.ShopFragment;
import Fragments.StatsFragment;
import Fragments.TimeTravelFragment;
import Fragments.WorldFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "TEST";
    private DrawerLayout drawer;
    private TextView energyTotText;

    //instantiates all fragments so they can be loaded from the start
    final Fragment worldFragment = new WorldFragment();
    final Fragment productionFragment = new ProductionFragment();
    final Fragment researchFragment = new ResearchFragment();
    final Fragment battleFragment = new BattleFragment();
    final Fragment timeFragment = new TimeTravelFragment();
    final Fragment statsFragment = new StatsFragment();
    final Fragment guideFragment = new GuideFragment();
    final Fragment shopFragment = new ShopFragment();
    final Fragment settingsFragment = new SettingsFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = worldFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().hide();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            //loads the world fragment as the default
            showFragment(worldFragment);
            navigationView.setCheckedItem(R.id.nav_world);
        }

        //loads all fragments, then hides all but the main world fragment
        //this prevents fragments from being recreated when navigating to different fragment
        fm.beginTransaction().add(R.id.fragment_container, settingsFragment, "settings").hide(settingsFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, shopFragment, "shop").hide(shopFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, guideFragment, "guide").hide(guideFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, statsFragment, "stats").hide(statsFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, timeFragment, "time").hide(timeFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, battleFragment, "battle").hide(battleFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, researchFragment, "research").hide(researchFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, productionFragment, "production").hide(productionFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, worldFragment, "world").commit();




        energyTotText = findViewById(R.id.energy_tot_text);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_world:
                showFragment(worldFragment);
                break;

            case R.id.nav_production:
                showFragment(productionFragment);
                break;

            case R.id.nav_research:
                showFragment(researchFragment);
                break;

            case R.id.nav_battle:
                showFragment(battleFragment);
                break;

            case R.id.nav_time_travel:
                showFragment(timeFragment);
                break;

            case R.id.nav_stats:
                showFragment(statsFragment);
                break;

            case R.id.nav_guide:
                showFragment(guideFragment);
                break;

            case R.id.nav_shop:
                showFragment(shopFragment);
                break;

            case R.id.nav_settings:
                showFragment(settingsFragment);
                break;
        }
        return true;
    }



    //shows the fragment the user clicked, reducing repetition
    public void showFragment(Fragment fragment) {
        fm.beginTransaction().hide(active).show(fragment).commit();
        active = fragment;
        drawer.closeDrawer(GravityCompat.START);
        Log.d(TAG, "showFragment: " + fragment);

    }


    //Closes navigation drawer on back pressed, or quits app when it is closed
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}


