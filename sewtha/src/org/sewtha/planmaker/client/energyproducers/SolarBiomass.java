package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class SolarBiomass extends SolarParent {
	// set them as per
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	private double plantConversionEfficiency = 0.005;
	private double solarBiomassElecConvLoss = 0.3333333;

	public SolarBiomass(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.SOLAR_BIOMASS, index, planHolder);
		this.geog = geog;
		// set them to the middle road plan
		currentUsage = 238.806;
		calculatedKWHdp = 2.0;
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Solar Biomass";
	}

	/**
	 * From pg 111 min usage in 2006
	 * 
	 * @return
	 */
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111 min usage in 2006 - biodiesel equivalent to 15.5223881m pp
	 */
	public double get2006KWHdp(){
		return geog.get2006BiomassKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getSolarBiomass2006Metric();
	}
	/**
	 * From Maximal plan
	 * 
	 * @return
	 */
	public double getMaxUsage() {
		return 3000;
	}

	public String getQuestionForChange() {
		return "Please choose how many " + "square metres for each person "
				+ "should be utilised for Bio Fuels.";
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
				* plantConversionEfficiency;
		double calculatedKWHdpBeforeConvLoss = watts
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		calculatedKWHdp = calculatedKWHdpBeforeConvLoss
				- (calculatedKWHdpBeforeConvLoss * solarBiomassElecConvLoss);
		fireEventThroughContainer();
	}

	public double getCurrentUsageValue() {
		return currentUsage;
	}

	public String getUsageMetricString() {
		return " of square metres for each person ";
	}

	public String getEquivalentMetricString() {
		return " square kilometres of Biomass plants";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				(currentUsage * geog.getPopulation())
						/ NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				(get2006KWHdpMetric() * geog.getPopulation())
						/ NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}
}
