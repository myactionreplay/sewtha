package org.sewtha.planmaker.client;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @deprecated
 * @author BarnabyK
 *
 */

public class EnergyPlanHolder {

	private ArrayList<EnergyPlan> energyPlans = new ArrayList();
	private int selectedPlan = 0;
	private static EnergyPlanHolder instance = null;

	public EnergyPlanHolder() {
		// on instantiation create the 5 plans from sewtha
		EnergyPlan e = EnergyPlan.getMainInstance();
		e.init();
		energyPlans.add(e);
		// after that go to the server and collect all other plans
	}

	public Collection<EnergyPlan> getEnergyPlans() {
		return energyPlans;
	}

	public EnergyPlan getSelectedPlan() {
		return energyPlans.get(selectedPlan);
	}

	public void addEnergyPlan(EnergyPlan ePlan) {
		energyPlans.add(ePlan);
	}

	public SimplePlanData getSimplePlanFromPlan() {
		EnergyPlan ePlan = getSelectedPlan();
		SimplePlanData plan = new SimplePlanData(ePlan.getPlanName());
		for (EnergyProducerOrConsumerGroup g : ePlan
				.getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case SOLAR_HEATING:
					plan.setSolarHeating(e.getCurrentUsageValue());
					break;
				case SOLAR_PV:
					plan.setSolarPv(e.getCurrentUsageValue());
					break;
				case SOLAR_FARMING:
					plan.setSolarFarming(e.getCurrentUsageValue());
					break;
				case SOLAR_BIOMASS:
					plan.setSolarBiomass(e.getCurrentUsageValue());
					break;
				case ONSHORE_WIND:
					plan.setOnshoreWind(e.getCurrentUsageValue());
					break;
				case OFFSHORE_WIND_SHALLOW:
					plan.setOffshoreWindShallow(e.getCurrentUsageValue());
					break;
				case OFFSHORE_WIND_DEEP:
					plan.setOffshoreWindDeep(e.getCurrentUsageValue());
					break;
				case HYDRO_LOWLAND:
					plan.setHydroLow(e.getCurrentUsageValue());
					break;
				case HYDRO_HIGHLAND:
					plan.setHydroHigh(e.getCurrentUsageValue());
					break;
				case TIDAL:
					plan.setTidal(e.getCurrentUsageValue());
					break;
				case BARRAGE:
					plan.setBarrage(e.getCurrentUsageValue());
					break;
				case LAGOONS:
					plan.setLagoons(e.getCurrentUsageValue());
					break;
				case WAVE:
					plan.setWave(e.getCurrentUsageValue());
					break;
				case GEOTHERMAL:
					plan.setGeothermal(e.getCurrentUsageValue());
					break;
				case CLEAN_COAL:
					plan.setCleanCoal(e.getCurrentUsageValue());
					break;
				case NUCLEAR:
					plan.setNuclear(e.getCurrentUsageValue());
					break;
				case SOLAR_DESERTS:
					plan.setSolarDesert(e.getCurrentUsageValue());
					break;
				case WASTE:
					plan.setWaste(e.getCurrentUsageValue());
					break;
				case PUMPED_HEAT:
					plan.setPumpedHeat(e.getCurrentUsageValue());
					break;
				case WOOD:
					plan.setWood(e.getCurrentUsageValue());
					break;

				}

			}
		}

		for (EnergyProducerOrConsumerGroup g : ePlan
				.getAllEnergyProducerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case ELECCONV_LOSSES:
					// not sure if we need to do anything here
					break;
				case ELECTHINGS:
					plan.setElecThings(e.getCurrentUsageValue());
					break;
				case HEATING_SAVINGS:
					plan.setHeatingSavings(e.getCurrentUsageValue());
					break;
				case TRANSPORT_SAVINGS:
					plan.setTransportSavings(e.getCurrentUsageValue());
					break;
				}
			}
		}

		return plan;

	}

	public Collection<String> getAllPlanNames() {
		ArrayList<String> r = new ArrayList();
		for (EnergyPlan e : energyPlans) {
			r.add(e.getPlanName());
		}
		return r;
	}

	public boolean removeEnergyPlan(EnergyPlan e) {
		boolean r = false;
		r = energyPlans.remove(e);
		return r;
	}

	public static EnergyPlanHolder getInstance() {
		if (instance == null) {
			instance = new EnergyPlanHolder();
		}
		return instance;
	}
}
