package Fragments;

import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunter.kardashevscale.R;

import java.util.ArrayList;

import Adapters.TileGridAdapter;

import static com.example.hunter.kardashevscale.MainActivity.gameData;


public class WorldFragment extends Fragment implements TileGridAdapter.ItemClickListener {

    String TAG = "TEST";

    Button energyBtn;

    public TextView foodText;
    public TextView battleText;
    TileGridAdapter adapter;

    public final static int GRID_COLS = 4;
    public final static int GRID_ROWS = 25;
    public final int pattern[] = {1, 2, 3, 4, 8, 7, 6, 5, 9, 10, 11, 12, 16, 15, 14, 13, 17, 18, 19, 20, 24, 23, 22, 21, 25, 26, 27, 28, 32, 31, 30, 29, 33, 34, 35, 36, 40, 39, 38, 37, 41, 42, 43, 44, 48, 47, 46, 45, 49, 50, 51, 52, 56, 55, 54, 53, 57, 58, 59, 60, 64, 63, 62, 61, 65, 66, 67, 68, 72, 71, 70, 69, 73, 74, 75, 76, 80, 79, 78, 77, 81, 82, 83, 84, 88, 87, 86, 85, 89, 90, 91, 92, 96, 95, 94, 93, 97, 98, 99, 100};


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


        ArrayList<String> data = new ArrayList<>();
        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<Boolean> captured = new ArrayList<>();
        ArrayList<Double> defense = new ArrayList<>();
        ArrayList<Double> resourceWeight = new ArrayList<>();


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


        int filler;
        int bossBonus = 1;
        for (int i = 0; i < GRID_COLS * GRID_ROWS; i++) {
            if (i % 10 == 9) {
                bossBonus = 5;
                images.add(R.drawable.ic_bosstexture);
            } else {
                bossBonus = 1;
                images.add(R.drawable.ic_unclaimed);
            }
            filler = pattern[i];
            data.add(String.valueOf(filler));
            captured.add(false);
            defense.add(bossBonus * filler * filler * filler * 125d);
            resourceWeight.add(bossBonus * Math.max( Math.random(),i*bossBonus/(GRID_COLS*GRID_ROWS)) * filler);
        }


        captured.set(0, true);   //home tile
        images.set(0, R.drawable.ic_claimed);

        // set up the RecyclerView
        RecyclerView recyclerView = getView().findViewById(R.id.tileGrid_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), GRID_COLS));
        adapter = new TileGridAdapter(getContext(), captured, images, data, defense, resourceWeight);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    public void onItemClick(View view, int position) {
        if (gameData.getBattle() > adapter.getDefense(position)) {
            adapter.setCaptured(position);
            gameData.setTilesCaptured(gameData.getTilesCaptured() + 1);

        }
        Log.d("TAG", "#" + pattern[position] + " | C: " + adapter.isCaptured(position) + " | D: " + gameData.formatSuffix(adapter.getDefense(position)) + " | R: " + gameData.formatSuffix(adapter.getResWeight(position)) + "x");
        Toast.makeText(getContext(), "#" + pattern[position] + " |  C: " + " |  D: " + gameData.formatSuffix(adapter.getDefense(position)) + " |  R: " + gameData.formatSuffix(adapter.getResWeight(position)) + "x", Toast.LENGTH_LONG).show();
    }

}
