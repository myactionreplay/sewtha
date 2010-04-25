package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class CleanCoal extends EnergyProducerOrConsumer {

	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables could change at some point
	private float cleanCoalReduction = 0.73333f;

	public CleanCoal(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		// this is where the country would be picked
		super(group, ProducerOrConsumerEnum.CLEAN_COAL, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = -0.6727;
		calculatedKWHdp = 3.0;
		setAnyEquivalent(true);
		setIsDecimal(false);
	}

	public String getDescription() {
		return "Clean Coal";
	}

	public double getMinUsage() {
		// as per page 111
		return -1;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006CleanCoalKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getCleanCoal2006Metric();
	}

	public double getMaxUsage() {
		//updated as advised by DM
		//return 0.8181818181;
		return 1.2;
	}

	public String getQuestionForChange() {
		return "All coal stations will be refitted as Clean "
				+ "Coal stations. Please choose the "
				+ "percentage change in current Coal Stations.";
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
		double currentCapAfterRetroKWHdp = (((geog.getCurrentCoalCapacityGW()* cleanCoalReduction) * NaturalConstants.RATIO_GW_TO_WATTS) / NaturalConstants.CONVERT_WATTS_TO_KWHDP)
				/ geog.getPopulation();
		//double currentCapAfterRetroKWHdp = (currentCapKW * cleanCoalReduction);
		double KWHdp = currentCapAfterRetroKWHdp * ((currentUsage + 1));
		calculatedKWHdp = KWHdp;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " change in the current number of coal stations ";
	}

	public String getEquivalentMetricString() {
		return " coal stations";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				((getCurrentUsageValue() + 1) * (geog
						.getCurrentCoalCapacityGW() / geog
						.getAverageCoalStationGW())));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		// use today current KWDp from pg 111
		String s;
		s = getNumberFormat().format(
				((geog.getCleanCoal2006Metric() + 1) * geog
						.getCurrentCoalCapacityGW())
						/ geog.getAverageCoalStationGW());
		return s;
	}
}
