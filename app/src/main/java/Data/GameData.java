package Data;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class GameData implements Serializable {
    private static boolean textSuffix;
    private static boolean numSuffix;
    private static boolean inBattle;

    private static double energy;
    private static double population;
    private static double food;
    private static double battle;
    private static double battleTimePenalty;
    private static double tilesCaptured;
    private static double day;
    private static double year;
    private static double wood;
    private static double metals;
    private static double refinedMetals;
    private static double uranium;
    private static double adamantium;
    private static double exoticMaterial;


    private static double resourceBonus;
    private static double energyPerSec;
    private static double energyPerPop;
    private static double foodPerSec;
    private static double populationPerSec;
    private static double foodBonus;
    private static double woodPerSec;
    private static double metalsPerSec;
    private static double refinedMetalsPerSec;
    private static double uraniumPerSec;
    private static double adamantiumPerSec;
    private static double exoticMaterialPerSec;

    private static double energyUpgrades;
    private static double battleUpgrades;
    private static double foodUpgrades;


    public boolean isTextSuffix() {
        return textSuffix;
    }

    public void setTextSuffix(boolean textSuffix) {
        this.textSuffix = textSuffix;
    }

    public boolean isNumSuffix() {
        return numSuffix;
    }

    public void setNumSuffix(boolean numSuffix) {
        this.numSuffix = numSuffix;
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getTilesCaptured() {
        return tilesCaptured;
    }

    public void setTilesCaptured(double tilesCaptured) {
        this.tilesCaptured = tilesCaptured;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }


    public double getFoodUpgrades() {
        return foodUpgrades;
    }

    public void setFoodUpgrades(double foodUpgrades) {
        this.foodUpgrades = foodUpgrades;
    }

    public double getBattle() {
        return battle;
    }

    public void setBattle(double battle) {
        this.battle = battle;
    }

    public double getBattleUpgrades() {
        return battleUpgrades;
    }

    public void setBattleUpgrades(double battleUpgrades) {
        this.battleUpgrades = battleUpgrades;
    }

    public double getBattleTimePenalty() {
        return battleTimePenalty;
    }

    public void setBattleTimePenalty(double battleTimePenalty) {
        this.battleTimePenalty = battleTimePenalty;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public double getFoodPerSec() {
        return foodPerSec;
    }

    public void setFoodPerSec(double foodPerSec) {
        this.foodPerSec = foodPerSec;
    }

    public double getFoodBonus() {
        return foodBonus;
    }

    public void setFoodBonus(double gameSpeed) {
        this.foodBonus = gameSpeed;
    }

    public double getWood() {
        return wood;
    }

    public void setWood(double wood) {
        this.wood = wood;
    }

    public double getMetals() {
        return metals;
    }

    public void setMetals(double metals) {
        this.metals = metals;
    }

    public double getRefinedMetals() {
        return refinedMetals;
    }

    public void setRefinedMetals(double refinedMetals) {
        this.refinedMetals = refinedMetals;
    }

    public double getUranium() {
        return uranium;
    }

    public void setUranium(double uranium) {
        this.uranium = uranium;
    }

    public double getAdamantium() {
        return adamantium;
    }

    public void setAdamantium(double adamantium) {
        this.adamantium = adamantium;
    }

    public double getExoticMaterial() {
        return exoticMaterial;
    }

    public void setExoticMaterial(double exoticMaterial) {
        this.exoticMaterial = exoticMaterial;
    }


    public double getResourceBonus() {
        return resourceBonus;
    }

    public void setResourceBonus(double resourceBonus) {
        this.resourceBonus = resourceBonus;
    }

    public double getEnergyPerSec() {
        return energyPerSec;
    }

    public void setEnergyPerSec(double energyPerSec) {
        this.energyPerSec = energyPerSec;
    }

    public double getEnergyPerPop() {
        return energyPerPop;
    }

    public void setEnergyPerPop(double energyPerPop) {
        this.energyPerPop = energyPerPop;
    }

    public double getWoodPerSec() {
        return woodPerSec;
    }

    public void setWoodPerSec(double woodPerSec) {
        this.woodPerSec = woodPerSec;
    }

    public double getPopulationPerSec() {
        return populationPerSec;
    }

    public void setPopulationPerSec(double populationPerSec) {
        this.populationPerSec = populationPerSec;
    }

    public double getMetalsPerSec() {
        return metalsPerSec;
    }

    public void setMetalsPerSec(double metalsPerSec) {
        this.metalsPerSec = metalsPerSec;
    }

    public double getRefinedMetalsPerSec() {
        return refinedMetalsPerSec;
    }

    public void setRefinedMetalsPerSec(double refinedMetalsPerSec) {
        this.refinedMetalsPerSec = refinedMetalsPerSec;
    }

    public double getUraniumPerSec() {
        return uraniumPerSec;
    }

    public void setUraniumPerSec(double uraniumPerSec) {
        this.uraniumPerSec = uraniumPerSec;
    }

    public double getAdamantiumPerSec() {
        return adamantiumPerSec;
    }

    public void setAdamantiumPerSec(double adamantiumPerSec) {
        this.adamantiumPerSec = adamantiumPerSec;
    }

    public double getExoticMaterialPerSec() {
        return exoticMaterialPerSec;
    }

    public void setExoticMaterialPerSec(double exoticMaterialPerSec) {
        this.exoticMaterialPerSec = exoticMaterialPerSec;
    }


    //removes the decimal point from doubles
    public String formatDouble(Double d, int numDec) {
        if (numDec == 0) {
            return String.format("%.0f", d);
        } else if (numDec == 1) {
            return String.format("%.1f", d);
        } else if (numDec == 2) {
            return String.format("%.2f", d);
        } else {
            return String.format("%.0f", d);
        }
    }


    //assylias - SO
    private static final NavigableMap<Double, String> suffixes = new TreeMap<>();

    static {
        String[] unitArray = new String[]{" K", " M", " B", " T", " q", " Q", " s", " S", " O", " N", " Dc", " uD", " dD", " tD", " qD", " QD", " sD", " SD", " oD", " nD", " Vg", " uV", " dV", " tV", " qV", " QV", " sV", " SV", " oV", " nV", " Tg", " uT", " dT", " tT", " qT", " QT", " sT", " ST", " oT", " nT", " qQ", " uqQ", " dqQ", " tqQ", " qqQ", " QqQ", " sqQ", " SqQ", " oqQ", " nqQ", " QQ", " uQQ", " dQQ", " tQQ", " qQQ", " QQQ", " sQQ", " SQQ", " oQQ", " nQQ", " sG", " usG", " dsG", " tsG", " qsG", " QsG", " ssG", " SsG", " osG", " nsG", " SG", " uSG", "dSG", " tSG", " qSG", " QSG", " sSG", " SSG", " oSG", " nSG", " oG", " uoG", " doG", " toG", " qoG", " QoG", " soG", " SoG", " ooG", " noG", " nG", " unG", " dnG", " tnG", " qnG", " QnG", " snG", " SnG", " onG", " nnG"};
        for (int i = 0; i < 100; i++) {
            suffixes.put(Math.pow(10, 3 + i * 3), unitArray[i]);
        }
        suffixes.put(Math.pow(10, 303), " CT");
        suffixes.put(Math.pow(10, 306), " uuCT");
        suffixes.put(Math.pow(10, 308), " ");

//            for (int i = 0; i < 100; i++) {
//                suffixes.put(Math.pow(10, 3 + i * 3), "e" + (3 + i * 3));
//            }


    }

    //assylias - SO
    public String formatSuffix(double value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Double.MIN_VALUE) return formatSuffix(Double.MIN_VALUE + 1);
        if (value < 0) return "-" + formatSuffix(-value);
        if (value < 1000) return formatDouble(value, 0); //deal with easy case

        Map.Entry<Double, String> e = suffixes.floorEntry(value);
        Double divideBy = e.getKey();
        String suffix = e.getValue();

        double truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? formatDouble(truncated / 10d, 1) + suffix : formatDouble(truncated / 10, 1) + suffix;
    }

    //makes the progress bar logarithmic
    public double civScale() {
        return Math.max((Math.log10(getEnergy()) - 6) / 10, 0);
    }




}
