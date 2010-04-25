package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class SolarPVFarm extends SolarParent {
	// set them as per
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	private double solarFarmingPlantEfficiency = 0.1;

	public SolarPVFarm(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.SOLAR_FARMING, index, planHolder);
		this.geog = geog;
		// set them to the middle road plan
		currentUsage = 0.0;
		calculatedKWHdp = 0.0;
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Solar Farming";
	}

	/**
	 * From pg 111 min usage in 2006
	 */
	public double get2006KWHdp(){
		return geog.get2006SolarPVKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getSolarPV2006Metric();
	}
	/**
	 * Could be reduced to 0
	 * 
	 * @return
	 */
	public double getMinUsage() {
		return 0.0000;
	}

	/**
	 * From Maximal plan
	 * 
	 * @return
	 */
	public double getMaxUsage() {
		return 200;
	}

	public String getQuestionForChange() {
		return "Please choose how many square metres "
				+ "for each person should be utilised for "
				+ "Solar Farming in the countryside.";
	}

	public double getKWHdp() {
		return calculatedKWHdp;
	}

	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double watts = getSunshinePowerWattsPerMetreSquared() * currentUsage
				* solarFarmingPlantEfficiency;
		calculatedKWHdp = watts / NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		fireEventThroughContainer();
	}

	public double getCurrentUsageValue() {
		return currentUsage;
	}

	public String getUsageMetricString() {
		return " of square metres for each person ";
	}
	
	public String getEquivalentMetricString() {
		return " one metre square Solar Farm Panels";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat()
				.format(currentUsage * geog.getPopulation());
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getDecimalFormat().format(geog.get2006SolarPVKWHdp() * geog.getPopulation());
		return s;
	}
}
