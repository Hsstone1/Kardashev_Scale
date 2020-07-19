package UpgradeTree.UpgradeObject;

public class UObject {

    private String upgradeName;
    private UParent uParent;
    private UCost uCost;
    private UBonus uBonus;
    private boolean purchased;

    public static class Builder {

        private String upgradeName;
        private UParent uParent;
        private UCost uCost;
        private UBonus uBonus;
        private boolean purchased;


        public Builder(String upgradeName) {
            this.upgradeName = upgradeName;
        }


        public Builder uParent(UParent parentUpgrade) {
            this.uParent = parentUpgrade;
            return this;
        }

        public Builder uCost(UCost uCost) {
            this.uCost = uCost;
            return this;
        }

        public Builder uBonus(UBonus uBonus) {
            this.uBonus = uBonus;
            return this;
        }

        public Builder isPurchased(boolean purchased) {
            this.purchased = purchased;
            return this;
        }

        public UObject build() {
            UObject upgrade = new UObject();
            upgrade.upgradeName = this.upgradeName;
            upgrade.uParent = this.uParent;
            upgrade.uCost = this.uCost;
            upgrade.uBonus = this.uBonus;
            upgrade.purchased = this.purchased;
            return upgrade;
        }

    }

    //sets the default values
    private UObject() {
        this.upgradeName = "";
        this.uParent = null;
        this.uCost = null;
        this.purchased = false;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public UObject[] getuParent() {
        return new UObject[]{uParent.getParentOne(), uParent.getParentTwo(), uParent.getParentThree(), uParent.getParentFour(), uParent.getParentFive()};
    }

    public double[] getuCost() {
        return new double[]{uCost.getEnergyCost(), uCost.getWoodCost(),
                uCost.getCopperCost(), uCost.getBronzeCost(), uCost.getIronCost(),
                uCost.getRefinedCost(), uCost.getPlasticsCost(), uCost.getUraniumCost(),
                uCost.getFuelCost(), uCost.getUnobtaniumCost(), uCost.getAntimatterCost()};
    }

    public double[] getuBonus() {
        return new double[]{uBonus.getBattleBonus(), uBonus.getFoodBonus(), uBonus.getProductionBonus(), uBonus.getEnergyBonus()};
    }


    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}

