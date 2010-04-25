package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class HydroElectricLowland extends EnergyProducerOrConsumer {

	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	public HydroElectricLowland(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.HYDRO_LOWLAND, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 0.01481481481481;
		calculatedKWHdp = 0.02;
		setIsDecimal(false);
		setAnyEquivalent(true);
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Hydro Electric (Lowland)";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 0.10;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006HydroLowlandKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getHydoLow2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose the percentage of all "
				+ "potential Lowland area to use for HydroElectric.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private double calculateNewKWHdp() {
		double areaUtilised = geog
				.getAvailableAreaForLowlandHydroMetreSquaredPerPerson()
				* (getCurrentUsageValue());
		calculatedKWHdp = (areaUtilised * geog
				.getGravitationalPowerLowlandHydroWattsPerMetreSquared())
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		fireEventThroughContainer();
		return calculatedKWHdp;
	}

	public String getUsageMetricString() {
		return " of all available sites ";
	}

	public String getEquivalentMetricString() {
		return " square kilometres for Lowland Hydro";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getDecimalFormat().format(
				(geog.getAvailableAreaForLowlandHydroMetreSquaredPerPerson()
						* getCurrentUsageValue()) * geog.getPopulation() / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				(geog.getAvailableAreaForLowlandHydroMetreSquaredPerPerson()
						* geog.getHydoLow2006Metric()) * geog.getPopulation() / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED);
		return s;
	}
}
