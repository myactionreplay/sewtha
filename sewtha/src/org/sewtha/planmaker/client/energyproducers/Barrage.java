package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Barrage extends EnergyProducerOrConsumer {
	// not as ratio - divide by 100 to use
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	private double minUsage = 0;

	private float tidalConvEfficiency = 0.5f;

	public Barrage(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.BARRAGE, index, planHolder);
		this.geog = geog;
		currentUsage = 1;
		calculatedKWHdp = 0.83;
		setIsDecimal(false);
		setAnyEquivalent(true);

	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Barrage";
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
		return minUsage;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006BarrageKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getBarrage2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose what percentage of the Severn Barrage scheme you want to"
				+ " implement.";
	}

	public double getStepSizeForSlider() {
		// severn could be done in 4 different stages
		return 0.25;
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		float totalKWHdp = geog.getPowerOfAllBarageKWHdp()
				* tidalConvEfficiency;
		double totalKWHdpForArea = totalKWHdp * currentUsage;
		calculatedKWHdp = totalKWHdpForArea;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of the barrage project ";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				currentUsage * geog.getAvailableAreaForBarrageSqKm());
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				geog.getBarrage2006Metric()
						* geog.getAvailableAreaForBarrageSqKm());
		return s;
	}

	public String getEquivalentMetricString() {
		return " square kilometres for the Barrage";
	}

}
