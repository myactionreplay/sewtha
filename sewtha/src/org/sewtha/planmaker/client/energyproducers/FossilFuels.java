package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class FossilFuels extends EnergyProducerOrConsumer {

	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables could change at some point
	private float cleanCoalReduction = 0.73333f;

	public FossilFuels(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		// this is where the country would be picked
		super(group, ProducerOrConsumerEnum.FOSSIL_FUELS, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 1;
		calculatedKWHdp = 0.0;
		setIsDecimal(false);
	}

	public String getDescription() {
		return "Fossil Fuels";
	}

	public double getMinUsage() {
		// as per page 111
		return 0;
	}
	
	public String getPercentageIncreaseStr() {
		String s = getPercentageFormat().format(
				(getCurrentUsageValue() - get2006KWHdpMetric()));
		return s;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return geog.get2006FossilFuelKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getFossilFuel2006Metric();
	}

	public double getMaxUsage() {
		return 1;
	}

	public String getQuestionForChange() {
		return "Please choose the percentage reduction in fossil fuels you wish to implement.";
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
		calculatedKWHdp = geog.get2006FossilFuelKWHdp() - (currentUsage * geog.get2006FossilFuelKWHdp());
		// fire event so everyone knows
		fireEventThroughContainer(this);
	}

	public String getUsageMetricString() {
		return " reduction in the usage of fossil fuels";
	}
	
	/**
	 * We override the method here as fossil fuels are just a balancing
	 * figure with regards to years to implement
	 */
	public void setYearsToImplement(int yearsToImplement){
		this.yearsToImplement = yearsToImplement;

	}

}
