package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class SolarDesert extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables:
	private float solarDesertWattsPerMetreSquared = 15f;

	public SolarDesert(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.SOLAR_DESERTS, index, planHolder);
		this.geog = geog;
		currentUsage = 2560;
		calculatedKWHdp = 16;
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Solar Desert";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 3200;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111 min usage in 2006
	 */
	public double get2006KWHdp(){
		return geog.get2006SolarDesertKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getSolarDesert2006Metric();
	}
	
	@Override
	public String getQuestionForChange() {
		return "Please choose how many kilometres squared "
				+ "should be used for overseas Solar Deserts.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double areaUsedInMetres = currentUsage
				* NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED;
		double wattsProduced = areaUsedInMetres
				* solarDesertWattsPerMetreSquared;
		double kwHDP = wattsProduced / geog.getPopulation()
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " square kilometres for Solar Deserts";
	}
	
}
