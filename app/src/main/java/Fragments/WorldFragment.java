package Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hunter.kardashevscale.GameData;
import com.example.hunter.kardashevscale.R;

import static com.example.hunter.kardashevscale.MainActivity.gameData;


public class WorldFragment extends Fragment {

    String TAG = "TEST";

    Button energyBtn;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_world, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gameData.setEnergy(0);

        energyBtn = getView().findViewById(R.id.energy_button);
        energyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                gameData.setTilesCaptured(gameData.getTilesCaptured() + 1);
                gameData.setEnergyPerPop(gameData.getEnergyPerPop() * 2);
                Log.d(TAG, "Captured: " + gameData.getTilesCaptured());
            }
        });

    }

}
