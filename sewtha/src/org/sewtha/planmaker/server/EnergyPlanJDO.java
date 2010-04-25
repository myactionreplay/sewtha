package org.sewtha.planmaker.server;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EnergyPlanJDO implements Serializable {

	private static final Logger LOG = Logger.getLogger(EnergyPlanJDO.class
			.getName());

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name = "No Name";
	@Persistent
	private String description;
	@Persistent
	User user;
	@Persistent
	private boolean isGlobalPlan;
	@Persistent
	private Double barrage;
	@Persistent
	private Double cleanCoal;
	@Persistent
	private Double elecLosses;
	@Persistent
	private Double elecThings;
	@Persistent
	private Double geothermal;
	@Persistent
	private Double heatingSavings;
	@Persistent
	private Double hydroHigh;
	@Persistent
	private Double hydroLow;
	@Persistent
	private Double lagoons;
	@Persistent
	private Double nuclear;
	@Persistent
	private Double offshoreWindDeep;
	@Persistent
	private Double offshoreWindShallow;
	@Persistent
	private Double onshoreWind;
	@Persistent
	private Double pumpedHeat;
	@Persistent
	private Double solarBiomass;
	@Persistent
	private Double solarDesert;
	@Persistent
	private Double solarFarming;
	@Persistent
	private Double solarHeating;
	@Persistent
	private Double solarPv;
	@Persistent
	private Double tidal;
	@Persistent
	private Double transportSavings;
	@Persistent
	private Double waste;
	@Persistent
	private Double wave;
	@Persistent
	private Double wood;
	@Persistent
	private Double fossilFuel;
	@Persistent
	private String optionalEmail;
	@Persistent
	private Integer barrageYears = 40;
	@Persistent
	private Integer cleanCoalYears = 40;
	@Persistent
	private Integer elecLossesYears = 40;
	@Persistent
	private Integer elecThingsYears = 40;
	@Persistent
	private Integer geothermalYears = 40;
	@Persistent
	private Integer heatingSavingsYears = 40;
	@Persistent
	private Integer hydroHighYears = 40;
	@Persistent
	private Integer hydroLowYears = 40;
	@Persistent
	private Integer lagoonsYears = 40;
	@Persistent
	private Integer nuclearYears = 40;
	@Persistent
	private Integer offshoreWindDeepYears = 40;
	@Persistent
	private Integer offshoreWindShallowYears = 40;
	@Persistent
	private Integer onshoreWindYears = 40;
	@Persistent
	private Integer pumpedHeatYears = 40;
	@Persistent
	private Integer solarBiomassYears = 40;
	@Persistent
	private Integer solarDesertYears = 40;
	@Persistent
	private Integer solarFarmingYears = 40;
	@Persistent
	private Integer solarHeatingYears = 40;
	@Persistent
	private Integer solarPvYears = 40;
	@Persistent
	private Integer tidalYears = 40;
	@Persistent
	private Integer transportSavingsYears = 40;
	@Persistent
	private Integer wasteYears = 40;
	@Persistent
	private Integer waveYears = 40;
	@Persistent
	private Integer woodYears = 40;
	@Persistent
	private Integer fossilFuelYears = 40;

	public EnergyPlanJDO(User user, String name, boolean isGlobal,
			double barrage, double cleanCoal, double elecLosses,
			double elecThings, double geothermal, double heatingSavings,
			double hydroHigh, double hydroLow, double lagoons, double nuclear,
			double offshoreWindDeep, double offshoreWindShallow,
			double onshoreWind, double pumpedHeat, double solarBiomass,
			double solarDesert, double solarFarming, double solarHeating,
			double solarPv, double tidal, double transportSavings,
			double waste, double wave, double wood, double fossilFuel,
			int barrageYears, int cleanCoalYears, int elecLossesYears,
			int elecThingsYears, int geothermalYears, int heatingSavingsYears,
			int hydroHighYears, int hydroLowYears, int lagoonsYears,
			int nuclearYears, int offshoreWindDeepYears,
			int offshoreWindShallowYears, int onshoreWindYears,
			int pumpedHeatYears, int solarBiomassYears, int solarDesertYears,
			int solarFarmingYears, int solarHeatingYears, int solarPvYears,
			int tidalYears, int transportSavingsYears, int wasteYears,
			int waveYears, int woodYears, int fossilFuelYears,
			String optionalEmail, String description) {

		LOG.info("Values as follows" + barrageYears + cleanCoalYears
				+ elecLossesYears + elecThingsYears + geothermalYears
				+ heatingSavingsYears + hydroHighYears + hydroLowYears
				+ lagoonsYears + nuclearYears + offshoreWindDeepYears
				+ offshoreWindShallowYears + onshoreWindYears + pumpedHeatYears
				+ solarBiomassYears + solarDesertYears + solarFarmingYears
				+ solarHeatingYears + solarPvYears + tidalYears
				+ transportSavingsYears + wasteYears + waveYears + woodYears
				+ fossilFuelYears);
		this.user = user;
		this.name = name;
		this.isGlobalPlan = isGlobal;
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
		this.optionalEmail = optionalEmail;
		this.description = description;
	}

	private int checkForNullAndReplace(Integer y){
		int r = 40;
		try {
			r = y.intValue();
		} catch (NullPointerException npe){
			r = 40;
		}
		return r;
	}
	
	public int getBarrageYears() {
		return checkForNullAndReplace(barrageYears);
	}

	public void setBarrageYears(int barrageYears) {
		this.barrageYears = barrageYears;
	}

	public int getCleanCoalYears() {
		return checkForNullAndReplace(cleanCoalYears);
	}

	public void setCleanCoalYears(int cleanCoalYears) {
		this.cleanCoalYears = cleanCoalYears;
	}

	public int getElecLossesYears() {
		return checkForNullAndReplace(elecLossesYears);
	}

	public void setElecLossesYears(int elecLossesYears) {
		this.elecLossesYears = elecLossesYears;
	}

	public int getElecThingsYears() {
		return checkForNullAndReplace(elecThingsYears);
	}

	public void setElecThingsYears(int elecThingsYears) {
		this.elecThingsYears = elecThingsYears;
	}

	public int getGeothermalYears() {
		return checkForNullAndReplace(geothermalYears);
	}

	public void setGeothermalYears(int geothermalYears) {
		this.geothermalYears = geothermalYears;
	}

	public int getHeatingSavingsYears() {
		return checkForNullAndReplace(heatingSavingsYears);
	}

	public void setHeatingSavingsYears(int heatingSavingsYears) {
		this.heatingSavingsYears = heatingSavingsYears;
	}

	public int getHydroHighYears() {
		return checkForNullAndReplace(hydroHighYears);
	}

	public void setHydroHighYears(int hydroHighYears) {
		this.hydroHighYears = hydroHighYears;
	}

	public int getHydroLowYears() {
		return checkForNullAndReplace(hydroLowYears);
	}

	public void setHydroLowYears(int hydroLowYears) {
		this.hydroLowYears = hydroLowYears;
	}

	public int getLagoonsYears() {
		return checkForNullAndReplace(lagoonsYears);
	}

	public void setLagoonsYears(int lagoonsYears) {
		this.lagoonsYears = lagoonsYears;
	}

	public int getNuclearYears() {
		return checkForNullAndReplace(nuclearYears);
	}

	public void setNuclearYears(int nuclearYears) {
		this.nuclearYears = nuclearYears;
	}

	public int getOffshoreWindDeepYears() {
		return checkForNullAndReplace(offshoreWindDeepYears);
	}

	public void setOffshoreWindDeepYears(int offshoreWindDeepYears) {
		this.offshoreWindDeepYears = offshoreWindDeepYears;
	}

	public int getOffshoreWindShallowYears() {
		return checkForNullAndReplace(offshoreWindShallowYears);
	}

	public void setOffshoreWindShallowYears(int offshoreWindShallowYears) {
		this.offshoreWindShallowYears = offshoreWindShallowYears;
	}

	public int getOnshoreWindYears() {
		return checkForNullAndReplace(onshoreWindYears);
	}

	public void setOnshoreWindYears(int onshoreWindYears) {
		this.onshoreWindYears = onshoreWindYears;
	}

	public int getPumpedHeatYears() {
		return checkForNullAndReplace(pumpedHeatYears);
	}

	public void setPumpedHeatYears(int pumpedHeatYears) {
		this.pumpedHeatYears = pumpedHeatYears;
	}

	public int getSolarBiomassYears() {
		return checkForNullAndReplace(solarBiomassYears);
	}

	public void setSolarBiomassYears(int solarBiomassYears) {
		this.solarBiomassYears = solarBiomassYears;
	}

	public int getSolarDesertYears() {
		return checkForNullAndReplace(solarDesertYears);
	}

	public void setSolarDesertYears(int solarDesertYears) {
		this.solarDesertYears = solarDesertYears;
	}

	public int getSolarFarmingYears() {
		return checkForNullAndReplace(solarFarmingYears);
	}

	public void setSolarFarmingYears(int solarFarmingYears) {
		this.solarFarmingYears = solarFarmingYears;
	}

	public int getSolarHeatingYears() {
		return checkForNullAndReplace(solarHeatingYears);
	}

	public void setSolarHeatingYears(int solarHeatingYears) {
		this.solarHeatingYears = solarHeatingYears;
	}

	public int getSolarPvYears() {
		return checkForNullAndReplace(solarPvYears);
	}

	public void setSolarPvYears(int solarPvYears) {
		this.solarPvYears = solarPvYears;
	}

	public int getTidalYears() {
		return checkForNullAndReplace(tidalYears);
	}

	public void setTidalYears(int tidalYears) {
		this.tidalYears = tidalYears;
	}

	public int getTransportSavingsYears() {
		return checkForNullAndReplace(transportSavingsYears);
	}

	public void setTransportSavingsYears(int transportSavingsYears) {
		this.transportSavingsYears = transportSavingsYears;
	}

	public int getWasteYears() {
		return checkForNullAndReplace(wasteYears);
	}

	public void setWasteYears(int wasteYears) {
		this.wasteYears = wasteYears;
	}

	public int getWaveYears() {
		return checkForNullAndReplace(waveYears);
	}

	public void setWaveYears(int waveYears) {
		this.waveYears = waveYears;
	}

	public int getWoodYears() {
		return checkForNullAndReplace(woodYears);
	}

	public void setWoodYears(int woodYears) {
		this.woodYears = woodYears;
	}

	public int getFossilFuelYears() {
		return checkForNullAndReplace(fossilFuelYears);
	}

	public void setFossilFuelYears(int fossilFuelYears) {
		this.fossilFuelYears = fossilFuelYears;
	}

	public EnergyPlanJDO() {
	}

	public boolean isGlobalPlan() {
		return isGlobalPlan;
	}

	public void setGlobalPlan(boolean isGlobalPlan) {
		this.isGlobalPlan = isGlobalPlan;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public double getFossilFuel() {
		return fossilFuel;
	}

	public void setFossilFuel(double fossilFuel) {
		this.fossilFuel = fossilFuel;
	}

	public String getOptionalEmail() {
		return optionalEmail;
	}

	public void setOptionalEmail() {
		this.optionalEmail = optionalEmail;
	}

}
