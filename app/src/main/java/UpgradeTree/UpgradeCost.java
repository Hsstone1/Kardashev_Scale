package UpgradeTree;

public class UpgradeCost {

    private double energyCount;
    private double woodCount;
    private double copperCount;
    private double bronzeCount;
    private double ironCount;
    private double refinedMetalCount;
    private double plasticsCount;
    private double uraniumCount;
    private double fuelCount;
    private double unobtaniumCount;
    private double antimatterCount;

    public static class Builder {

        private double energyCount;
        private double woodCount;
        private double copperCount;
        private double bronzeCount;
        private double ironCount;
        private double refinedMetalCount;
        private double plasticsCount;
        private double uraniumCount;
        private double fuelCount;
        private double unobtaniumCount;
        private double antimatterCount;


        public Builder energyCost(double energyCount){
            this.energyCount = energyCount;
            return this;
        }

        public Builder woodCost(double woodCount){
            this.woodCount = woodCount;
            return this;
        }

        public Builder copperCost(double copperCount){
            this.copperCount = copperCount;
            return this;
        }

        public Builder bronzeCost(double bronzeCount){
            this.bronzeCount = bronzeCount;
            return this;
        }

        public Builder ironCost(double ironCount){
            this.ironCount = ironCount;
            return this;
        }

        public Builder refinedCost(double refinedMetalCount){
            this.refinedMetalCount = refinedMetalCount;
            return this;
        }

        public Builder plasticCost(double plasticsCount){
            this.plasticsCount = plasticsCount;
            return this;
        }

        public Builder uraniumCost(double uraniumCount){
            this.uraniumCount = uraniumCount;
            return this;
        }

        public Builder fuelCost(double fuelCount){
            this.fuelCount = fuelCount;
            return this;
        }

        public Builder unobtainiumCost(double unobtaniumCount){
            this.unobtaniumCount = unobtaniumCount;
            return this;
        }

        public Builder antimatterCost(double antimatterCount){
            this.antimatterCount = antimatterCount;
            return this;
        }



        public UpgradeCost build(){
            UpgradeCost cost = new UpgradeCost();
            cost.energyCount = this.energyCount;
            cost.woodCount = this.woodCount;
            cost.copperCount = this.copperCount;
            cost.bronzeCount = this.bronzeCount;
            cost.ironCount = this.ironCount;
            cost.refinedMetalCount = this.refinedMetalCount;
            cost.plasticsCount = this.plasticsCount;
            cost.uraniumCount = this.uraniumCount;
            cost.fuelCount = this.fuelCount;
            cost.unobtaniumCount = this.unobtaniumCount;
            cost.antimatterCount = this.antimatterCount;

            return cost;
        }

    }

    //sets the default values
    private UpgradeCost(){
        this.energyCount = 0;
        this.woodCount = 0;
        this.copperCount = 0;
        this.bronzeCount = 0;
        this.ironCount = 0;
        this.refinedMetalCount = 0;
        this.plasticsCount = 0;
        this.uraniumCount = 0;
        this.fuelCount = 0;
        this.unobtaniumCount = 0;
        this.antimatterCount = 0;
    }

    public double getEnergyCost() {
        return energyCount;
    }

    public double getWoodCost() {
        return woodCount;
    }

    public double getCopperCost() {
        return copperCount;
    }

    public double getBronzeCost() {
        return bronzeCount;
    }

    public double getIronCost() {
        return ironCount;
    }

    public double getRefinedCost() {
        return refinedMetalCount;
    }

    public double getPlasticsCost() {
        return plasticsCount;
    }

    public double getUraniumCost() {
        return uraniumCount;
    }

    public double getFuelCost() {
        return fuelCount;
    }

    public double getUnobtaniumCost() {
        return unobtaniumCount;
    }

    public double getAntimatterCost() {
        return antimatterCount;
    }
}




/*
private double tileCount;
    private double energyCount;
    private double woodCount;
    private double copperCount;
    private double bronzeCount;
    private double ironCount;
    private double refinedMetalCount;
    private double preciousMetalCount;
    private double plasticsCount;
    private double uraniumCount;
    private double fuelCount;
    private double adamantiumCount;
    private double exoticMaterialCount;


    public UpgradeCost(double tileCount, double energyCount, double woodCount, double copperCount, double bronzeCount, double ironCount, double refinedMetalCount, double preciousMetalCount, double plasticsCount, double uraniumCount, double fuelCount, double adamantiumCount, double exoticMaterialCount) {
        this.tileCount = tileCount;
        this.energyCount = energyCount;
        this.woodCount = woodCount;
        this.copperCount = copperCount;
        this.bronzeCount = bronzeCount;
        this.ironCount = ironCount;
        this.refinedMetalCount = refinedMetalCount;
        this.preciousMetalCount = preciousMetalCount;
        this.plasticsCount = plasticsCount;
        this.uraniumCount = uraniumCount;
        this.fuelCount = fuelCount;
        this.adamantiumCount = adamantiumCount;
        this.exoticMaterialCount = exoticMaterialCount;
    }

    public double getTileCount() {
        return tileCount;
    }

    public double getEnergyCost() {
        return energyCount;
    }

    public double getWoodCost() {
        return woodCount;
    }

    public double getCopperCost() {
        return copperCount;
    }

    public double getBronzeCost() {
        return bronzeCount;
    }

    public double getIronCost() {
        return ironCount;
    }

    public double getRefinedCost() {
        return refinedMetalCount;
    }

    public double getPreciousMetalCount() {
        return preciousMetalCount;
    }

    public double getPlasticsCost() {
        return plasticsCount;
    }

    public double getUraniumCost() {
        return uraniumCount;
    }

    public double getFuelCost() {
        return fuelCount;
    }

    public double getAdamantiumCount() {
        return adamantiumCount;
    }

    public double getExoticMaterialCount() {
        return exoticMaterialCount;
    }
    */
