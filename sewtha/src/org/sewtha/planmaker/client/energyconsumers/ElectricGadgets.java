package org.sewtha.planmaker.client.energyconsumers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class ElectricGadgets extends EnergyProducerOrConsumer{
	// not as ratio - divide by 100 to use
	private double currentElecticGadgetUsageKWHdp;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	private double elecGadgetEfficiencySaving = 0.0;

	public ElectricGadgets(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.ELECTHINGS, index, planHolder);
		this.geog = geog;
		currentElecticGadgetUsageKWHdp = geog.getCurrentElectricGadgetKWHdp();
		calculatedKWHdp = currentElecticGadgetUsageKWHdp * (1-elecGadgetEfficiencySaving);
		setIsDecimal(false);

	}

	@Override
	public double getCurrentUsageValue() {
		return elecGadgetEfficiencySaving;
	}

	@Override
	public String getDescription() {
		return "Electrical Things";
	}

	@Override
	public double getKWHdp() {
		return calculatedKWHdp;
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

	/**
	 * From pg 111
	 */
	public double get2006KWHdp(){
		return geog.get2006ElectricalThingskWhd();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getElecThings2006Metric();
	}
	@Override
	public String getQuestionForChange() {
		return "please choose the percentage of efficiency measures "
				+ "you plan to implement for all electrical items.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		elecGadgetEfficiencySaving = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		calculatedKWHdp = currentElecticGadgetUsageKWHdp
				* (1 - elecGadgetEfficiencySaving);
		// fire event so everyone knows
		fireEventThroughContainer();
	}
	public String getUsageMetricString(){
		return " of savings ";
	}
}
