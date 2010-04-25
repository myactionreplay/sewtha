package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Lagoon extends EnergyProducerOrConsumer {
	// not as ratio - divide by 100 to use
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	private float tidalConvEfficiency = 0.5f;
	private float pumpedStorageBoost = 1.25f;

	public Lagoon(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.LAGOONS, index, planHolder);
		this.geog = geog;
		currentUsage = 2;
		calculatedKWHdp = 1.51;
		setAnyEquivalent(true);
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Lagoons";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return geog.getMaxNumberOfPossibleLagoons();
	}

	/**
	 * Min
	 * 
	 */
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006LagoonKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getLagoon2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose the number of "
				+ "400km square Lagoons you wish to develop.";
	}

	public double getStepSizeForSlider() {
		// only 2 lagoons possible
		return 1;
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double totalKWHdp = geog.getPowerOfOneLagoonRegionKWHdp()
				* getCurrentUsageValue();
		double totalKWHdpAfterConvAndBoost = (totalKWHdp * tidalConvEfficiency)
				* pumpedStorageBoost;
		calculatedKWHdp = totalKWHdpAfterConvAndBoost;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of the two available lagoons sites ";
	}

	public String getEquivalentMetricString() {
		return " square kilometres for Tidal";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				(getCurrentUsageValue() * geog.getLagoonKmSquared()));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		// use today current KWDp from pg 111
		String s;
		if (geog.getLagoon2006Metric() <= 0) {
			s = "0";
		} else {
			s = getNumberFormat().format(
					(geog.getLagoon2006Metric() / geog.getLagoonKmSquared()));
		}
		return s;
	}
}
