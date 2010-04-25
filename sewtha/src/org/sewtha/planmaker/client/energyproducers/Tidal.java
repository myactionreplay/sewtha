package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Tidal extends EnergyProducerOrConsumer {

	// not as ratio - divide by 100 to use
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	private float tidalConvEfficiency = 0.5f;

	public Tidal(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index,
			EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.TIDAL, index, planHolder);
		this.geog = geog;
		currentUsage = 0.149096053892686;
		calculatedKWHdp = 1.36;
		setIsDecimal(false);
		setAnyEquivalent(true);

	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Tidal Power";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 1;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006TidalKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getTidal2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose the percentage of all "
				+ "available Tidal Farms to be implemented.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		float totalKWHdp = geog.getTotalAvailableTidalPowerInKWHdp()
				* tidalConvEfficiency;
		double totalKWHdpForArea = totalKWHdp * (currentUsage);
		calculatedKWHdp = totalKWHdpForArea;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of all available sites ";
	}

	public String getEquivalentMetricString() {
		return " square kilometres for Tidal";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				(geog.getTotalAvailableTidalAreaInSqKm())
						* getCurrentUsageValue());

		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				(geog.getTotalAvailableTidalAreaInSqKm())
						* geog.getTidal2006Metric());
		return s;
	}
}
