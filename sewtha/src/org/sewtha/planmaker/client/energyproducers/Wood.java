package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Wood extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables:
	private float efficiencyOfConvertingWood = 0.7f;

	public Wood(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index,
			EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.WOOD, index, planHolder);
		this.geog = geog;
		currentUsage = 510.20;
		calculatedKWHdp = 5;
		setAnyEquivalent(true);
	}

	/**
	 * as per page 111
	 */
	public double get2006KWHdp() {
		return geog.get2006WoodKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getWood2006Metric();
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Wood";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		return 1000;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose the square metres per "
				+ "person to grow wood to use for for heating.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double kwHDP = NaturalConstants.POWER_DENSITY_WOOD_METRE_SQUARED
				* efficiencyOfConvertingWood * currentUsage;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of square metres per person ";
	}

	public String getEquivalentMetricString() {
		return " squared kilometres for Managed Wood Farms";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat()
				.format(
						((currentUsage * geog.getPopulation()) / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat()
				.format(
						((geog.getWood2006Metric() * geog.getPopulation()) / NaturalConstants.RATIO_KM_SQUARED_TO_METRE_SQUARED));
		return s;
	}
}
