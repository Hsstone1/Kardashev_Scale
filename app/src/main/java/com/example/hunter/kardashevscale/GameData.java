package com.example.hunter.kardashevscale;

import android.widget.ProgressBar;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static com.example.hunter.kardashevscale.MainActivity.ENERGY_TO_CIV_1;

public class GameData {
    private double energy;
    private double population;
    private double logisticPopulation;
    private double tilesCaptured;
    private double day;
    private double year;
    private double wood;
    private double metals;
    private double refinedMetals;
    private double uranium;
    private double adamantium;
    private double exoticMaterial;

    private double energyPerSec;
    private double energyPerPop;
    private double populationPerSec;
    private double gameSpeed;
    private double woodPerSec;
    private double metalsPerSec;
    private double refinedMetalsPerSec;
    private double uraniumPerSec;
    private double adamantiumPerSec;
    private double exoticMaterialPerSec;


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

    public double getLogisticPopulation() {
        return logisticPopulation;
    }

    public void setLogisticPopulation(double logisticPopulation) {
        this.logisticPopulation = logisticPopulation;
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

    public double getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
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
    public String formatDouble(Double d) {
        return String.format("%.0f", d);
    }

    public String formatDoubleOne(Double d) {
        return String.format("%.1f", d);
    }


    //assylias - SO
    private static final NavigableMap<Double, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(Math.pow(10, 3), " K");
        suffixes.put(Math.pow(10, 6), " M");
        suffixes.put(Math.pow(10, 9), " B");
        suffixes.put(Math.pow(10, 12), " T");
        suffixes.put(Math.pow(10, 15), " q");
        suffixes.put(Math.pow(10, 18), " Q");
        suffixes.put(Math.pow(10, 21), " s");
        suffixes.put(Math.pow(10, 24), " S");
        suffixes.put(Math.pow(10, 27), " O");
        suffixes.put(Math.pow(10, 30), " N");
        suffixes.put(Math.pow(10, 33), " Dc");
        suffixes.put(Math.pow(10, 36), " uD");
        suffixes.put(Math.pow(10, 39), " dD");
        suffixes.put(Math.pow(10, 42), " tD");
        suffixes.put(Math.pow(10, 45), " qD");
        suffixes.put(Math.pow(10, 48), " QD");
        suffixes.put(Math.pow(10, 51), " sD");
        suffixes.put(Math.pow(10, 54), " SD");
        suffixes.put(Math.pow(10, 57), " oD");
        suffixes.put(Math.pow(10, 60), " nD");
        suffixes.put(Math.pow(10, 63), " Vg");
        suffixes.put(Math.pow(10, 66), " uV");
        suffixes.put(Math.pow(10, 69), " dV");
        suffixes.put(Math.pow(10, 72), " tV");
        suffixes.put(Math.pow(10, 75), " qV");
        suffixes.put(Math.pow(10, 78), " QV");
        suffixes.put(Math.pow(10, 81), " sV");
        suffixes.put(Math.pow(10, 84), " SV");
        suffixes.put(Math.pow(10, 87), " oV");
        suffixes.put(Math.pow(10, 90), " nV");
        suffixes.put(Math.pow(10, 93), " Tg");
        suffixes.put(Math.pow(10, 96), " uT");
        suffixes.put(Math.pow(10, 99), " dT");
    }

    //assylias - SO
    public String formatSuffix(double value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Double.MIN_VALUE) return formatSuffix(Double.MIN_VALUE + 1);
        if (value < 0) return "-" + formatSuffix(-value);
        if (value < 1000) return formatDouble(value); //deal with easy case

        Map.Entry<Double, String> e = suffixes.floorEntry(value);
        Double divideBy = e.getKey();
        String suffix = e.getValue();

        double truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? formatDoubleOne(truncated / 10d) + suffix : formatDoubleOne(truncated / 10) + suffix;
    }

    //makes the progress bar logarithmic
    public int logProgress(double maxEnergy, double minEnergy) {
        return (int) Math.sqrt(((getEnergy() - minEnergy) / (maxEnergy - minEnergy)) * 10000);
    }


}
