package UpgradeTree;

public class UpgradeObject {

    private String upgradeName;
    private UpgradeParent upgradeParent;
    private UpgradeCost upgradeCost;
    private UpgradeBonus upgradeBonus;
    private boolean purchased;

    public static class Builder {

        private String upgradeName;
        private UpgradeParent upgradeParent;
        private UpgradeCost upgradeCost;
        private UpgradeBonus upgradeBonus;
        private boolean purchased;


        public Builder(String upgradeName) {
            this.upgradeName = upgradeName;
        }


        public Builder upgradeParent(UpgradeParent parentUpgrade) {
            this.upgradeParent = parentUpgrade;
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
            upgrade.upgradeParent = this.upgradeParent;
            upgrade.upgradeCost = this.upgradeCost;
            upgrade.upgradeBonus = this.upgradeBonus;
            upgrade.purchased = this.purchased;
            return upgrade;
        }

    }

    //sets the default values
    private UpgradeObject() {
        this.upgradeName = "";
        this.upgradeParent = null;
        this.upgradeCost = null;
        this.purchased = false;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public UpgradeObject[] getUpgradeParent() {
        return new UpgradeObject[]{upgradeParent.getParentOne(),upgradeParent.getParentTwo(),upgradeParent.getParentThree(),upgradeParent.getParentFour(),upgradeParent.getParentFive()};
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

