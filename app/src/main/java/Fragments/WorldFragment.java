package Fragments;

import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunter.kardashevscale.MainActivity;
import com.example.hunter.kardashevscale.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import Adapters.TileGridAdapter;

import static com.example.hunter.kardashevscale.MainActivity.FPS;
import static com.example.hunter.kardashevscale.MainActivity.gameData;


public class WorldFragment extends Fragment implements TileGridAdapter.ItemClickListener {

    String TAG = "TEST";


    public TextView foodText;
    public TextView battleText;

    public Button battleButton;
    public Button stopBattleButton;
    public Button easyCaptureButton;
    public ProgressBar captureProgress;
    TileGridAdapter adapter;
    //static int pos = 0;
    static int battleTile = 1;  //first uncaptured tile

    static boolean stopBattle = false;

    public final static int GRID_COLS = 4;
    public final static int GRID_ROWS = 25;
    //public final int pattern[] = {1, 2, 3, 4, 8, 7, 6, 5, 9, 10, 11, 12, 16, 15, 14, 13, 17, 18, 19, 20, 24, 23, 22, 21, 25, 26, 27, 28, 32, 31, 30, 29, 33, 34, 35, 36, 40, 39, 38, 37, 41, 42, 43, 44, 48, 47, 46, 45, 49, 50, 51, 52, 56, 55, 54, 53, 57, 58, 59, 60, 64, 63, 62, 61, 65, 66, 67, 68, 72, 71, 70, 69, 73, 74, 75, 76, 80, 79, 78, 77, 81, 82, 83, 84, 88, 87, 86, 85, 89, 90, 91, 92, 96, 95, 94, 93, 97, 98, 99, 100};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_world, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        foodText = getView().findViewById(R.id.food_textView);
        battleText = getView().findViewById(R.id.battle_textView);

        battleButton = getView().findViewById(R.id.battle_button);
        stopBattleButton = getView().findViewById(R.id.stopBattle_button);
        captureProgress = getView().findViewById(R.id.tileGrid_progress);
        easyCaptureButton = getView().findViewById(R.id.captureEasy_button);


        final ArrayList<String> data = new ArrayList<>();
        final ArrayList<Integer> images = new ArrayList<>();
        final ArrayList<Integer> textColors = new ArrayList<>();     //R - unkillable, Y - Maybe, G - Likely, W - Captured
        final ArrayList<Boolean> captured = new ArrayList<>();
        final ArrayList<Double> defense = new ArrayList<>();
        final ArrayList<Double> resourceWeight = new ArrayList<>();
        final ArrayList<Integer> captureProgress = new ArrayList<>();




        int bossBonus;
        int bossCount = 1;
        for (int i = 1; i <= GRID_COLS * GRID_ROWS; i++) {
            if (i % 10 == 0) {
                bossBonus = 2;
                images.add(R.drawable.ic_bosstexture);
                bossCount++;
            } else {
                bossBonus = 1;
                images.add(R.drawable.ic_unclaimed);
            }

            captureProgress.add(0);
            textColors.add(getResources().getColor(R.color.black, null));
            data.add(String.valueOf(i));
            captured.add(false);
            defense.add(bossBonus * i * i * i * i * 100d * bossCount);
            resourceWeight.add(bossBonus * Math.max(Math.random(), i * bossBonus / (GRID_COLS * GRID_ROWS)) * bossCount);
        }


        captured.set(0, true);   //home tile
        images.set(0, R.drawable.ic_claimed);
        textColors.set(0, getResources().getColor(R.color.white, null));


        // set up the RecyclerView
        RecyclerView recyclerView = getView().findViewById(R.id.tileGrid_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), GRID_COLS));
        adapter = new TileGridAdapter(getContext(), textColors, captured, images, data, defense, resourceWeight, captureProgress);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //refreshed the tile colors
        BackgroundRunnable backgroundRun = new BackgroundRunnable();
        new Thread(backgroundRun).start();


        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (battleTile != 100) {
                    ProgressRunnable progressRun = new ProgressRunnable();
                    new Thread(progressRun).start();
                    updateButtonVisibility(stopBattleButton, View.VISIBLE);
                    updateButtonVisibility(battleButton, View.GONE);
                    stopBattle = false;
                } else {
                    Log.d(TAG, "You captured all tiles.");
                }
            }
        });

        stopBattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "STOP BATTLE");
                stopBattle = true;
                updateButtonVisibility(stopBattleButton, View.GONE);
                updateButtonVisibility(battleButton, View.VISIBLE);
            }
        });

        easyCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = battleTile; i < 100; i++) {
                    if(adapter.getDefense(i) < gameData.getBattle()/10){
                        updateAdapterCaptured(i);
                        battleTile++;
                    } else if(adapter.getDefense(i) < gameData.getBattle()/3) {
                        toastMessage("You can not easy capture any more land.",Toast.LENGTH_LONG);
                        break;
                    }
                }
            }
        });
    }

    //prints info when clicked grid tile
    public void onItemClick(View view, int position) {
        Log.d(TAG, "#" + (position+1) + " | C: " + adapter.isCaptured(position) + " | D: " + gameData.formatSuffix(adapter.getDefense(position)) + " | R: " + gameData.formatSuffix(adapter.getResWeight(position)) + "x");
        Toast.makeText(getContext(), "#" + (position+1) + " |  C: " + " |  D: " + gameData.formatSuffix(adapter.getDefense(position)) + " |  R: " + gameData.formatSuffix(adapter.getResWeight(position)) + "x", Toast.LENGTH_LONG).show();
    }

    class BackgroundRunnable implements Runnable{
        @Override
        public void run() {
            int count = 0;
            while(true){
                count++;
                checkTileColors();
                if(count %5 == 0){
                    //Log.d(TAG, "Resource Bonus: " + gameData.getResourceBonus());
                }

                try {
                    Thread.sleep((long) (2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class ProgressRunnable implements Runnable {
        @Override
        public void run() {
            int count = 0;
            while (true) {
                count++;
                final double battleRatio = gameData.getBattle() / adapter.getDefense(battleTile);
                gameData.setInBattle(true);

                if (battleRatio <= 1) {
                    //decreases population when bellow 1
                    gameData.setPopulation(gameData.getPopulation() * Math.pow(Math.max(1 + Math.log(battleRatio), 0.05), 0.02));
                    if (count % 30 == 0) {
                       // Log.d(TAG, "run: " + Math.pow(Math.max(1 + Math.log(battleRatio), 0.05), 0.02));
                    }

                    if (count % (FPS / 4) == 0) {
                        adapter.setProgress(battleTile, (int) Math.pow(Math.max(battleRatio * (1 - Math.log(battleRatio)), 0), 1.5));
                        updateProgress(captureProgress, captureProgress.getProgress() + (int) Math.pow(Math.max(battleRatio * (1 - Math.log(battleRatio)), 0), 1.5));
                    }
                } else {
                    if (count % (FPS / 4) == 0) {
                        adapter.setProgress(battleTile, (int) Math.pow(Math.max(battleRatio * (1 + Math.log(battleRatio)), 0), 1.5));
                        updateProgress(captureProgress, captureProgress.getProgress() + (int) Math.pow(Math.max(battleRatio * (1 + Math.log(battleRatio)), 0), 1.5));
                    }
                }

                if (count % 60 == 0) {
                    Log.d(TAG, "Tile: " + battleTile + " | progress: " + adapter.getProgress(battleTile) + " | Ratio: " + gameData.formatDouble(battleRatio * (1 + Math.log(battleRatio)), 2));
                }
                if (battleRatio < .1) {
                    toastMessage("You are too weak for this tile. ", Toast.LENGTH_LONG);
                    tileEnd();
                    break;
                }

                if (stopBattle) {
                    updateButtonVisibility(stopBattleButton, View.GONE);
                    updateButtonVisibility(battleButton, View.VISIBLE);
                    stopBattle = false;
                    break;
                }

                if (adapter.getProgress(battleTile) >= 100) {
                    updateAdapterCaptured(battleTile);
                    battleTile++;
                    tileEnd();
                    break;
                }

                try {
                    Thread.sleep((long) (1000 / FPS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    //    public void updateAdapterProgress(final int position, final int value){
//        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                adapter.setProgress(position, value);
//            }
//        });
//    }

    public void updateProgress(final ProgressBar progressBar, final int value) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(value);
            }
        });
    }

    public void updateAdapterCaptured(final int position) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setCaptured(position);
                gameData.setTilesCaptured(gameData.getTilesCaptured() + 1);
                Log.d(TAG, "CAPTURED");
                gameData.setInBattle(false);
            }
        });
    }

    public void updateAdapterColor(final int position, final int color) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setTextColor(position, color);
                adapter.notifyItemChanged(position);
            }
        });
    }

    public void updateButtonVisibility(final Button button, final int visibility) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(visibility);
            }
        });
    }

    public void toastMessage(final String text, final int length) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), text, length).show();
            }
        });
    }

    public void tileEnd() {
        updateButtonVisibility(stopBattleButton, View.GONE);
        updateButtonVisibility(battleButton, View.VISIBLE);
        updateProgress(captureProgress, 0);
        gameData.setInBattle(false);

    }

    public int calcTileColor(double battle) {
        if (battle > 1) {
            //Log.d(TAG, "COLOR GREEN");
            return getResources().getColor(R.color.lime, null);
        } else if (battle > .7) {
            return getResources().getColor(R.color.yellow, null);
        } else if ( battle > .15){
            return getResources().getColor(R.color.red, null);
        } else {
            return getResources().getColor(R.color.black, null);
        }
    }
    public void checkTileColors(){
        for (int i = battleTile; i < 100; i++) {
            //if(adapter.getTextColor(i) != getResources().getColor(R.color.lime, null)) {
                updateAdapterColor(i, calcTileColor(gameData.getBattle() / adapter.getDefense(i)));
            //}
            if(adapter.getDefense(i) / gameData.getBattle() > 20)
                break;

        }
    }
}






//THIS IS FOR PRINTING SNAKE PATTERN WHEN GRID IS DIFFERENT FROM 4 X 25

//        int snakePattern[][] = new int[GRID_ROWS][GRID_COLS];
//        int num = 1;
//
//        for (int i = 0; i < GRID_ROWS; i++) {
//            for (int j = 0; j < GRID_COLS; j++) {
//                snakePattern[i][j] = num;
//                num++;
//            }
//        }
//
//        for (int i=0; i < GRID_ROWS; ++i) {
//            if (i%2 == 0) {
//                // for even rows, print as we normally would
//                for (int j=0; j < GRID_COLS; ++j) {
//                    System.out.printf("%3d", snakePattern[i][j]);
//                    System.out.print(",");
//
//                }
//            }
//            else {
//                // for odd rows, iterate backwards over the array, the print left to right
//                for (int j=GRID_COLS-1; j >= 0; --j) {
//                    System.out.printf("%3d", snakePattern[i][j]);
//                    System.out.print(",");
//                }
//            }
//
//        }
//        System.out.println();