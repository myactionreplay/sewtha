package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class ShallowOffshore extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables could change at some point
	private double windTurbineEfficiency = 0.50;
	private double windTurbineDiameterMetres = 25;
	private float ratioIncreaseInPowerOverOnshore = 1.5f;

	public ShallowOffshore(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		// this is where the country would be picked
		super(group, ProducerOrConsumerEnum.OFFSHORE_WIND_SHALLOW, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 0.0362746309041357;
		calculatedKWHdp = 2.0;
		setIsDecimal(false);
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Offshore Wind Farms (Shallow)";
	}

	/**
	 * Could be reduced
	 */
	public double getMinUsage() {
	return 0;
	}
	
	/**
	 * as per page 111
	 */
	public double get2006KWHdp(){
		return geog.get2006ShallowOffWindKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getShallowWind2006Metric();
	}

	public double getMaxUsage() {
		return 0.33;
	}

	public String getQuestionForChange() {
		return "Please choose the percentage of all available seabed to use for Shallow Offshore Wind Farms.";
	}

	public double getKWHdp() {
		return calculatedKWHdp;
	}

	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	public double getCurrentUsageValue() {
		return currentUsage;
	}

	private void calculateNewKWHdp() {
		// as per page 264 and 265 of sewtha
		// calcualte the kinetic energy
		double kineticEnergy = ((NaturalConstants.DENSITY_OF_WIND_KG_PER_METRE_CUBED * (Math
				.pow(geog.getWindspeedMetresPerSecond(), 3))) * 0.5)
				* windTurbineEfficiency;
		// Consider the wind turbine diameter
		double incDiameter = kineticEnergy * Math.PI / 4
				* Math.pow(windTurbineDiameterMetres, 2);
		//incSPacing is the power per unit area w/m squared
		double incSpacing = incDiameter
				/ Math.pow(windTurbineDiameterMetres * 5, 2);
		incSpacing *= ratioIncreaseInPowerOverOnshore;
		double offshoreArea = geog.getAvailableAreaForShallowOffshoreKmSquared();
		double ratioToKm = NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED;
		//this is strange - this equation does not work below
		double t = geog.getAvailableAreaForShallowOffshoreKmSquared() * NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED;
		double totalMetreSquared = offshoreArea * ratioToKm;
		double metresSquared = (getCurrentUsageValue())
				* totalMetreSquared;
		double watts = ((incSpacing * metresSquared)/geog.getPopulation());
		double kwHDP = watts / NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}
	public String getUsageMetricString(){
		return " of all available seabed ";
	}
	
	public String getEquivalentMetricString() {
		return " wind turbines";
	}
	
	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				(getKWHdp() / geog.getWindTurbineKWhdp()));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		// use today current KWDp from pg 111
		String s = getNumberFormat().format(
				(geog.get2006ShallowOffWindKWHdp() / geog.getWindTurbineKWhdp()));
		return s;
	}
	
	public String getColor(){
		return "#7BBA43";
	}
}
