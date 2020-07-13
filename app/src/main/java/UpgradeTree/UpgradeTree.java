package UpgradeTree;

import static com.example.hunter.kardashevscale.MainActivity.gameData;


public class UpgradeTree {
    public static class stage_0 { //upgrade_(PARENT NUM)_(CHILD NUM)
        public static UpgradeObject upgrade_0 = new UpgradeObject(null, "Stone Tools", false,
                new UpgradeCost(1e6, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        public static UpgradeObject upgrade_0_0 = new UpgradeObject(upgrade_0, "Animal Cleaning", false,
                new UpgradeCost(5e5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));



    }
}
