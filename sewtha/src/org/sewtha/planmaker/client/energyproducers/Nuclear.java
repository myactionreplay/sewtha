package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Nuclear extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables:
	private float sizeOfNucPowerStationInKm = 1f;

	public Nuclear(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.NUCLEAR, index, planHolder);
		this.geog = geog;
		currentUsage = 39;
		calculatedKWHdp = 16;
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Nuclear Power";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 106;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp(){
		return geog.get2006NuclearKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getNuclear2006Metric();
	}
	
	@Override
	public String getQuestionForChange() {
		return "Please choose the number of Nuclear Power Stations you want to "
				+ "Build and Maintain (there are currently 9).";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double kwHDPperStation = NaturalConstants.NUCLEAR_POWER_PER_KMSQUARED
				* sizeOfNucPowerStationInKm;
		double kwHDP = kwHDPperStation * currentUsage;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " nuclear stations to build and maintain ";
	}
}
