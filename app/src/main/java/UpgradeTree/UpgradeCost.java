package UpgradeTree;

public class UpgradeCost {

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


    public UpgradeCost(double energyCount, double woodCount, double copperCount, double bronzeCount, double ironCount, double refinedMetalCount, double preciousMetalCount, double plasticsCount, double uraniumCount, double fuelCount, double adamantiumCount, double exoticMaterialCount) {
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

    public double getEnergyCount() {
        return energyCount;
    }

    public double getWoodCount() {
        return woodCount;
    }

    public double getCopperCount() {
        return copperCount;
    }

    public double getBronzeCount() {
        return bronzeCount;
    }

    public double getIronCount() {
        return ironCount;
    }

    public double getRefinedMetalCount() {
        return refinedMetalCount;
    }

    public double getPreciousMetalCount() {
        return preciousMetalCount;
    }

    public double getPlasticsCount() {
        return plasticsCount;
    }

    public double getUraniumCount() {
        return uraniumCount;
    }

    public double getFuelCount() {
        return fuelCount;
    }

    public double getAdamantiumCount() {
        return adamantiumCount;
    }

    public double getExoticMaterialCount() {
        return exoticMaterialCount;
    }
}
