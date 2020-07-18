package Data;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class GameData {
    private static boolean textSuffix;
    private static boolean numSuffix;
    private static boolean inBattle;

    private static double energy;
    private static double energyPerSec;
    private static double energyPerPop;
    private static double energySpent;
    private static double population;
    private static double populationPerSec;
    private static double populationPerSecREAL;
    private static double food;
    private static double foodPerSec;
    private static double battle;
    private static double timePenalty;
    private static double tilesCaptured;
    private static double day;
    private static double year;
    private static double captureBonus;

    private static double woodCount;
    private static double woodPerSec;
    private static double copperCount;
    private static double copperPerSec;
    private static double bronzeCount;
    private static double bronzePerSec;
    private static double ironCount;
    private static double ironPerSec;
    private static double refinedMetalCount;
    private static double refinedMetalPerSec;
    private static double plasticsCount;
    private static double plasticsPerSec;
    private static double uraniumCount;
    private static double uraniumPerSec;
    private static double fuelCount;
    private static double fuelPerSec;
    private static double unobtaniumCount;
    private static double unobtaniumPerSec;
    private static double antimatterCount;
    private static double antimatterPerSec;


    private static double energyBonus;
    private static double battleBonus;
    private static double foodBonus;
    private static double productionBonus;


    public boolean isTextSuffix() {
        return textSuffix;
    }

    public void setTextSuffix(boolean textSuffix) {
        GameData.textSuffix = textSuffix;
    }

    public boolean isNumSuffix() {
        return numSuffix;
    }

    public void setNumSuffix(boolean numSuffix) {
        GameData.numSuffix = numSuffix;
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        GameData.inBattle = inBattle;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        GameData.energy = energy;
    }

    public double getEnergyPerSec() {
        return energyPerSec;
    }

    public void setEnergyPerSec(double energyPerSec) {
        GameData.energyPerSec = energyPerSec;
    }

    public double getEnergyPerPop() {
        return energyPerPop;
    }

    public void setEnergyPerPop(double energyPerPop) {
        GameData.energyPerPop = energyPerPop;
    }

    public double getEnergySpent() {
        return energySpent;
    }

    public void setEnergySpent(double energySpent) {
        GameData.energySpent = energySpent;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        GameData.population = population;
    }

    public double getPopulationPerSec() {
        return populationPerSec;
    }

    public void setPopulationPerSec(double populationPerSec) {
        GameData.populationPerSec = populationPerSec;
    }

    public double getPopulationPerSecREAL() {
        return populationPerSecREAL;
    }

    public void setPopulationPerSecREAL(double populationPerSecREAL) {
        GameData.populationPerSecREAL = populationPerSecREAL;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        GameData.food = food;
    }

    public double getFoodPerSec() {
        return foodPerSec;
    }

    public void setFoodPerSec(double foodPerSec) {
        GameData.foodPerSec = foodPerSec;
    }

    public double getBattle() {
        return battle;
    }

    public void setBattle(double battle) {
        GameData.battle = battle;
    }

    public double getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(double timePenalty) {
        GameData.timePenalty = timePenalty;
    }

    public double getTilesCaptured() {
        return tilesCaptured;
    }

    public void setTilesCaptured(double tilesCaptured) {
        GameData.tilesCaptured = tilesCaptured;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        GameData.day = day;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        GameData.year = year;
    }

    public double getCaptureBonus() {
        return captureBonus;
    }

    public void setCaptureBonus(double resourceBonus) {
        GameData.captureBonus = resourceBonus;
    }

    public double getWoodCount() {
        return woodCount;
    }

    public void setWoodCount(double woodCount) {
        GameData.woodCount = woodCount;
    }

    public double getCopperCount() {
        return copperCount;
    }

    public void setCopperCount(double copperCount) {
        GameData.copperCount = copperCount;
    }

    public double getBronzeCount() {
        return bronzeCount;
    }

    public void setBronzeCount(double bronzeCount) {
        GameData.bronzeCount = bronzeCount;
    }

    public double getIronCount() {
        return ironCount;
    }

    public void setIronCount(double ironCount) {
        GameData.ironCount = ironCount;
    }

    public double getRefinedMetalCount() {
        return refinedMetalCount;
    }

    public void setRefinedMetalCount(double refinedMetalCount) {
        GameData.refinedMetalCount = refinedMetalCount;
    }

    public double getPlasticsCount() {
        return plasticsCount;
    }

    public void setPlasticsCount(double plasticsCount) {
        GameData.plasticsCount = plasticsCount;
    }

    public double getUraniumCount() {
        return uraniumCount;
    }

    public void setUraniumCount(double uraniumCount) {
        GameData.uraniumCount = uraniumCount;
    }

    public double getFuelCount() {
        return fuelCount;
    }

    public void setFuelCount(double fuelCount) {
        GameData.fuelCount = fuelCount;
    }

    public double getUnobtaniumCount() {
        return unobtaniumCount;
    }

    public void setUnobtaniumCount(double unobtaniumCount) {
        GameData.unobtaniumCount = unobtaniumCount;
    }

    public double getAntimatterCount() {
        return antimatterCount;
    }

    public void setAntimatterCount(double antimatterCount) {
        GameData.antimatterCount = antimatterCount;
    }

    public double getWoodPerSec() {
        return woodPerSec;
    }

    public void setWoodPerSec(double woodPerSec) {
        GameData.woodPerSec = woodPerSec;
    }

    public double getCopperPerSec() {
        return copperPerSec;
    }

    public void setCopperPerSec(double copperPerSec) {
        GameData.copperPerSec = copperPerSec;
    }

    public double getBronzePerSec() {
        return bronzePerSec;
    }

    public void setBronzePerSec(double bronzePerSec) {
        GameData.bronzePerSec = bronzePerSec;
    }

    public double getIronPerSec() {
        return ironPerSec;
    }

    public void setIronPerSec(double ironPerSec) {
        GameData.ironPerSec = ironPerSec;
    }

    public double getRefinedMetalPerSec() {
        return refinedMetalPerSec;
    }

    public void setRefinedMetalPerSec(double refinedMetalPerSec) {
        GameData.refinedMetalPerSec = refinedMetalPerSec;
    }

    public double getPlasticsPerSec() {
        return plasticsPerSec;
    }

    public void setPlasticsPerSec(double plasticsPerSec) {
        GameData.plasticsPerSec = plasticsPerSec;
    }

    public double getUraniumPerSec() {
        return uraniumPerSec;
    }

    public void setUraniumPerSec(double uraniumPerSec) {
        GameData.uraniumPerSec = uraniumPerSec;
    }

    public double getFuelPerSec() {
        return fuelPerSec;
    }

    public void setFuelPerSec(double fuelPerSec) {
        GameData.fuelPerSec = fuelPerSec;
    }

    public double getUnobtaniumPerSec() {
        return unobtaniumPerSec;
    }

    public void setUnobtaniumPerSec(double unobtaniumPerSec) {
        GameData.unobtaniumPerSec = unobtaniumPerSec;
    }

    public double getAntimatterPerSec() {
        return antimatterPerSec;
    }

    public void setAntimatterPerSec(double antimatterPerSec) {
        GameData.antimatterPerSec = antimatterPerSec;
    }

    public double getEnergyBonus() {
        return energyBonus;
    }

    public void setEnergyBonus(double energyBonus) {
        GameData.energyBonus = energyBonus;
    }

    public double getBattleBonus() {
        return battleBonus;
    }

    public void setBattleBonus(double battleBonus) {
        GameData.battleBonus = battleBonus;
    }

    public double getFoodBonus() {
        return foodBonus;
    }

    public void setFoodBonus(double foodBonus) {
        GameData.foodBonus = foodBonus;
    }

    public double getProductionBonus() {
        return productionBonus;
    }

    public void setProductionBonus(double productionBonus) {
        GameData.productionBonus = productionBonus;
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
