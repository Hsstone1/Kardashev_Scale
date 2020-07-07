package com.example.hunter.kardashevscale;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.Objects;

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
    public final static int FPS = 60;

    public final static double SECONDS_IN_DAY = 10;
    public final static double DAYS_IN_YEAR = 365;
    public final static double SECONDS_IN_YEAR = (SECONDS_IN_DAY / 60) * 60 * DAYS_IN_YEAR;

    public final static double ENERGY_TO_CIV_1 = Math.pow(10, 16);
    public final static double ENERGY_TO_CIV_2 = Math.pow(10, 26);
    public final static double ENERGY_TO_CIV_3 = Math.pow(10, 36);
    public final static double ENERGY_TO_CIV_4 = Math.pow(10, 80);

    public final static double INIT_POPULATION = 1000;
    public final static double RAMP_POPULATION = -0.03;   //hits inflection at 100 days
    public final static double LOGISTIC_POPULATION = 2500;

    public final static GameData gameData = new GameData();


    //toolbar
    private TextView energyTotText;
    private TextView energyDerText;
    private TextView popTotText;
    private TextView popDerText;
    private TextView timeYearText;
    private TextView timeDayText;

    //nav view
    private DrawerLayout drawer;
    private TextView civText;
    private ProgressBar civProgress;
    private ImageView civIcon;
    private TextView civProgressText;


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


        energyTotText = findViewById(R.id.energy_tot_text);
        energyDerText = findViewById(R.id.energy_der_text);
        popTotText = findViewById(R.id.pop_tot_text);
        popDerText = findViewById(R.id.pop_der_text);
        timeYearText = findViewById(R.id.time_year_text);
        timeDayText = findViewById(R.id.time_day_text);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);        //possible break
        //getSupportActionBar().hide();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //inflates header in navigation view
        View header = navigationView.getHeaderView(0);
        civText = header.findViewById(R.id.civ_text);
        civProgress = header.findViewById(R.id.civ_progress);
        civIcon = header.findViewById(R.id.civ_icon);
        civProgressText = header.findViewById(R.id.civ_progress_text);


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

        initGameVals();
        GameClock runnable = new GameClock();
        new Thread(runnable).start();
    }

    //Sets the game to run at a defined fps
    class GameClock implements java.lang.Runnable {
        @Override
        public void run() {
            Date startDate = new Date();
            Date currentDate;
            long startMS = startDate.getTime();
            long currentMS;
            double timeGameRate;
            long count = 0;
            boolean timeTooFast = false;
            double totDays, daysSinceTileCapture;

            if (gameData.getGameSpeed() > 5 * Math.pow(10, 4)) {
                timeTooFast = true;
                timeDayText.setVisibility(View.GONE);
            }

            while (true) {
                count++;
                currentDate = new Date();
                currentMS = currentDate.getTime();
                timeGameRate = ((currentMS - startMS) * gameData.getGameSpeed() / 1000);

                if (!timeTooFast) {
                    totDays = DAYS_IN_YEAR * (gameData.getYear() - Math.floor(gameData.getYear()));
                } else {
                    totDays = gameData.getYear() * DAYS_IN_YEAR;
                }

                gameData.setDay(totDays);
                gameData.setPopulationPerSec(calculatePopulation(totDays) * gameData.getGameSpeed());


                gameData.setPopulation(gameData.getPopulation() + gameData.getPopulationPerSec() / FPS);
                gameData.setEnergyPerSec(gameData.getPopulationPerSec()  * gameData.getEnergyPerPop());
                gameData.setEnergy(gameData.getEnergy() + gameData.getEnergyPerSec() / FPS);
                gameData.setYear(timeGameRate / SECONDS_IN_YEAR);


                if (!timeTooFast)
                    setUIText(timeDayText, gameData.formatDouble(Math.floor(gameData.getDay())));
                setUIText(timeYearText, "Year " + gameData.formatSuffix(Math.floor(gameData.getYear())));
                setUIText(popTotText, gameData.formatSuffix(gameData.getPopulation()));
                setUIText(energyTotText, gameData.formatSuffix(gameData.getEnergy()));

                if (count % 2 == 0) {
                    setUIText(energyDerText, gameData.formatSuffix(gameData.getEnergyPerSec()) + "/s");
                    setUIText(popDerText, gameData.formatSuffix(gameData.getPopulationPerSec()) + "/s");
                }

                //slower ui update
                if (count % 10 == 0) {
                    updateNavHeader();

                    //Log.d(TAG, "year: " + gameData.getYear() + "        Day: " + gameData.getDay());
                    //Log.d(TAG, "POP: " + (gameData.getPopulation() / timeGameRate) * gameData.getGameSpeed());

//                    if (Double.isNaN(gameData.getEnergy())) {
//                        Log.d(TAG, "YOU HAVE REACHED INFINITY");
//                    }

                }

//                if (count % 60 == 0) {
//                    //once a second
//                }

                try {
                    Thread.sleep((long) (1000 / FPS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initGameVals() {
        gameData.setEnergyPerPop(Math.pow(10, 2));
        gameData.setTilesCaptured(1);
        gameData.setLogisticPopulation(LOGISTIC_POPULATION);
        gameData.setPopulation(INIT_POPULATION);
        gameData.setGameSpeed(1);   //max out at 10x normal speed
        gameData.setEnergy(INIT_POPULATION * gameData.getEnergyPerPop());
        gameData.setFood(10000);
        gameData.setFoodPerSec(100);
    }

    public double calculatePopulation(double totDays) {
        double supportPop = gameData.getLogisticPopulation();
        double tileGrowthPop = Math.pow(gameData.getTilesCaptured(), 1.25);
        double startRate = supportPop / 200;
//        return (supportPop / (1 + (supportPop / 200) * Math.exp(RAMPa_POPULATION * totDays))) * (Math.pow(0.1 * totDays, .25)) + INIT_POPULATION;


        return Math.max(/*(Math.log(Math.cbrt(totDays) * gameData.getTilesCaptured()) * Math.pow(gameData.getTilesCaptured(), 1.2)) + */((((supportPop * tileGrowthPop) * ((1 / 9) * (startRate * Math.exp(RAMP_POPULATION * totDays) * totDays) + ((1 / 7) * (startRate * Math.exp(RAMP_POPULATION * totDays)) + 1))) / (Math.pow(totDays, .75) * (Math.pow(startRate * Math.exp(RAMP_POPULATION * totDays) + 1, 2)))) * Math.log(totDays / Math.pow(tileGrowthPop, .5))), 0);
    }

    //calculates how many people die of old age (1 year)
//    public double calcPopFood(double food){
//
//        return 1.5*Math.sqrt(food - 1) + 2;
//    }


    public void updateNavHeader() {
        if (gameData.getEnergy() < ENERGY_TO_CIV_1) {
            setUIProgress(civProgress, gameData.logProgress(ENERGY_TO_CIV_1, 0));
            setUIText(civText, getString(R.string.type0_string));
            setUIImage(civIcon, R.drawable.ic_type_0);  //local
            setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.logProgress(ENERGY_TO_CIV_1, 0) / 100f + ")");

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_2) {
            setUIProgress(civProgress, gameData.logProgress(ENERGY_TO_CIV_2, ENERGY_TO_CIV_1));
            setUIText(civText, getString(R.string.type1_string));
            setUIImage(civIcon, R.drawable.ic_type_1);  //planetary
            setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.logProgress(ENERGY_TO_CIV_2, ENERGY_TO_CIV_1) / 100f + ")");

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_3) {
            setUIProgress(civProgress, gameData.logProgress(ENERGY_TO_CIV_3, ENERGY_TO_CIV_2));
            setUIText(civText, getString(R.string.type2_string));
            setUIImage(civIcon, R.drawable.ic_type_2);  //stellar
            setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.logProgress(ENERGY_TO_CIV_3, ENERGY_TO_CIV_2) / 100f + ")");

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_4) {
            setUIProgress(civProgress, gameData.logProgress(ENERGY_TO_CIV_4, ENERGY_TO_CIV_3));
            setUIText(civText, getString(R.string.type3_string));
            setUIImage(civIcon, R.drawable.ic_type_3);  //galactic
            setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.logProgress(ENERGY_TO_CIV_4, ENERGY_TO_CIV_3) / 100f + ")");

        } else {
            setUIProgress(civProgress, gameData.logProgress(1e308, ENERGY_TO_CIV_4));
            setUIText(civText, getString(R.string.type4_string));
            setUIImage(civIcon, R.drawable.ic_type_4);  //universal
            setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.logProgress(1e308, ENERGY_TO_CIV_4) / 100f + ")");
        }


    }


    //allows textViews to be edited on the background thread
    private void setUIText(final TextView text, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    //allows progressBars to be edited on the background thread
    private void setUIProgress(final ProgressBar progressBar, final int value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(value);
            }
        });
    }

    //allows imageViews to be edited on the background thread
    private void setUIImage(final ImageView image, final int value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageResource(value);
            }
        });
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


