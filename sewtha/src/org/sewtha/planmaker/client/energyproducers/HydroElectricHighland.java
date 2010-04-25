package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class HydroElectricHighland extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	public HydroElectricHighland(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.HYDRO_HIGHLAND, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 0.0230769230769231;
		calculatedKWHdp = 0.18;
		setIsDecimal(false);
		setAnyEquivalent(true);
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Hydro Electric (Highland)";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 0.20;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}
	
	/**
	 * From pg 111
	 */
	public double get2006KWHdp(){
		return geog.get2006HydroHighlandKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getHydroHigh2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose the percentage of all "
				+ "potential Highland area to use for HydroElectric.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private double calculateNewKWHdp() {
		double areaUtilised = geog
				.getAvailableAreaForHighlandHydroMetreSquaredPerPerson()
				* (getCurrentUsageValue());
		calculatedKWHdp = (areaUtilised * geog
				.getGravitationalPowerHighlandHydroWattsPerMetreSquared())
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		fireEventThroughContainer();
		return calculatedKWHdp;
	}
	
	public String getUsageMetricString(){
		return " of all potential highland sites ";
	}
	public String getEquivalentMetricString() {
		return " square kilometres for Highland Hydro";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getDecimalFormat().format(
				(geog.getAvailableAreaForHighlandHydroMetreSquaredPerPerson()
						* getCurrentUsageValue()) * geog.getPopulation() / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				(geog.getAvailableAreaForHighlandHydroMetreSquaredPerPerson()
						* geog.getHydroHigh2006Metric()) * geog.getPopulation() / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}
	
}
