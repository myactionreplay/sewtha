package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Waste extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables:
	private float efficiencyOfConvertingWaste = 0.2f;

	public Waste(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index,
			EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.WASTE, index, planHolder);
		this.geog = geog;
		currentUsage = 2.12;
		calculatedKWHdp = 1.1;
		setAnyEquivalent(true);
	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Waste";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	@Override
	public double getMaxUsage() {
		// doubled maximum from book on advice from DM
		return 4.24;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006WasteKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getWaste2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "Please choose how many kilograms per "
				+ "person of domestic and agricultural waste "
				+ "should be converted into Electricity.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double kwHDP = NaturalConstants.POWER_DENSITY_1KG_WASTE_KWH
				* efficiencyOfConvertingWaste * currentUsage;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " kilograms of domestic and agricultural waste per person ";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				Math.ceil(getKWHdp() * geog.getWastePowerStationsPerKWHdp()));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getNumberFormat().format(
				Math.ceil(geog.get2006WasteKWHdp()
						* geog.getWastePowerStationsPerKWHdp()));
		return s;
	}

	public String getEquivalentMetricString() {
		return " 30MW Waste Incineration Plants ";
	}
}
