package org.sewtha.planmaker.client.energyconsumers;

import org.sewtha.planmaker.client.EnergyChangeListener;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class ElectricalLosses extends EnergyProducerOrConsumer {

	// not as ratio - divide by 100 to use
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	

	public ElectricalLosses(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		super(group, ProducerOrConsumerEnum.ELECCONV_LOSSES, index, false, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 2;
		calculatedKWHdp = 2;
		planHolder.addListener(new EnergyChangeListenerImpl());
		setIsDecimal(false);

	}

	@Override
	public double getCurrentUsageValue() {
		return currentUsage;
	}

	@Override
	public String getDescription() {
		return "Electrical Conversion and Grid Losses";
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
		return 100;
	}

	@Override
	public double getMinUsage() {
		return 0;
	}

	/**
	 * From pg 111
	 */
	public double get2006KWHdp() {
		return 27;
	}

	public double get2006KWHdpMetric() {
		return 27;
	}

	@Override
	public String getQuestionForChange() {
		return "This should not be a question here for this one as it is dependant on"
				+ " fossil fuels";
	}

	@Override
	public double setNewUsage(double changedValue) {
		this.currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	/**
	 * Here we need to calculate the KWHdp for elec losses based on the amount of fossil
	 * fuels and the amount of elec gadget usage
	 */
	private void calculateNewKWHdp() {
		EnergyProducerOrConsumer elecThings = planHolder
				.getEnergyProducerOrConsumer(ProducerOrConsumerEnum.ELECTHINGS);
		EnergyProducerOrConsumer fossFuels = planHolder
				.getEnergyProducerOrConsumer(
						ProducerOrConsumerEnum.FOSSIL_FUELS);
		//double eKWHDP = elecThings.getKWHdp();
		double fossFuelKWHDP = fossFuels.getKWHdp();
		// the KWHdp is worked out from the fossil fuel use now not the elec use
//		double calculatedPotentialKWHdpLoss = (fossFuelKWHDP
//				/ NaturalConstants.ELEC_LOSSES_FROM_FOSSIL_FUEL_CONV_RATIO) - fossFuelKWHDP;
//		//the fossil fuel usage is percentage of reduction
//		calculatedKWHdp = calculatedPotentialKWHdpLoss	* (1 - fossFuels.getCurrentUsageValue());
		calculatedKWHdp = fossFuelKWHDP * NaturalConstants.ELEC_LOSSES_FROM_FOSSIL_FUEL_CONV_RATIO;
		//there is a fixed loss for elec grid which will always be present
		calculatedKWHdp += NaturalConstants.ELEC_GRID_LOSSES;
		// fire event so everyone knows
		fireEventThroughContainer(this);
	}

	public String getUsageMetricString() {
		return " Should not be displayted ";
	}

	class EnergyChangeListenerImpl implements EnergyChangeListener {

		@Override
		public void EnergyPlanChanged() {
			// Not sure if we need to do anything here or not
			//calculateNewKWHdp();

		}

		@Override
		public void EnergySourceChanged(EnergyProducerOrConsumer changedSource) {
			// Not sure if we need to do anything here or not
			//calculateNewKWHdp();
		}

		@Override
		public void EnergyValueChanged(double metricValue, double KWHdpValue,
				EnergyProducerOrConsumer changedProdOrCons) {
			// We are interested if Elec Things or Fossil Fuels have changed
			if (changedProdOrCons.getEnergyType() == ProducerOrConsumerEnum.ELECTHINGS
					|| changedProdOrCons.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS) {
				calculateNewKWHdp();
			}

		}

	}
}
