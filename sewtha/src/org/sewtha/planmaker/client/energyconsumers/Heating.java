package org.sewtha.planmaker.client.energyconsumers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Heating extends EnergyProducerOrConsumer {
	// not as ratio - divide by 100 to use
	private double currentHeatingUsageKWHdp;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	private double heatingEfficiencySaving = 0.25;

	public Heating(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.HEATING_SAVINGS, index, planHolder);
		this.geog = geog;
		currentHeatingUsageKWHdp = geog.getCurrentHeatingUsageKWHdp();
		//set as per middle of the road
		calculatedKWHdp = currentHeatingUsageKWHdp
				* (1 - heatingEfficiencySaving);
		setIsDecimal(false);

	}

	@Override
	public double getCurrentUsageValue() {
		return heatingEfficiencySaving;
	}

	@Override
	public String getDescription() {
		return "Heating";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp(){
		return geog.get2006HeatingkWhd();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getHeating2006Metric();
	}
	/**
	 * This will actually be the maximum usage of the efficiency - ie 100%
	 * 
	 * @return
	 */
	public double getMaxUsage() {
		return 1;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	@Override
	public String getQuestionForChange() {
		return "please choose the percentage of heating efficiency measures"
				+ " you plan to implement.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		heatingEfficiencySaving = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		calculatedKWHdp = currentHeatingUsageKWHdp
				* (1 - heatingEfficiencySaving);
		// fire event so everyone knows
		fireEventThroughContainer();
	}
	
	public String getUsageMetricString(){
		return " of savings ";
	}
}
