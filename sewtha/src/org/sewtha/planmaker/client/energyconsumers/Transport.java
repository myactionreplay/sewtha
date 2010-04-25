package org.sewtha.planmaker.client.energyconsumers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class Transport extends EnergyProducerOrConsumer {
	// not as ratio - divide by 100 to use
	private double currentTransportUsageKWHdp;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	private double transportEfficiencySaving = 0.50;

	public Transport(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.TRANSPORT_SAVINGS, index, planHolder);
		this.geog = geog;
		currentTransportUsageKWHdp = geog.getCurrentTransportKWHdp();
		calculatedKWHdp = currentTransportUsageKWHdp
				* (1 - transportEfficiencySaving);
		setIsDecimal(false);
	}

	@Override
	public double getCurrentUsageValue() {
		return transportEfficiencySaving;
	}

	@Override
	public String getDescription() {
		return "Transport";
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
	public double get2006KWHdp() {
		return geog.get2006TransportkWhd();
	}

	public double get2006KWHdpMetric() {
		return geog.getTransport2006Metric();
	}

	@Override
	public String getQuestionForChange() {
		return "please choose the percentage of transport efficieny measures"
				+ " you plan to implement.";
	}

	@Override
	public double setNewUsage(double changedValue) {
		transportEfficiencySaving = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		calculatedKWHdp = currentTransportUsageKWHdp
				* (1 - transportEfficiencySaving);
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of savings ";
	}
}
