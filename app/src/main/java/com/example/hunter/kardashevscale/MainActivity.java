package com.example.hunter.kardashevscale;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Objects;

import Data.GameData;
import Fragments.BattleFragment;
import Fragments.GuideFragment;
import Fragments.ProductionFragment;
import Fragments.UpgradeFragment;
import Fragments.SettingsFragment;
import Fragments.ShopFragment;
import Fragments.StatsFragment;
import Fragments.TimeTravelFragment;
import Fragments.WorldFragment;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public void initGameVals() {
        gameData.setEnergyPerPop(100);
        gameData.setPopulation(INIT_POPULATION);
        gameData.setEnergySpent(0);
        gameData.setTilesCaptured(1);
        gameData.setFoodBonus(1);
        gameData.setFood(1000);  //cannot go bellow 1000
        gameData.setBattleBonus(1);  //increases battle per pop
        gameData.setCaptureBonus(1);
        gameData.setNumSuffix(false);
        gameData.setTextSuffix(true);
        gameData.setProductionBonus(1);

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


    public static GameData gameData = new GameData();


    //public final static double gameDoubleVals[] = new double[]{gameData.getAdamantium(), gameData.getAdamantiumPerSec(), gameData.getBattle(), gameData.getTimePenalty(), gameData.getBattleUpgrades(), gameData.getDay(), gameData.getEnergy(), gameData.getEnergyPerPop(), gameData.getEnergyPerSec(), gameData.getExoticMaterial(), gameData.getExoticMaterialPerSec(), gameData.getFood(), gameData.getFoodBonus(), gameData.getFoodPerSec(), gameData.getFoodUpgrades(), gameData.getMetals(), gameData.getMetalsPerSec(), gameData.getPopulation(), gameData.getPopulationPerSec(), gameData.getRefinedMetals(), gameData.getRefinedMetalsPerSec(), gameData.getCaptureBonus(), gameData.getTilesCaptured(), gameData.getUranium(), gameData.getUraniumPerSec(), gameData.getWood(), gameData.getWoodPerSec(), gameData.getYear()};


    //toolbar
    private TextView battleText;
    private TextView battleMultiText;
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
    private TextView civEnergyText;


    //instantiates all fragments so they can be loaded from the start
    Fragment worldFragment = new WorldFragment();
    Fragment productionFragment = new ProductionFragment();
    Fragment researchFragment = new UpgradeFragment();
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


        battleText = findViewById(R.id.battle_text);
        battleMultiText = findViewById(R.id.battle_multi_text);
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
        civEnergyText = header.findViewById(R.id.energy_text);


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


        //if(loadGame("savegame") == null)
        initGameVals();
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 1);
        }

        //gameData = loadGame("savegame");


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
            long count = 0;
            double timeGameRate;
            double totDays;
            double lastFramePop = gameData.getPopulation();
            boolean timeTooFast = false;
            boolean firstCycleRun = false;


            while (true) {
                count++;
                currentDate = new Date();
                currentMS = currentDate.getTime();
                timeGameRate = Math.max(((currentMS - startMS) / 1000), 1);
                totDays = gameData.getYear() * DAYS_IN_YEAR;

                calculateFoodSuply();
                gameData.setDay(totDays);
                gameData.setPopulationPerSec(calcPopulation(gameData.getFood()));
                gameData.setTimePenalty(calcTimePenalty(gameData.getYear() * DAYS_IN_YEAR, 1));    //500 normal
                gameData.setBattle(calcBattle(gameData.getPopulation()) * gameData.getTimePenalty() * .5);    //.5 simulates half male population
                gameData.setFood(Math.max(gameData.getFood() + gameData.getFoodPerSec() / FPS, 0));
                gameData.setPopulation(gameData.getPopulation() + gameData.getPopulationPerSec() / FPS);
                gameData.setEnergyPerSec(gameData.getPopulationPerSecREAL() * gameData.getEnergyPerPop() * .25/* * calcTimePenalty(gameData.getYear() * DAYS_IN_YEAR, 1000)*/);
                gameData.setEnergy(gameData.getEnergy() + (gameData.getEnergyPerSec() / FPS) - gameData.getEnergySpent());
                gameData.setYear(timeGameRate / SECONDS_IN_YEAR);


                if (!timeTooFast)
                    setUIText(timeDayText, gameData.formatDouble(DAYS_IN_YEAR * (gameData.getYear() - Math.floor(gameData.getYear())), 0));
                setUIText(timeYearText, "Year " + gameData.formatSuffix(Math.floor(gameData.getYear())));
                setUIText(popTotText, gameData.formatSuffix(gameData.getPopulation()));
                setUIText(battleText, gameData.formatSuffix(gameData.getBattle()));

                if (count % 2 == 0) {
                    if (gameData.getBattleBonus() * gameData.getTimePenalty() < 1) {
                        setUIText(battleMultiText, gameData.formatDouble(gameData.getTimePenalty() * gameData.getBattleBonus(), 2) + "x");
                    } else {
                        setUIText(battleMultiText, gameData.formatSuffix(gameData.getTimePenalty() * gameData.getBattleBonus()) + "x");
                    }
                    if(Math.abs(gameData.getPopulationPerSecREAL()) > 1) {
                        setUIText(popDerText, gameData.formatSuffix((gameData.getPopulation() - lastFramePop) * FPS / 2) + "/s");
                    } else {
                        setUIText(popDerText, 0 + "/s");
                    }
                    gameData.setPopulationPerSecREAL((gameData.getPopulation() - lastFramePop) * FPS / 2);
                    //setUIText(popDerText, gameData.formatSuffix(gameData.getPopulationPerSec()) + "/s");

                    if (firstCycleRun) {
                        setUIText(((WorldFragment) worldFragment).foodText, "Food: " + gameData.formatSuffix(gameData.getFood()) + " (" + gameData.formatSuffix(gameData.getFoodPerSec()) + "/s)");

                    }
                    lastFramePop = gameData.getPopulation();
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
                    //Log.d(TAG, "E/s: " + (gameData.getEnergyPerSec() / FPS));
                }

                if (count % 6000 == 0) {
                    //saveGame("savegame");
                    //writeToFile(getApplicationContext(), "savegame", String.valueOf(gameData));
                    //Log.d(TAG, "GAME DATA: " + saveGameWriteFormat());
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
////        return (supportPop / (1 + (supportPop / 200) * Math.exp(RAMP_POPULATION * totDays))) * (Math.pow(0.1 * totDays, .25)) + INIT_POPULATION;
//
//
//        return Math.max(/*(Math.log(Math.cbrt(totDays) * gameData.getTilesCaptured()) * Math.pow(gameData.getTilesCaptured(), 1.2)) + */((((supportPop * tileGrowthPop) * ((1 / 9) * (startRate * Math.exp(RAMP_POPULATION * totDays) * totDays) + ((1 / 7) * (startRate * Math.exp(RAMP_POPULATION * totDays)) + 1))) / (Math.pow(totDays, .75) * (Math.pow(startRate * Math.exp(RAMP_POPULATION * totDays) + 1, 2)))) * Math.log(totDays / Math.pow(tileGrowthPop, .5))), 0);
//    }

    public double calcPopulation(double food) {

        return Math.max(1.5 * Math.sqrt(food - 1) + 2, 1);
    }

    public double calcBattle(double population) {

        return population * gameData.getBattleBonus();
    }
    //gameData.setPopulation(gameData.getPopulation() * Math.pow(Math.max(1 + Math.log(battleRatio), 0.05), 0.02));

    public void calculateFoodSuply() {
        gameData.setFood(Math.max((gameData.getFood() - 0.01 * gameData.getPopulation()), 1));
        if (gameData.isInBattle()) {
            gameData.setFoodPerSec(Math.max(gameData.getFoodBonus() * calcFoodPerSec() * .5, 1)); //simulates farmers heading for battle
        } else {
            gameData.setFoodPerSec(Math.max(gameData.getFoodBonus() * calcFoodPerSec(), 1));
        }
        gameData.setPopulation(gameData.getPopulation() * Math.min(Math.pow(Math.log(Math.max(gameData.getFood(),100)) / 5 ,0.02),1));
    }

    public double calcFoodPerSec() {
        return (500 * Math.max(Math.floor(Math.pow(gameData.getTilesCaptured(), 1.1)), 0) * Math.log10(.1 * gameData.getPopulation()));
    }

    public double calcTimePenalty(double totDays, double daysTo90Percent) {
        return (1 / (1 + 100 * Math.exp(-(6.8 / daysTo90Percent) * totDays)));  //6.8 aligns the formula to the day
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
        setUIText(civEnergyText, getString(R.string.energy_string) + ": " + gameData.formatSuffix(gameData.getEnergy()) + " (" + gameData.formatSuffix(gameData.getEnergyPerSec()) + " /s) watts");


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
        //Log.d(TAG, "showFragment: " + fragment);

    }

    public void saveGame(String filename) {
        try {
            FileOutputStream fos = getApplication().openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(gameData);
            Log.d(TAG, "SAVED GAME | ENNERGY:" + gameData.getEnergy());
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameData loadGame(String filename) {
        try {
            FileInputStream fis = getApplication().openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            GameData data = (GameData) is.readObject();
            is.close();
            fis.close();
            Log.d(TAG, "LOAD | ENERGY: " + data.getEnergy());
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private void writeToFile(Context context, String filename, String data) {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }


//    private String readFromFile(Context context, String filename) {
//
//        String ret = "";
//
//        try {
//            InputStream inputStream = context.openFileInput(filename);
//
//            if (inputStream != null) {
//                int pos = 0;
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//
//                while ((receiveString = bufferedReader.readLine()) != null) {
//                    stringBuilder.append("\n").append(receiveString);
//                    pos++;
//
//                }
//
//                inputStream.close();
//                ret = stringBuilder.toString();
//            }
//        } catch (FileNotFoundException e) {
//            Log.e(TAG, "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e(TAG, "Can not read file: " + e.toString());
//        }
//
//        return ret;
//    }

    //gameData.getAdamantium() ,gameData.getAdamantiumpersec() ,gameData.getBattle() ,gameData.getBattletimepenalty() ,gameData.getBattleupgrades() ,gameData.getDay() ,gameData.getEnergy() ,gameData.getEnergyperpop() ,gameData.getEnergypersec() ,gameData.getExoticmaterial() ,gameData.getExoticmaterialpersec() ,gameData.getFood() ,gameData.getFoodbonus() ,gameData.getFoodpersec() ,gameData.getFoodupgrades() ,gameData.getMetals() ,gameData.getMetalspersec() ,gameData.getPopulation() ,gameData.getPopulationpersec() ,gameData.getRefinedmetals() ,gameData.getRefinedmetalspersec() ,gameData.getResourcebonus() ,gameData.getTilescaptured() ,gameData.getUranium() ,gameData.getUraniumpersec() ,gameData.getWood() ,gameData.getWoodpersec() ,gameData.getYear()
//    public String saveGameWriteFormat() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < gameDoubleVals.length; i++) {
//            s.append(gameDoubleVals[i]).append("\n");
//        }
//        return s.toString();
//    }

//    public void setGameData(int pos) {
//        switch (pos) {
//            case 1:
//                gameData.setAdamantium();
//                gameData.setAdamantiumPerSec();
//                gameData.setBattle();
//                gameData.setTimePenalty();
//                gameData.setBattleUpgrades();
//                gameData.setDay();
//                gameData.setEnergy();
//                gameData.setEnergyPerPop();
//                gameData.setEnergyPerSec();
//                gameData.setExoticMaterial();
//                gameData.setExoticMaterialPerSec();
//                gameData.setFood();
//                gameData.setFoodBonus();
//                gameData.setFoodPerSec();
//                gameData.setFoodUpgrades();
//                gameData.setMetals();
//                gameData.setMetalsPerSec();
//                gameData.setPopulation();
//                gameData.setPopulationPerSec();
//                gameData.setRefinedMetals();
//                gameData.setRefinedMetalsPerSec();
//                gameData.setCaptureBonus();
//                gameData.setTilesCaptured();
//                gameData.setUranium();
//                gameData.setUraniumPerSec();
//                gameData.setWood();
//                gameData.setWoodPerSec();
//                gameData.setYear();
//
//
//        }
//    }


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


