package UpgradeTree;

public class UpgradeObject {
    private UpgradeObject parentUpgrade;
    private String upgradeName;
    private UpgradeCost upgradeCost;
    private boolean purchased;

    public UpgradeObject(UpgradeObject parentUpgrade, String upgradeName, boolean purchased, UpgradeCost upgradeCost) {
        this.parentUpgrade = parentUpgrade;
        this.upgradeName = upgradeName;
        this.purchased = purchased;
        this.upgradeCost = upgradeCost;

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
        return new double[]{upgradeCost.getEnergyCount(), upgradeCost.getWoodCount(), upgradeCost.getCopperCount(),
                upgradeCost.getBronzeCount(), upgradeCost.getIronCount(), upgradeCost.getRefinedMetalCount(),
                upgradeCost.getPreciousMetalCount(), upgradeCost.getPlasticsCount(), upgradeCost.getUraniumCount(),
                upgradeCost.getFuelCount(), upgradeCost.getAdamantiumCount(), upgradeCost.getExoticMaterialCount()};
    }


}
