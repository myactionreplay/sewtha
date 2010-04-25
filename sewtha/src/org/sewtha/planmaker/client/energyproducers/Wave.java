package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Wave extends EnergyProducerOrConsumer {

	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables:
	private float waveMachineEfficiency = .5f;

	public Wave(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index,
			EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.WAVE, index, planHolder);
		this.geog = geog;
		currentUsage = 0.036;
		calculatedKWHdp = 0.3;
		setIsDecimal(false);
		setAnyEquivalent(true);
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Wave Power";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 0.50;
	}

	public double get2006KWHdp() {
		return geog.get2006WaveKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.get2006WaveKWHdp();
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	@Override
	public String getQuestionForChange() {
		return "Please use the slider to choose the percentage of all available "
				+ "coastline to use for Wave energy.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double potentialAreaUsedMetres = (geog.getAvailableCoastlineKm() * 1000);
		double watts = potentialAreaUsedMetres
				* NaturalConstants.POTENTIAL_WAVEPOWER_KW
				* NaturalConstants.RATIO_KW_TO_WATTS;
		double maxkwHDP = watts / geog.getPopulation()
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		double kwHDP = maxkwHDP * (currentUsage) * this.waveMachineEfficiency;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of all available coastline";
	}

	public String getEquivalentMetricString() {
		return " miles of coastline";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat()
				.format(
						(geog.getAvailableCoastlineForWaveKm() * getCurrentUsageValue()));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		// use today current KWDp from pg 111
		String s;
		if (geog.getWave2006Metric() <= 0) {
			s = "0";
		} else {
			s = getNumberFormat().format(
					(geog.getAvailableCoastlineForWaveKm() / geog
							.getWave2006Metric()));

		}
		return s;
	}
}
