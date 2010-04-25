package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyChangeListener;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class PumpedHeat extends EnergyProducerOrConsumer {
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;

	public PumpedHeat(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		// this is where the country would be picked
		super(group, ProducerOrConsumerEnum.PUMPED_HEAT, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 0.5;
		calculatedKWHdp = 12.0;
		planHolder.addListener(new EnergyChangeListenerImpl());
		setIsDecimal(false);

	}

	public String getDescription() {
		return "Pumped Heat";
	}

	/**
	 * Could be reduced
	 */
	public double getMinUsage() {
		return 0;
	}

	/**
	 * as per page 111
	 */
	public double get2006KWHdp() {
		return geog.get2006PumpedHeatKWHdp();
	}

	public double get2006KWHdpMetric() {
		return geog.getPumpedHeat2006Metric();
	}

	public double getMaxUsage() {
		return 1;
	}

	public String getQuestionForChange() {
		return "Please choose the percentage of heating needs " +
				"(after taking into account Solar Heating and Wood) to be met by Pumped Heat.";
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
		// based on how much heating is actually needed.
		EnergyProducerOrConsumer heatingUsage = planHolder
				.getEnergyProducerOrConsumer(
						ProducerOrConsumerEnum.HEATING_SAVINGS);
		EnergyProducerOrConsumer solarHW = EnergyPlan.getMainInstance()
				.getEnergyProducerOrConsumer(
						ProducerOrConsumerEnum.SOLAR_HEATING);
		EnergyProducerOrConsumer woodHeating = EnergyPlan.getMainInstance()
				.getEnergyProducerOrConsumer(ProducerOrConsumerEnum.WOOD);
		double maxHeatForPumpedHeat = heatingUsage.getKWHdp()
				- solarHW.getKWHdp() - woodHeating.getKWHdp();
		calculatedKWHdp = maxHeatForPumpedHeat * currentUsage;
		// fire event so everyone knows
		fireEventThroughContainer(this);
	}

	public String getUsageMetricString() {
		return " of remaining Heating needs to be met by Pumped Heat after taking account of " +
				"Solar Heating and Wood";
	}

	class EnergyChangeListenerImpl implements EnergyChangeListener {

		@Override
		public void EnergyPlanChanged() {
			// the usage of this is dependant on the usage of Wood and Solar
			// Heat
			calculateNewKWHdp();

		}

		@Override
		public void EnergySourceChanged(EnergyProducerOrConsumer changedSource) {
			// the usage of this is dependent on the usage of Wood and Solar
			// Heat
			calculateNewKWHdp();
		}

		@Override
		public void EnergyValueChanged(double metricValue, double KWHdpValue,
				EnergyProducerOrConsumer changedProdOrCons) {
			// the usage of this is dependent on the usage of Wood and Solar
			// Heat
			if (changedProdOrCons.getEnergyType() == ProducerOrConsumerEnum.SOLAR_HEATING
					|| changedProdOrCons.getEnergyType() == ProducerOrConsumerEnum.WOOD) {
				calculateNewKWHdp();
			}

		}

	}
}
