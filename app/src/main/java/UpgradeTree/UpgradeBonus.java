package UpgradeTree;

import android.os.Build;

public class UpgradeBonus {
    private double battleBonus;
    private double foodBonus;
    private double productionBonus;
    private double energyBonus;

    public static class Builder {
        private double battleBonus;
        private double foodBonus;
        private double productionBonus;
        private double energyBonus;


        public Builder battleBonus(double battleBonus) {
            this.battleBonus = battleBonus;
            return this;
        }

        public Builder foodBonus(double foodBonus) {
            this.foodBonus = foodBonus;
            return this;
        }

        public Builder prodBonus(double productionBonus) {
            this.productionBonus = productionBonus;
            return this;
        }

        public Builder energyBonus(double energyBonus) {
            this.energyBonus = energyBonus;
            return this;
        }

        public UpgradeBonus build() {
            UpgradeBonus bonus = new UpgradeBonus();
            bonus.battleBonus = this.battleBonus;
            bonus.foodBonus = this.foodBonus;
            bonus.productionBonus = this.productionBonus;
            bonus.energyBonus = this.energyBonus;
            return bonus;
        }
    }

    //sets the default values
    private UpgradeBonus() {
        this.battleBonus = 0;
        this.foodBonus = 0;
        this.productionBonus = 0;
        this.energyBonus = 0;

    }

    public double getBattleBonus() {
        return battleBonus;
    }

    public double getFoodBonus() {
        return foodBonus;
    }

    public double getProductionBonus() {
        return productionBonus;
    }

    public double getEnergyBonus() {
        return energyBonus;
    }
}
