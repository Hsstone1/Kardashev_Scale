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

    public void initGameVals() {
        gameData.setEnergyPerPop(Math.pow(10, 2));
        gameData.setPopulation(INIT_POPULATION);
        gameData.setEnergy(INIT_POPULATION * gameData.getEnergyPerPop());
        gameData.setTilesCaptured(1);
        gameData.setGameSpeed(1);   //max out at 10x normal speed
        gameData.setFood(100);  //cannot go bellow 100
        gameData.setBattleUpgrades(100);  //increases battle per pop
        gameData.setNumSuffix(false);
        gameData.setTextSuffix(true);

    }


    String TAG = "TEST";
    public final static int FPS = 60;

    public final static double SECONDS_IN_DAY = 1;
    public final static double DAYS_IN_YEAR = 365;
    public final static double SECONDS_IN_YEAR = (SECONDS_IN_DAY / 60) * 60 * DAYS_IN_YEAR;

    public final static double ENERGY_TO_CIV_1 = Math.pow(10, 16);
    public final static double ENERGY_TO_CIV_2 = Math.pow(10, 26);
    public final static double ENERGY_TO_CIV_3 = Math.pow(10, 36);
    public final static double ENERGY_TO_CIV_4 = Math.pow(10, 46);

    public final static double INIT_POPULATION = 100;

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
    Fragment worldFragment = new WorldFragment();
    Fragment productionFragment = new ProductionFragment();
    Fragment researchFragment = new ResearchFragment();
    Fragment battleFragment = new BattleFragment();
    Fragment timeFragment = new TimeTravelFragment();
    Fragment statsFragment = new StatsFragment();
    Fragment guideFragment = new GuideFragment();
    Fragment shopFragment = new ShopFragment();
    Fragment settingsFragment = new SettingsFragment();
    FragmentManager fm = getSupportFragmentManager();
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


        initGameVals();

        FragmentRunnable fragRun = new FragmentRunnable();
        new Thread(fragRun).start();

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
            double totDays;
            boolean firstCycleRun = false;

            if (gameData.getGameSpeed() > 5 * Math.pow(10, 4)) {
                timeTooFast = true;
                timeDayText.setVisibility(View.GONE);
            }

            while (true) {
                count++;
                currentDate = new Date();
                currentMS = currentDate.getTime();
                timeGameRate = Math.max(((currentMS - startMS) * gameData.getGameSpeed() / 1000), 1);


                totDays = gameData.getYear() * DAYS_IN_YEAR;

                calculateFoodSuply();
                gameData.setDay(totDays);
                if(!gameData.isInBattle()) {
                    gameData.setPopulationPerSec(calcPopulation(gameData.getFood()) * gameData.getGameSpeed());
                } else {
                    gameData.setPopulationPerSec(calcPopulation(gameData.getFood()*.05) * gameData.getGameSpeed());
                }
                gameData.setBattleTimePenalty(calcBattleTimePenalty(gameData.getYear() * DAYS_IN_YEAR));
                gameData.setBattle(calcBattle(gameData.getPopulation()) * gameData.getBattleTimePenalty());
                gameData.setFoodPerSec(Math.max(calcFoodPerSec(), 1));
                gameData.setFood(Math.max(gameData.getFood() + gameData.getFoodPerSec() / FPS, 0));
                gameData.setPopulation(gameData.getPopulation() + gameData.getPopulationPerSec() / FPS);
                gameData.setEnergyPerSec(Math.max((gameData.getPopulation() / timeGameRate) * gameData.getEnergyPerPop(), 1));
                gameData.setEnergy(gameData.getEnergy() + gameData.getEnergyPerSec() / FPS);
                gameData.setYear(timeGameRate / SECONDS_IN_YEAR);


                if (!timeTooFast)
                    setUIText(timeDayText, gameData.formatDouble(DAYS_IN_YEAR * (gameData.getYear() - Math.floor(gameData.getYear())), 0));
                setUIText(timeYearText, "Year " + gameData.formatSuffix(Math.floor(gameData.getYear())));
                setUIText(popTotText, gameData.formatSuffix(gameData.getPopulation()));
                setUIText(energyTotText, gameData.formatSuffix(gameData.getEnergy()));

                if (count % 2 == 0) {
                    setUIText(energyDerText, gameData.formatSuffix(gameData.getEnergyPerSec()) + "/s");
                    setUIText(popDerText, gameData.formatSuffix(gameData.getPopulationPerSec()) + "/s");
                    if (firstCycleRun) {
                        setUIText(((WorldFragment) worldFragment).foodText, "Food: " + gameData.formatSuffix(gameData.getFood()) + " (" + gameData.formatSuffix(gameData.getFoodPerSec()) + "/s)");
                        setUIText(((WorldFragment) worldFragment).battleText, "Battle: " + gameData.formatSuffix(gameData.getBattle()) + " (" + gameData.formatDouble(gameData.getBattleTimePenalty(), 2) + "x)");

                    }
                }

                //slower ui update
                if (count % 10 == 0) {
                    updateNavHeader();

//                    if (Double.isNaN(gameData.getEnergy())) {
//                        Log.d(TAG, "YOU HAVE REACHED INFINITY");
//                    }

                }

                //this allows text from fragments to be loaded before they are called
                if (count % 30 == 0) {
                    if (!firstCycleRun)
                        firstCycleRun = true;
                    //Log.d(TAG, "Battle Pen: " + gameData.getBattleTimePenalty());
                }


                try {
                    Thread.sleep((long) (1000 / FPS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class FragmentRunnable implements Runnable {
        @Override
        public void run() {

            fm.beginTransaction().add(R.id.fragment_container, settingsFragment, "settings").hide(settingsFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, shopFragment, "shop").hide(shopFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, guideFragment, "guide").hide(guideFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, statsFragment, "stats").hide(statsFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, timeFragment, "time").hide(timeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, battleFragment, "battle").hide(battleFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, researchFragment, "research").hide(researchFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, productionFragment, "production").hide(productionFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, worldFragment, "world").commit();
        }
    }



//    public double calculatePopulation(double totDays) {
//        double supportPop = gameData.getLogisticPopulation();
//        double tileGrowthPop = Math.pow(gameData.getTilesCaptured(), 1.25);
//        double startRate = supportPop / 200;
////        return (supportPop / (1 + (supportPop / 200) * Math.exp(RAMPa_POPULATION * totDays))) * (Math.pow(0.1 * totDays, .25)) + INIT_POPULATION;
//
//
//        return Math.max(/*(Math.log(Math.cbrt(totDays) * gameData.getTilesCaptured()) * Math.pow(gameData.getTilesCaptured(), 1.2)) + */((((supportPop * tileGrowthPop) * ((1 / 9) * (startRate * Math.exp(RAMP_POPULATION * totDays) * totDays) + ((1 / 7) * (startRate * Math.exp(RAMP_POPULATION * totDays)) + 1))) / (Math.pow(totDays, .75) * (Math.pow(startRate * Math.exp(RAMP_POPULATION * totDays) + 1, 2)))) * Math.log(totDays / Math.pow(tileGrowthPop, .5))), 0);
//    }

    public double calcPopulation(double food) {

        return Math.max(1.5 * Math.sqrt(food - 1) + 2, 1);
    }

    public double calcBattle(double population) {

        return population * gameData.getBattleUpgrades();
    }


    public void calculateFoodSuply() {
        gameData.setFood(Math.max((gameData.getFood() - 0.01 * gameData.getPopulation()), 1));
        gameData.setFoodPerSec(gameData.getFoodPerSec());
        if (gameData.getFood() < 1000) {
            gameData.setPopulation(gameData.getPopulation() * .99);
        } else if (gameData.getFood() < 10000) {
            gameData.setPopulation(gameData.getPopulation() * .999);
        } else if (gameData.getFood() < 100000) {
            gameData.setPopulation(gameData.getPopulation() * .9999);
        }
    }

    public double calcFoodPerSec() {
        return (1000 * Math.max(Math.floor(Math.pow(gameData.getTilesCaptured(), 1.1)), 0) * Math.log10(.1 * gameData.getPopulation()));
    }

    public double calcBattleTimePenalty(double totDays) {
        return (1 / (1 + 100 * Math.exp(-0.01 * totDays)));
    }


    public void updateNavHeader() {
        if (gameData.getEnergy() < ENERGY_TO_CIV_1) {
            setUIProgress(civProgress, (int) Math.floor(100 * gameData.civScale()));
            setUIText(civText, getString(R.string.type0_string));
            setUIImage(civIcon, R.drawable.ic_type_0);  //local

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_2) {
            setUIProgress(civProgress, (int) Math.floor(100 * gameData.civScale()) - 100);
            setUIText(civText, getString(R.string.type1_string));
            setUIImage(civIcon, R.drawable.ic_type_1);  //planetary

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_3) {
            setUIProgress(civProgress, (int) Math.floor(100 * gameData.civScale()) - 200);
            setUIText(civText, getString(R.string.type2_string));
            setUIImage(civIcon, R.drawable.ic_type_2);  //stellar

        } else if (gameData.getEnergy() < ENERGY_TO_CIV_4) {
            setUIProgress(civProgress, (int) Math.floor(100 * gameData.civScale()) - 300);
            setUIText(civText, getString(R.string.type3_string));
            setUIImage(civIcon, R.drawable.ic_type_3);  //galactic

        } else {
            setUIProgress(civProgress, (int) Math.floor(100 * gameData.civScale()) - 400);
            setUIText(civText, getString(R.string.type4_string));
            setUIImage(civIcon, R.drawable.ic_type_4);  //universal
        }
        setUIText(civProgressText, getString(R.string.civ_progress_string) + " (Type " + gameData.formatDouble(gameData.civScale(), 2) + ")");


    }


    //allows textViews to be edited on the background thread
    public final void setUIText(final TextView text, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    //allows progressBars to be edited on the background thread
    public final void setUIProgress(final ProgressBar progressBar, final int value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(value);
            }
        });
    }

    //allows imageViews to be edited on the background thread
    public final void setUIImage(final ImageView image, final int value) {
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


