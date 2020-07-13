package UpgradeTree;


public class UpgradeTree {
    public static class stage_0 { //upgrade_(PARENT NUM)_(CHILD NUM)
        public static UpgradeObject upgrade_0 = new UpgradeObject.Builder("Stone Tools")
                .tileUnlock(2).build();
        public static UpgradeObject upgrade_0_0 = new UpgradeObject.Builder("Animal Cleaning")
                .hasParent(upgrade_0).tileUnlock(2)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().foodBonus(0.1).build()).build();

        public static UpgradeObject upgrade_0_1 = new UpgradeObject.Builder("Mechanical Advantage")
                .hasParent(upgrade_0).tileUnlock(2)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().energyBonus(0.1).build()).build();

        public static UpgradeObject upgrade_0_2 = new UpgradeObject.Builder("Leather Clothing")
                .hasParent(upgrade_0).tileUnlock(2)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().battleBonus(0.1).build()).build();


        public static UpgradeObject upgrade_1 = new UpgradeObject.Builder("Fire")
                .tileUnlock(3).build();
        public static UpgradeObject upgrade_1_0 = new UpgradeObject.Builder("Cook Meat")
                .hasParent(upgrade_1).tileUnlock(3)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().foodBonus(0.1).build()).build();

        public static UpgradeObject upgrade_1_1 = new UpgradeObject.Builder("Torches")
                .hasParent(upgrade_1).tileUnlock(3)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().prodBonus(0.1).build()).build();

        public static UpgradeObject upgrade_1_2 = new UpgradeObject.Builder("Sharper Tools")
                .hasParent(upgrade_1).tileUnlock(3)
                .upgradeCost(new UpgradeCost.Builder().energyCost(5e5).build())
                .upgradeBonus(new UpgradeBonus.Builder().battleBonus(0.1).build()).build();

    }
}


        /*
        public static UpgradeObject upgrade_0 = new UpgradeObject(null, "Stone Tools", false,
                new UpgradeCost(2, 1e6, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                new UpgradeBonus(0,0,0,0));
        */
