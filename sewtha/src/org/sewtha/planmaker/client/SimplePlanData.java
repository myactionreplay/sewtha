package org.sewtha.planmaker.client;

import java.io.Serializable;

public class SimplePlanData extends SimplerPlan implements Serializable {

	private double barrage;
	public int getBarrageYears() {
		return barrageYears;
	}

	public void setBarrageYears(int barrageYears) {
		this.barrageYears = barrageYears;
	}

	public int getCleanCoalYears() {
		return cleanCoalYears;
	}

	public void setCleanCoalYears(int cleanCoalYears) {
		this.cleanCoalYears = cleanCoalYears;
	}

	public int getElecLossesYears() {
		return elecLossesYears;
	}

	public void setElecLossesYears(int elecLossesYears) {
		this.elecLossesYears = elecLossesYears;
	}

	public int getElecThingsYears() {
		return elecThingsYears;
	}

	public void setElecThingsYears(int elecThingsYears) {
		this.elecThingsYears = elecThingsYears;
	}

	public int getGeothermalYears() {
		return geothermalYears;
	}

	public void setGeothermalYears(int geothermalYears) {
		this.geothermalYears = geothermalYears;
	}

	public int getHeatingSavingsYears() {
		return heatingSavingsYears;
	}

	public void setHeatingSavingsYears(int heatingSavingsYears) {
		this.heatingSavingsYears = heatingSavingsYears;
	}

	public int getHydroHighYears() {
		return hydroHighYears;
	}

	public void setHydroHighYears(int hydroHighYears) {
		this.hydroHighYears = hydroHighYears;
	}

	public int getHydroLowYears() {
		return hydroLowYears;
	}

	public void setHydroLowYears(int hydroLowYears) {
		this.hydroLowYears = hydroLowYears;
	}

	public int getLagoonsYears() {
		return lagoonsYears;
	}

	public void setLagoonsYears(int lagoonsYears) {
		this.lagoonsYears = lagoonsYears;
	}

	public int getNuclearYears() {
		return nuclearYears;
	}

	public void setNuclearYears(int nuclearYears) {
		this.nuclearYears = nuclearYears;
	}

	public int getOffshoreWindDeepYears() {
		return offshoreWindDeepYears;
	}

	public void setOffshoreWindDeepYears(int offshoreWindDeepYears) {
		this.offshoreWindDeepYears = offshoreWindDeepYears;
	}

	public int getOffshoreWindShallowYears() {
		return offshoreWindShallowYears;
	}

	public void setOffshoreWindShallowYears(int offshoreWindShallowYears) {
		this.offshoreWindShallowYears = offshoreWindShallowYears;
	}

	public int getOnshoreWindYears() {
		return onshoreWindYears;
	}

	public void setOnshoreWindYears(int onshoreWindYears) {
		this.onshoreWindYears = onshoreWindYears;
	}

	public int getPumpedHeatYears() {
		return pumpedHeatYears;
	}

	public void setPumpedHeatYears(int pumpedHeatYears) {
		this.pumpedHeatYears = pumpedHeatYears;
	}

	public int getSolarBiomassYears() {
		return solarBiomassYears;
	}

	public void setSolarBiomassYears(int solarBiomassYears) {
		this.solarBiomassYears = solarBiomassYears;
	}

	public int getSolarDesertYears() {
		return solarDesertYears;
	}

	public void setSolarDesertYears(int solarDesertYears) {
		this.solarDesertYears = solarDesertYears;
	}

	public int getSolarFarmingYears() {
		return solarFarmingYears;
	}

	public void setSolarFarmingYears(int solarFarmingYears) {
		this.solarFarmingYears = solarFarmingYears;
	}

	public int getSolarHeatingYears() {
		return solarHeatingYears;
	}

	public void setSolarHeatingYears(int solarHeatingYears) {
		this.solarHeatingYears = solarHeatingYears;
	}

	public int getSolarPvYears() {
		return solarPvYears;
	}

	public void setSolarPvYears(int solarPvYears) {
		this.solarPvYears = solarPvYears;
	}

	public int getTidalYears() {
		return tidalYears;
	}

	public void setTidalYears(int tidalYears) {
		this.tidalYears = tidalYears;
	}

	public int getTransportSavingsYears() {
		return transportSavingsYears;
	}

	public void setTransportSavingsYears(int transportSavingsYears) {
		this.transportSavingsYears = transportSavingsYears;
	}

	public int getWasteYears() {
		return wasteYears;
	}

	public void setWasteYears(int wasteYears) {
		this.wasteYears = wasteYears;
	}

	public int getWaveYears() {
		return waveYears;
	}

	public void setWaveYears(int waveYears) {
		this.waveYears = waveYears;
	}

	public int getWoodYears() {
		return woodYears;
	}

	public void setWoodYears(int woodYears) {
		this.woodYears = woodYears;
	}

	public int getFossilFuelYears() {
		return fossilFuelYears;
	}

	public void setFossilFuelYears(int fossilFuelYears) {
		this.fossilFuelYears = fossilFuelYears;
	}

	private int barrageYears = 40;
	private double cleanCoal;
	private int cleanCoalYears = 40;
	private double elecLosses;
	private int elecLossesYears = 40;
	private double elecThings;
	private int elecThingsYears = 40;
	private double geothermal;
	private int geothermalYears = 40;
	private double heatingSavings;
	private int heatingSavingsYears = 40;
	private double hydroHigh;
	private int hydroHighYears = 40;
	private double hydroLow;
	private int hydroLowYears = 40;
	private double lagoons;
	private int lagoonsYears = 40;
	private double nuclear;
	private int nuclearYears = 40;
	private double offshoreWindDeep;
	private int offshoreWindDeepYears = 40;
	private double offshoreWindShallow;
	private int offshoreWindShallowYears = 40;
	private double onshoreWind;
	private int onshoreWindYears = 40;
	private double pumpedHeat;
	private int pumpedHeatYears = 40;
	private double solarBiomass;
	private int solarBiomassYears = 40;
	private double solarDesert;
	private int solarDesertYears = 40;
	private double solarFarming;
	private int solarFarmingYears = 40;
	private double solarHeating;
	private int solarHeatingYears = 40;
	private double solarPv;
	private int solarPvYears = 40;
	private double tidal;
	private int tidalYears = 40;
	private double transportSavings;
	private int transportSavingsYears = 40;
	private double waste;
	private int wasteYears = 40;
	private double wave;
	private int waveYears = 40;
	private double wood;
	private int woodYears = 40;
	private double fossilFuel;
	private int fossilFuelYears = 40;
	private boolean isGlobal;
	private String name;
	private String description;
	private long id;
	private String optionalEmail;

	// public String getName(){
	// return name;
	// }
	//	
	// public void setName(String name){
	// this.name = name;
	// }
	//	
	// public long getId(){
	// return id;
	// }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public double getBarrage() {
		return barrage;
	}

	public void setBarrage(double barrage) {
		this.barrage = barrage;
	}

	public double getCleanCoal() {
		return cleanCoal;
	}

	public void setCleanCoal(double cleanCoal) {
		this.cleanCoal = cleanCoal;
	}

	public double getElecLosses() {
		return elecLosses;
	}

	public void setElecLosses(double elecLosses) {
		this.elecLosses = elecLosses;
	}

	public double getElecThings() {
		return elecThings;
	}

	public void setElecThings(double elecThings) {
		this.elecThings = elecThings;
	}

	public double getGeothermal() {
		return geothermal;
	}

	public void setGeothermal(double geothermal) {
		this.geothermal = geothermal;
	}

	public double getHeatingSavings() {
		return heatingSavings;
	}

	public void setHeatingSavings(double heatingSavings) {
		this.heatingSavings = heatingSavings;
	}

	public double getHydroHigh() {
		return hydroHigh;
	}

	public void setHydroHigh(double hydroigh) {
		this.hydroHigh = hydroigh;
	}

	public double getHydroLow() {
		return hydroLow;
	}

	public void setHydroLow(double hydroLow) {
		this.hydroLow = hydroLow;
	}

	public double getLagoons() {
		return lagoons;
	}

	public void setLagoons(double lagoons) {
		this.lagoons = lagoons;
	}

	public double getNuclear() {
		return nuclear;
	}

	public void setNuclear(double nuclear) {
		this.nuclear = nuclear;
	}

	public double getOffshoreWindDeep() {
		return offshoreWindDeep;
	}

	public void setOffshoreWindDeep(double offshoreWindDeep) {
		this.offshoreWindDeep = offshoreWindDeep;
	}

	public double getOffshoreWindShallow() {
		return offshoreWindShallow;
	}

	public void setOffshoreWindShallow(double offshoreWindShallow) {
		this.offshoreWindShallow = offshoreWindShallow;
	}

	public double getOnshoreWind() {
		return onshoreWind;
	}

	public void setOnshoreWind(double onshoreWind) {
		this.onshoreWind = onshoreWind;
	}

	public double getPumpedHeat() {
		return pumpedHeat;
	}

	public void setPumpedHeat(double pumpedHeat) {
		this.pumpedHeat = pumpedHeat;
	}

	public double getSolarBiomass() {
		return solarBiomass;
	}

	public void setSolarBiomass(double solarBiomass) {
		this.solarBiomass = solarBiomass;
	}

	public double getSolarDesert() {
		return solarDesert;
	}

	public void setSolarDesert(double solarDesert) {
		this.solarDesert = solarDesert;
	}

	public double getSolarFarming() {
		return solarFarming;
	}

	public void setSolarFarming(double solarFarming) {
		this.solarFarming = solarFarming;
	}

	public double getSolarHeating() {
		return solarHeating;
	}

	public void setSolarHeating(double solarHeating) {
		this.solarHeating = solarHeating;
	}

	public double getSolarPv() {
		return solarPv;
	}

	public void setSolarPv(double solarPv) {
		this.solarPv = solarPv;
	}

	public double getTidal() {
		return tidal;
	}

	public void setTidal(double tidal) {
		this.tidal = tidal;
	}

	public double getTransportSavings() {
		return transportSavings;
	}

	public void setTransportSavings(double transportSavings) {
		this.transportSavings = transportSavings;
	}

	public double getWaste() {
		return waste;
	}

	public void setWaste(double waste) {
		this.waste = waste;
	}

	public double getWave() {
		return wave;
	}

	public void setWave(double wave) {
		this.wave = wave;
	}

	public double getWood() {
		return wood;
	}

	public void setWood(double wood) {
		this.wood = wood;
	}

	public void setFossilFuel(double fossilFuel) {
		this.fossilFuel = fossilFuel;
	}

	public double getFossilFuel() {
		return fossilFuel;
	}

	public void setOptionalEmail(String optionalEmail) {
		this.optionalEmail = optionalEmail;
	}
	
	public String getOptionalEmail(){
		return optionalEmail;
	}

	public SimplePlanData(String name) {
		super(name, new Long(0), false);
	}

	public SimplePlanData() {
		super("", new Long(0), false);
	}

	public SimplePlanData(boolean isGlobal, double barrage, double cleanCoal,
			double elecLosses, double elecThings, double geothermal,
			double heatingSavings, double hydroHigh, double hydroLow,
			double lagoons, double nuclear, double offshoreWindDeep,
			double offshoreWindShallow, double onshoreWind, double pumpedHeat,
			double solarBiomass, double solarDesert, double solarFarming,
			double solarHeating, double solarPv, double tidal,
			double transportSavings, double waste, double wave, double wood,
			double fossilFuel, int barrageYears, int cleanCoalYears,
			int elecLossesYears, int elecThingsYears, int geothermalYears,
			int heatingSavingsYears, int hydroHighYears, int hydroLowYears,
			int lagoonsYears, int nuclearYears, int offshoreWindDeepYears,
			int offshoreWindShallowYears, int onshoreWindYears, int pumpedHeatYears,
			int solarBiomassYears, int solarDesertYears, int solarFarmingYears,
			int solarHeatingYears, int solarPvYears, int tidalYears,
			int transportSavingsYears, int wasteYears, int waveYears, int woodYears,
			int fossilFuelYears, String name, String description, String optionalEmail, long id) {
		super(name, id, isGlobal);
		// have had to add isGlobal to super class so could remove from this if
		// we want
		this.isGlobal = isGlobal;
		this.barrage = barrage;
		this.cleanCoal = cleanCoal;
		this.elecLosses = elecLosses;
		this.elecThings = elecThings;
		this.geothermal = geothermal;
		this.heatingSavings = heatingSavings;
		this.hydroHigh = hydroHigh;
		this.hydroLow = hydroLow;
		this.lagoons = lagoons;
		this.nuclear = nuclear;
		this.offshoreWindDeep = offshoreWindDeep;
		this.offshoreWindShallow = offshoreWindShallow;
		this.onshoreWind = onshoreWind;
		this.pumpedHeat = pumpedHeat;
		this.solarBiomass = solarBiomass;
		this.solarDesert = solarDesert;
		this.solarFarming = solarFarming;
		this.solarHeating = solarHeating;
		this.solarPv = solarPv;
		this.tidal = tidal;
		this.transportSavings = transportSavings;
		this.waste = waste;
		this.wave = wave;
		this.wood = wood;
		this.fossilFuel = fossilFuel;
		this.description = description;
		this.optionalEmail = optionalEmail;
		this.barrageYears = barrageYears;
		this.cleanCoalYears = cleanCoalYears;
		this.elecLossesYears = elecLossesYears;
		this.elecThingsYears = elecThingsYears;
		this.geothermalYears = geothermalYears;
		this.heatingSavingsYears = heatingSavingsYears;
		this.hydroHighYears = hydroHighYears;
		this.hydroLowYears = hydroLowYears;
		this.lagoonsYears = lagoonsYears;
		this.nuclearYears = nuclearYears;
		this.offshoreWindDeepYears = offshoreWindDeepYears;
		this.offshoreWindShallowYears = offshoreWindShallowYears;
		this.onshoreWindYears = onshoreWindYears;
		this.pumpedHeatYears = pumpedHeatYears;
		this.solarBiomassYears = solarBiomassYears;
		this.solarDesertYears = solarDesertYears;
		this.solarFarmingYears = solarFarmingYears;
		this.solarHeatingYears = solarHeatingYears;
		this.solarPvYears = solarPvYears;
		this.tidalYears = tidalYears;
		this.transportSavingsYears = transportSavingsYears;
		this.wasteYears = wasteYears;
		this.waveYears = waveYears;
		this.woodYears = woodYears;
		this.fossilFuelYears = fossilFuelYears;
		// this.name = name;
		// this.id = id;
	}

}
