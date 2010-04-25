/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import java.util.ArrayList;
import java.util.Collection;

import org.sewtha.planmaker.client.energyconsumers.ElectricGadgets;
import org.sewtha.planmaker.client.energyconsumers.ElectricalLosses;
import org.sewtha.planmaker.client.energyconsumers.Heating;
import org.sewtha.planmaker.client.energyconsumers.Transport;
import org.sewtha.planmaker.client.energyproducers.Barrage;
import org.sewtha.planmaker.client.energyproducers.CleanCoal;
import org.sewtha.planmaker.client.energyproducers.DeepOffshore;
import org.sewtha.planmaker.client.energyproducers.FossilFuels;
import org.sewtha.planmaker.client.energyproducers.HydroElectricHighland;
import org.sewtha.planmaker.client.energyproducers.HydroElectricLowland;
import org.sewtha.planmaker.client.energyproducers.Lagoon;
import org.sewtha.planmaker.client.energyproducers.Nuclear;
import org.sewtha.planmaker.client.energyproducers.OnshoreWindFarms;
import org.sewtha.planmaker.client.energyproducers.PumpedHeat;
import org.sewtha.planmaker.client.energyproducers.ShallowOffshore;
import org.sewtha.planmaker.client.energyproducers.SolarBiomass;
import org.sewtha.planmaker.client.energyproducers.SolarDesert;
import org.sewtha.planmaker.client.energyproducers.SolarHeating;
import org.sewtha.planmaker.client.energyproducers.SolarPVFarm;
import org.sewtha.planmaker.client.energyproducers.SolarPVRoof;
import org.sewtha.planmaker.client.energyproducers.Tidal;
import org.sewtha.planmaker.client.energyproducers.Waste;
import org.sewtha.planmaker.client.energyproducers.Wave;
import org.sewtha.planmaker.client.energyproducers.Wood;

/**
 * 
 * @author BarnabyK
 */
public class EnergyProducerOrConsumerGroup {

	private EnergyGroupingEnum eGroup;
	private Collection<EnergyProducerOrConsumer> energyProducerOrConsumers = new ArrayList();
	private EnergyProducerOrConsumer selectedEnergy;
	private boolean first = true;
	private String groupName = "";
	private String color = "#FF0000";
	private boolean shownInQuestion = true;
	private int graphModelRow = 0;
	private int graphModelCol = 0;
	private EnergyPlan planHolder;

	public EnergyProducerOrConsumerGroup(EnergyGroupingEnum eGroup,
			EnergyPlan planHolder) {
		this.planHolder = planHolder;

		switch (eGroup) {
		case CLEAN_COAL:
			groupName = "Clean Coal";
			selectedEnergy = new CleanCoal(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#326363";
			break;

		case ELECCONV_LOSSES:
			groupName = "Elec Conv Losses";
			selectedEnergy = new ElectricalLosses(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			shownInQuestion = false;
			break;

		case ELECTHINGS:
			groupName = "Electrical Things";
			selectedEnergy = new ElectricGadgets(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#B442BD";
			break;

		case FOSSIL_FUEL:
			groupName = "Fossil Fuels";
			selectedEnergy = new FossilFuels(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#808000";
			break;

		case GEOTHERMAL:
			groupName = "Geothermal";
			color = "#00FF00";
			break;

		case HEATING_SAVINGS:
			groupName = "Heating";
			selectedEnergy = new Heating(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#DA8425";
			break;

		case NUCLEAR:
			groupName = "Nuclear";
			selectedEnergy = new Nuclear(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "000000";
			break;

		case PUMPED_HEAT:
			groupName = "Pumped Heat";
			selectedEnergy = new PumpedHeat(
					GeographicalConstants.getInstance(), this, planHolder
							.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#DD2020";
			break;

		case HYDRO_WAVE_TIDAL:
			groupName = "Hydro / Tidal / Wave";
			selectedEnergy = new HydroElectricLowland(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			energyProducerOrConsumers.add(new HydroElectricHighland(
					GeographicalConstants.getInstance(), this, planHolder
							.getCurrentIndex(), planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new Tidal(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new Barrage(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new Lagoon(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new Wave(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			color = "#597BAE";
			break;

		case SOLAR:
			groupName = "Solar";
			selectedEnergy = new SolarHeating(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			energyProducerOrConsumers.add(new SolarPVRoof(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new SolarPVFarm(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new SolarBiomass(
					GeographicalConstants.getInstance(), this, planHolder
							.getCurrentIndex(), planHolder));
			planHolder.incrementCurrentIndex();
			color = "#D5D033";
			break;

		case SOLAR_DESERTS:
			groupName = "Solar Deserts";
			selectedEnergy = new SolarDesert(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#FFFF00";
			break;

		case TRANSPORT_SAVINGS:
			groupName = "Transport";
			selectedEnergy = new Transport(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#993333";
			break;

		case WASTE:
			selectedEnergy = new Waste(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			groupName = "Waste";
			color = "#999999";
			break;

		case WIND:
			groupName = "Wind";
			selectedEnergy = new OnshoreWindFarms(GeographicalConstants
					.getInstance(), this, planHolder.getCurrentIndex(),
					planHolder);
			planHolder.incrementCurrentIndex();
			// set selected component for this group
			energyProducerOrConsumers.add(selectedEnergy);
			energyProducerOrConsumers.add(new ShallowOffshore(
					GeographicalConstants.getInstance(), this, planHolder
							.getCurrentIndex(), planHolder));
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(new DeepOffshore(
					GeographicalConstants.getInstance(), this, planHolder
							.getCurrentIndex(), planHolder));
			planHolder.incrementCurrentIndex();
			color = "#7BBA43";
			break;

		case WOOD:
			groupName = "Wood";
			selectedEnergy = new Wood(GeographicalConstants.getInstance(),
					this, planHolder.getCurrentIndex(), planHolder);
			planHolder.incrementCurrentIndex();
			energyProducerOrConsumers.add(selectedEnergy);
			color = "#A65300";
			break;

		}
		this.eGroup = eGroup;
	}

	public boolean getShownInQuestion() {
		return shownInQuestion;
	}

	/**
	 * Gets the KWHdp for everything except fossil fuels if you specify 
	 * ignore fossil fuels
	 * 
	 * @return
	 */
	public double getKWHdpForGroup(boolean ignoreFossilFuel) {
		double KWHdp = 0.0;
		for (EnergyProducerOrConsumer e : energyProducerOrConsumers) {
			if (e.getEnergyType() != ProducerOrConsumerEnum.FOSSIL_FUELS) {
				KWHdp += e.getKWHdp();
			} else if (!ignoreFossilFuel) {
				KWHdp += e.getKWHdp();
			}
		}
		return KWHdp;
	}

	/**
	 * This will return KWHdp for everything (including fossil fuels)
	 * If you do not want fossil fuels there is anotehr method in which you 
	 * can pass a flag
	 * @return
	 */
	public double getKWHdpForGroup() {
		return getKWHdpForGroup(false);
	}

	public EnergyGroupingEnum getEnergyGroupEnum() {
		return eGroup;
	}

	public String getEnergyGroupName() {
		return groupName;
	}

	public void setSelectedComponent(EnergyProducerOrConsumer selectedEnergy) {
		this.selectedEnergy = selectedEnergy;
		planHolder.setSelectedGroup(this);
		// find the index and save it

	}

	public EnergyProducerOrConsumer getSelectedComponent() {
		return selectedEnergy;
	}

	public Collection<EnergyProducerOrConsumer> getAllEnergyInGroup() {
		return energyProducerOrConsumers;
	}

	public String getColor() {
		return color;
	}

	public void setGraphModelRowAndColumn(int row, int column) {
		graphModelRow = row;
		graphModelCol = column;
	}

	public int getGraphModelRow() {
		return graphModelRow;
	}

	public int getGraphModelCol() {
		return graphModelCol;
	}
}
