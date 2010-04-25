package org.sewtha.planmaker.client;

import java.util.Collection;

public class EnergyPlanClone extends EnergyPlan {

	// always starts on 0
	private int currentSimYear = 0;

	public EnergyPlanClone() {
		super();
	}

	protected void setUpSimulatedPlan() {
		// get the original
		EnergyPlan orig = EnergyPlan.getMainInstance();
		// don't want any listeners on the copy
		setUpEnergyPlanComponents();
		setPlanName(orig.getPlanName());
		setPlanDescription(orig.getPlanDescription());
		setLongestYearImplementation(orig.getLongestYearImplementation());
		setUsageOnSimPlan(orig.getAllEnergyConsumerGroups());
		setUsageOnSimPlan(orig.getAllEnergyProducerGroups());
	}

	private void setUsageOnSimPlan(
			Collection<EnergyProducerOrConsumerGroup> origColl) {
		for (EnergyProducerOrConsumerGroup g : origColl) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				EnergyProducerOrConsumer simE = this
						.getEnergyProducerOrConsumer(e.getEnergyType());
				// reset to no change values
				simE.setNewUsage(simE.get2006KWHdpMetric());
				simE.setTargetUsage(e.getCurrentUsageValue());
				simE.setYearsToImplement(e.getYearsToImplement());

			}
		}
	}

	public void rollSimulatorOnAYear() {
		// only roll on if all plans have been implemented

		if (currentSimYear < getLongestYearImplementation()) {
			rollOnConsumption();
			rollOnProduction();
			currentSimYear++;
		}
	}

	private void rollOnConsumption() {
		// we do need to roll on the consumption as it affects pumped heat
		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				// but ignore elec losses as this will be derived from
				// fossil fuels
				if (e.getEnergyType() != ProducerOrConsumerEnum.ELECCONV_LOSSES) {
					double incr = (e.getTargetUsage() - e.get2006KWHdpMetric())
							/ e.getYearsToImplement();
					double proposedUsage = e.getCurrentUsageValue() + incr;
					// don't want to go over the target usage
					if (proposedUsage > e.getTargetUsage()) {
						proposedUsage = e.getTargetUsage();
					}
					e.setNewUsage(proposedUsage);
				}
			}
		}
	}

	private void rollOnProduction() {
		// producers are the main part though
		for (EnergyProducerOrConsumerGroup g : getAllEnergyProducerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				// fossil fuels are a special case as they need to balance
				// out
				// any strange things caused by timings of plans
				if (e.getEnergyType() != ProducerOrConsumerEnum.FOSSIL_FUELS) {
					double incr = (e.getTargetUsage() - e.get2006KWHdpMetric())
							/ e.getYearsToImplement();
					double proposedUsage = e.getCurrentUsageValue() + incr;
					if (e.getTargetUsage() > e.get2006KWHdpMetric()) {
						// don't want to go over the target usage if the target
						// usage
						// is greater than the baseline usage
						if (proposedUsage > e.getTargetUsage()) {
							proposedUsage = e.getTargetUsage();
						}
					} else {
						// don't want to go under the target usage is less than
						// the baseline usage
						if (proposedUsage < e.getTargetUsage()) {
							proposedUsage = e.getTargetUsage();
						}
					}
					e.setNewUsage(proposedUsage);
				}
			}
		}
		// now sort fossil fuels
		// we are effectively saying that all fossil fuels are used for conv to
		// elec which could be incorrect but is ok for now
		double balance = getConsumerKWHdpNotIncFossFuelElecLosses()
				- getProducerKWHdp(true);

		// we can reasonable say that the balance will incur the conversion
		// losses
		EnergyProducerOrConsumer foss = getEnergyProducerOrConsumer(ProducerOrConsumerEnum.FOSSIL_FUELS);
		double calculatedPotentialKWHdpLoss = balance
				* NaturalConstants.ELEC_LOSSES_FROM_FOSSIL_FUEL_CONV_RATIO;
		// need to set usage as the percentage reduction in time zero values

		foss.setNewUsage(1 - ((balance + calculatedPotentialKWHdpLoss) / foss
				.get2006KWHdp()));

	}

	/**
	 * Obtains the KWHdp used for consumption not including the amount of elec
	 * losses due to fossil fuel conversion
	 * 
	 * @return
	 */
	private double getConsumerKWHdpNotIncFossFuelElecLosses() {
		double ret = 0;
		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				if (e.getEnergyType() != ProducerOrConsumerEnum.ELECCONV_LOSSES) {
					ret += e.getKWHdp();
				}
			}
		}
		// add in the general grid losses
		ret += NaturalConstants.ELEC_GRID_LOSSES;
		return ret;
	}

}
