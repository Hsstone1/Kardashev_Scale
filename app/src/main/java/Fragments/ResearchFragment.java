package Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hunter.kardashevscale.R;

import java.util.Arrays;

import UpgradeTree.UpgradeTree;

public class ResearchFragment extends Fragment {

    private static final String TAG = "test";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_research, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Upgrade: " + UpgradeTree.stage_0.upgrade_0.getUpgradeName());
        Log.d(TAG, "Upgrade: " + UpgradeTree.stage_0.upgrade_0_0.getUpgradeName());
        Log.d(TAG, "Upgrade: " + Arrays.toString(UpgradeTree.stage_0.upgrade_0_0.getUpgradeCost()));
        Log.d(TAG, "Upgrade: " + Arrays.toString(UpgradeTree.stage_0.upgrade_0_0.getUpgradeBonus()));
        Log.d(TAG, "Upgrade: " + UpgradeTree.stage_0.upgrade_0_0.getParentUpgrade().getUpgradeName());


//        for (int i = 0; i < UpgradeTree.stage_0.upgrade_0_0.getUpgradeCost().length; i++) {
//            Log.d(TAG, "Upgrade: " + UpgradeTree.stage_0.upgrade_0_0.getUpgradeCost()[i]);
//
//        }
//        Log.d(TAG, "Upgrade: " + Arrays.toString(UpgradeTree.stage_0.upgrade_0_0.getUpgradeCost()));

    }
}
