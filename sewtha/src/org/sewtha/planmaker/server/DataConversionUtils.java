package org.sewtha.planmaker.server;

import java.util.ArrayList;
import java.util.List;

import org.sewtha.planmaker.client.SimplePlanData;

import com.google.appengine.api.users.User;

public class DataConversionUtils {

	public static List<SimplePlanData> convertJDOPlansToClientPlans(
			List<EnergyPlanJDO> plans) {
		List<SimplePlanData> r = new ArrayList();
		for (EnergyPlanJDO dbPlan : plans) {
			r.add(new SimplePlanData(dbPlan.isGlobalPlan(),
					dbPlan.getBarrage(), dbPlan.getCleanCoal(), dbPlan
							.getElecLosses(), dbPlan.getElecThings(), dbPlan
							.getGeothermal(), dbPlan.getHeatingSavings(),
					dbPlan.getHydroHigh(), dbPlan.getHydroLow(), dbPlan
							.getLagoons(), dbPlan.getNuclear(), dbPlan
							.getOffshoreWindDeep(), dbPlan
							.getOffshoreWindShallow(), dbPlan.getOnshoreWind(),
					dbPlan.getPumpedHeat(), dbPlan.getSolarBiomass(), dbPlan
							.getSolarDesert(), dbPlan.getSolarFarming(), dbPlan
							.getSolarHeating(), dbPlan.getSolarPv(), dbPlan
							.getTidal(), dbPlan.getTransportSavings(), dbPlan
							.getWaste(), dbPlan.getWave(), dbPlan.getWood(),
					dbPlan.getFossilFuel(), dbPlan.getBarrageYears(), dbPlan.getCleanCoalYears(), dbPlan
					.getElecLossesYears(), dbPlan.getElecThingsYears(), dbPlan
					.getGeothermalYears(), dbPlan.getHeatingSavingsYears(),
			dbPlan.getHydroHighYears(), dbPlan.getHydroLowYears(), dbPlan
					.getLagoonsYears(), dbPlan.getNuclearYears(), dbPlan
					.getOffshoreWindDeepYears(), dbPlan
					.getOffshoreWindShallowYears(), dbPlan.getOnshoreWindYears(),
			dbPlan.getPumpedHeatYears(), dbPlan.getSolarBiomassYears(), dbPlan
					.getSolarDesertYears(), dbPlan.getSolarFarmingYears(), dbPlan
					.getSolarHeatingYears(), dbPlan.getSolarPvYears(), dbPlan
					.getTidalYears(), dbPlan.getTransportSavingsYears(), dbPlan
					.getWasteYears(), dbPlan.getWaveYears(), dbPlan.getWoodYears(),
			dbPlan.getFossilFuelYears(),dbPlan.getName(), dbPlan
							.getDescription(), dbPlan.getOptionalEmail(), dbPlan.getId()));
		}
		return r;
	}

	public static SimplePlanData convertJDOPlanToClientPlan(EnergyPlanJDO dbPlan) {
		SimplePlanData r = new SimplePlanData(dbPlan.isGlobalPlan(), dbPlan
				.getBarrage(), dbPlan.getCleanCoal(), dbPlan.getElecLosses(),
				dbPlan.getElecThings(), dbPlan.getGeothermal(), dbPlan
						.getHeatingSavings(), dbPlan.getHydroHigh(), dbPlan
						.getHydroLow(), dbPlan.getLagoons(), dbPlan
						.getNuclear(), dbPlan.getOffshoreWindDeep(), dbPlan
						.getOffshoreWindShallow(), dbPlan.getOnshoreWind(),
				dbPlan.getPumpedHeat(), dbPlan.getSolarBiomass(), dbPlan
						.getSolarDesert(), dbPlan.getSolarFarming(), dbPlan
						.getSolarHeating(), dbPlan.getSolarPv(), dbPlan
						.getTidal(), dbPlan.getTransportSavings(), dbPlan
						.getWaste(), dbPlan.getWave(), dbPlan.getWood(), dbPlan
						.getFossilFuel(), dbPlan
						.getBarrageYears(), dbPlan.getCleanCoalYears(), dbPlan.getElecLossesYears(),
						dbPlan.getElecThingsYears(), dbPlan.getGeothermalYears(), dbPlan
								.getHeatingSavingsYears(), dbPlan.getHydroHighYears(), dbPlan
								.getHydroLowYears(), dbPlan.getLagoonsYears(), dbPlan
								.getNuclearYears(), dbPlan.getOffshoreWindDeepYears(), dbPlan
								.getOffshoreWindShallowYears(), dbPlan.getOnshoreWindYears(),
						dbPlan.getPumpedHeatYears(), dbPlan.getSolarBiomassYears(), dbPlan
								.getSolarDesertYears(), dbPlan.getSolarFarmingYears(), dbPlan
								.getSolarHeatingYears(), dbPlan.getSolarPvYears(), dbPlan
								.getTidalYears(), dbPlan.getTransportSavingsYears(), dbPlan
								.getWasteYears(), dbPlan.getWaveYears(), dbPlan.getWoodYears(), dbPlan
								.getFossilFuelYears(), dbPlan.getName(), dbPlan
						.getDescription(), dbPlan.getOptionalEmail(), dbPlan.getId());

		return r;
	}

	public static void updateEnergyPlanJDOWithSimplePlan(EnergyPlanJDO ePlan,
			SimplePlanData clientPlan) {
		ePlan.setBarrage(clientPlan.getBarrage());
		ePlan.setCleanCoal(clientPlan.getCleanCoal());
		ePlan.setDescription(clientPlan.getDescription());
		ePlan.setElecLosses(clientPlan.getElecLosses());
		ePlan.setElecThings(clientPlan.getElecThings());
		ePlan.setGeothermal(clientPlan.getGeothermal());
		ePlan.setHeatingSavings(clientPlan.getHeatingSavings());
		ePlan.setHydroHigh(clientPlan.getHydroHigh());
		ePlan.setHydroLow(clientPlan.getHydroLow());
		ePlan.setLagoons(clientPlan.getLagoons());
		ePlan.setName(clientPlan.getName());
		ePlan.setNuclear(clientPlan.getNuclear());
		ePlan.setOffshoreWindDeep(clientPlan.getOffshoreWindDeep());
		ePlan.setOffshoreWindShallow(clientPlan.getOffshoreWindShallow());
		ePlan.setOnshoreWind(clientPlan.getOnshoreWind());
		ePlan.setPumpedHeat(clientPlan.getPumpedHeat());
		ePlan.setSolarBiomass(clientPlan.getSolarBiomass());
		ePlan.setSolarDesert(clientPlan.getSolarDesert());
		ePlan.setSolarFarming(clientPlan.getSolarFarming());
		ePlan.setSolarHeating(clientPlan.getSolarHeating());
		ePlan.setSolarPv(clientPlan.getSolarPv());
		ePlan.setTidal(clientPlan.getTidal());
		ePlan.setTransportSavings(clientPlan.getTransportSavings());
		ePlan.setWaste(clientPlan.getWaste());
		ePlan.setWave(clientPlan.getWave());
		ePlan.setWood(clientPlan.getWood());
	}

	public static EnergyPlanJDO convertClientPlanToJDOPlan(
			SimplePlanData clientPlan, User user) {

		// if the plan is sent from a user it will always be non-global
		boolean isGlobal = false;
		EnergyPlanJDO r = new EnergyPlanJDO(user, clientPlan.getName(),
				isGlobal, clientPlan.getBarrage(), clientPlan.getCleanCoal(),
				clientPlan.getElecLosses(), clientPlan.getElecThings(),
				clientPlan.getGeothermal(), clientPlan.getHeatingSavings(),
				clientPlan.getHydroHigh(), clientPlan.getHydroLow(), clientPlan
						.getLagoons(), clientPlan.getNuclear(), clientPlan
						.getOffshoreWindDeep(), clientPlan
						.getOffshoreWindShallow(), clientPlan.getOnshoreWind(),
				clientPlan.getPumpedHeat(), clientPlan.getSolarBiomass(),
				clientPlan.getSolarDesert(), clientPlan.getSolarFarming(),
				clientPlan.getSolarHeating(), clientPlan.getSolarPv(),
				clientPlan.getTidal(), clientPlan.getTransportSavings(),
				clientPlan.getWaste(), clientPlan.getWave(), clientPlan
						.getWood(), clientPlan.getFossilFuel(), clientPlan.getBarrageYears(), clientPlan.getCleanCoalYears(),
						clientPlan.getElecLossesYears(), clientPlan.getElecThingsYears(),
						clientPlan.getGeothermalYears(), clientPlan.getHeatingSavingsYears(),
						clientPlan.getHydroHighYears(), clientPlan.getHydroLowYears(), clientPlan
								.getLagoonsYears(), clientPlan.getNuclearYears(), clientPlan
								.getOffshoreWindDeepYears(), clientPlan
								.getOffshoreWindShallowYears(), clientPlan.getOnshoreWindYears(),
						clientPlan.getPumpedHeatYears(), clientPlan.getSolarBiomassYears(),
						clientPlan.getSolarDesertYears(), clientPlan.getSolarFarmingYears(),
						clientPlan.getSolarHeatingYears(), clientPlan.getSolarPvYears(),
						clientPlan.getTidalYears(), clientPlan.getTransportSavingsYears(),
						clientPlan.getWasteYears(), clientPlan.getWaveYears(), clientPlan
								.getWoodYears(), clientPlan.getFossilFuelYears(), clientPlan.getOptionalEmail(), clientPlan.getDescription());

		return r;
	}
}
