package UpgradeTree;

public class UpgradeObject {

    private String upgradeName;
    private int tileUnlock;
    private UpgradeObject parentUpgrade;
    private UpgradeCost upgradeCost;
    private UpgradeBonus upgradeBonus;
    private boolean purchased;

    public static class Builder {

        private String upgradeName;
        private int tileUnlock;
        private UpgradeObject parentUpgrade;
        private UpgradeCost upgradeCost;
        private UpgradeBonus upgradeBonus;
        private boolean purchased;


        public Builder(String upgradeName) {
            this.upgradeName = upgradeName;
        }

        public Builder tileUnlock(int tileUnlock) {
            this.tileUnlock = tileUnlock;
            return this;
        }

        public Builder hasParent(UpgradeObject parentUpgrade) {
            this.parentUpgrade = parentUpgrade;
            return this;
        }

        public Builder upgradeCost(UpgradeCost upgradeCost) {
            this.upgradeCost = upgradeCost;
            return this;
        }

        public Builder upgradeBonus(UpgradeBonus upgradeBonus) {
            this.upgradeBonus = upgradeBonus;
            return this;
        }

        public Builder isPurchased(boolean purchased) {
            this.purchased = purchased;
            return this;
        }

        public UpgradeObject build() {
            UpgradeObject upgrade = new UpgradeObject();
            upgrade.upgradeName = this.upgradeName;
            upgrade.parentUpgrade = this.parentUpgrade;
            upgrade.upgradeCost = this.upgradeCost;
            upgrade.upgradeBonus = this.upgradeBonus;
            upgrade.purchased = this.purchased;
            return upgrade;
        }

    }

    //sets the default values
    private UpgradeObject() {
        this.upgradeName = "";
        this.tileUnlock = 0;
        this.parentUpgrade = null;
        this.upgradeCost = null;
        this.purchased = false;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public int getTileUnlock() {
        return tileUnlock;
    }

    public UpgradeObject getParentUpgrade() {
        return parentUpgrade;
    }

    public double[] getUpgradeCost() {
        return new double[]{upgradeCost.getEnergyCost(), upgradeCost.getWoodCost(),
                upgradeCost.getCopperCost(), upgradeCost.getBronzeCost(), upgradeCost.getIronCost(),
                upgradeCost.getRefinedCost(), upgradeCost.getPlasticsCost(), upgradeCost.getUraniumCost(),
                upgradeCost.getFuelCost(), upgradeCost.getUnobtaniumCost(), upgradeCost.getAntimatterCost()};
    }

    public double[] getUpgradeBonus() {
        return new double[]{upgradeBonus.getBattleBonus(), upgradeBonus.getFoodBonus(), upgradeBonus.getProductionBonus(), upgradeBonus.getEnergyBonus()};
    }

    public boolean isPurchased() {
        return purchased;
    }
}









/*
    private UpgradeObject parentUpgrade;
    private String upgradeName;
    private UpgradeCost upgradeCost;
    private UpgradeBonus upgradeBenefit;
    private boolean purchased;

    public UpgradeObject(UpgradeObject parentUpgrade, String upgradeName, boolean purchased, UpgradeCost upgradeCost, UpgradeBonus upgradeBenefit) {
        this.parentUpgrade = parentUpgrade;
        this.upgradeName = upgradeName;
        this.purchased = purchased;
        this.upgradeCost = upgradeCost;
        this.upgradeBenefit = upgradeBenefit;
    }


    public UpgradeObject getParentUpgrade() {
        return parentUpgrade;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public double[] getUpgradeCost() {
        return new double[]{upgradeCost.getTileCount(), upgradeCost.getEnergyCost(), upgradeCost.getWoodCost(), upgradeCost.getCopperCost(),
                upgradeCost.getBronzeCost(), upgradeCost.getIronCost(), upgradeCost.getRefinedCost(),
                upgradeCost.getPreciousMetalCount(), upgradeCost.getPlasticsCost(), upgradeCost.getUraniumCost(),
                upgradeCost.getFuelCost(), upgradeCost.getAdamantiumCount(), upgradeCost.getExoticMaterialCount()};
    }

    public double[] getUpgradeBonus() {
        return new double[]{upgradeBenefit.getBattleBonus(), upgradeBenefit.getFoodBonus(), upgradeBenefit.getProductionBonus(), upgradeBenefit.getEnergyBonus()};
    }

    */
