/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.visualization.client.DataTable;

/**
 * This class contains all information about the energy producers and consumers.
 * Any listeners should attach to this object rather than an individual energy
 * source.
 * 
 * @author BarnabyK
 */
public class EnergyPlan implements Cloneable {

	private Collection<EnergyProducerOrConsumerGroup> energyProducerGroupColl = null;
	private Collection<EnergyProducerOrConsumerGroup> energyConsumerGroupColl = null;
	private static EnergyPlan instance = null;
	private Collection<EnergyChangeListener> listeners = new ArrayList<EnergyChangeListener>();
	private DataTable dataForVis = null;
	private String planName = "Plan M - Middle Road";
	private String planDescription = "Acts as a compromise between all of the other plans and takes into consideration economical factors also.";
	private EnergyProducerOrConsumerGroup selectedGroup;
	private boolean updatesTempOff = false;
	private long id;
	private int eConsumOrProdCounter = 0;
	private boolean isNameChanged = false;
	private boolean isDescChanged = false;
	private int currentQuestionCounter = 0;
	private String optionalEmail = "";
	private int longestYearImpl = NaturalConstants.DEFAULT_YEARS_TO_IMPLEMENT;

	public EnergyPlan() {
	}

	public void init() {
		// Create the energy groups (which will create their own energies)
		// select the first group as the selected group
		setUpEnergyPlanComponents();
	}

	protected void setUpEnergyPlanComponents() {
		boolean first = true;
		this.energyConsumerGroupColl = new ArrayList<EnergyProducerOrConsumerGroup>();
		this.energyProducerGroupColl = new ArrayList<EnergyProducerOrConsumerGroup>();
		for (EnergyGroupingEnum e : EnergyGroupingEnum.values()) {
			EnergyProducerOrConsumerGroup g = new EnergyProducerOrConsumerGroup(
					e, this);
			populateRelevantCollection(g);
			if (first) {
				selectedGroup = g;
				first = false;
			}
		}
	}

	/**
	 * This clone returns a mirrored clone of the Energy PLan to be used in
	 * simulations. The main Energy Plan will be returned by getInstance
	 * 
	 */
	public EnergyPlanClone clone() {
		EnergyPlanClone ret = null;
		// clone seems to not work with GWT
		ret = new EnergyPlanClone();
		// ret = new EnergyPlan();
		ret.setUpSimulatedPlan();
		return ret;
	}

	public int getLongestYearImplementation() {
		return longestYearImpl;
	}

	protected void setLongestYearImplementation(int longestYearImpl) {
		this.longestYearImpl = longestYearImpl;
	}

	public void updateLongestYearImplementation() {
		// reset longest year as the longest one may have been changed
		longestYearImpl = 0;
		checkForImplYears(energyConsumerGroupColl);
		checkForImplYears(energyProducerGroupColl);
		updateFossilFuelsYearsToImpl();
	}

	/**
	 * In terms of years to implement, fossil fuels is the balancing number
	 * 
	 */
	private void updateFossilFuelsYearsToImpl() {
		EnergyProducerOrConsumer fossFuel = getEnergyProducerOrConsumer(ProducerOrConsumerEnum.FOSSIL_FUELS);
		fossFuel.setYearsToImplement(longestYearImpl);

	}

	private void checkForImplYears(
			Collection<EnergyProducerOrConsumerGroup> coll) {

		for (EnergyProducerOrConsumerGroup g : coll) {
			// should not do fossil fuels as they should be the balancing number
			if (g.getEnergyGroupEnum() != EnergyGroupingEnum.FOSSIL_FUEL) {
				for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
					if (e.getYearsToImplement() > longestYearImpl) {
						longestYearImpl = e.getYearsToImplement();
					}
				}
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Populates the relevant collections for the type of group.
	 * 
	 * @param g
	 */
	private void populateRelevantCollection(EnergyProducerOrConsumerGroup g) {
		switch (g.getEnergyGroupEnum()) {
		case CLEAN_COAL:
			energyProducerGroupColl.add(g);
			break;

		case ELECCONV_LOSSES:
			energyConsumerGroupColl.add(g);
			break;

		case ELECTHINGS:
			energyConsumerGroupColl.add(g);
			break;

		case GEOTHERMAL:
			energyProducerGroupColl.add(g);
			break;

		case HEATING_SAVINGS:
			energyConsumerGroupColl.add(g);
			break;

		case HYDRO_WAVE_TIDAL:
			energyProducerGroupColl.add(g);
			break;

		case NUCLEAR:
			energyProducerGroupColl.add(g);
			break;

		case PUMPED_HEAT:
			energyProducerGroupColl.add(g);
			break;

		case SOLAR:
			energyProducerGroupColl.add(g);
			break;

		case SOLAR_DESERTS:
			energyProducerGroupColl.add(g);
			break;

		case TRANSPORT_SAVINGS:
			energyConsumerGroupColl.add(g);
			break;

		case WASTE:
			energyProducerGroupColl.add(g);
			break;

		case WIND:
			energyProducerGroupColl.add(g);
			break;

		case FOSSIL_FUEL:
			energyProducerGroupColl.add(g);
			break;

		case WOOD:
			energyProducerGroupColl.add(g);
			break;
		}

	}

	/**
	 * Returns the original Energy Plan - this should NOT be used for retrieving
	 * a copy. This should only be retrieved using clone()
	 * 
	 * @return
	 */
	public static EnergyPlan getMainInstance() {
		if (instance == null) {
			instance = new EnergyPlan();
		}
		return instance;
	}

	public Collection<EnergyProducerOrConsumerGroup> getAllEnergyProducerGroups() {
		return energyProducerGroupColl;
	}

	public Collection<EnergyProducerOrConsumerGroup> getAllEnergyConsumerGroups() {
		return energyConsumerGroupColl;
	}

	public void setNewPlan(SimplePlanData plan) {
		// turn off updates while we do a batch update
		updatesTempOff = true;
		setPlanDescription(plan.getDescription());
		setPlanName(plan.getName());
		for (EnergyProducerOrConsumerGroup g : getAllEnergyProducerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case SOLAR_HEATING:
					e.setNewUsage(plan.getSolarHeating());
					e.setYearsToImplement(plan.getSolarHeatingYears());
					break;
				case SOLAR_PV:
					e.setNewUsage(plan.getSolarPv());
					e.setYearsToImplement(plan.getSolarPvYears());
					break;
				case SOLAR_FARMING:
					e.setNewUsage(plan.getSolarFarming());
					e.setYearsToImplement(plan.getSolarFarmingYears());
					break;
				case SOLAR_BIOMASS:
					e.setNewUsage(plan.getSolarBiomass());
					e.setYearsToImplement(plan.getSolarBiomassYears());
					break;
				case ONSHORE_WIND:
					e.setNewUsage(plan.getOnshoreWind());
					e.setYearsToImplement(plan.getOnshoreWindYears());
					break;
				case OFFSHORE_WIND_SHALLOW:
					e.setNewUsage(plan.getOffshoreWindShallow());
					e.setYearsToImplement(plan.getOffshoreWindShallowYears());
					break;
				case OFFSHORE_WIND_DEEP:
					e.setNewUsage(plan.getOffshoreWindDeep());
					e.setYearsToImplement(plan.getOffshoreWindDeepYears());
					break;
				case HYDRO_LOWLAND:
					e.setNewUsage(plan.getHydroLow());
					e.setYearsToImplement(plan.getHydroLowYears());
					break;
				case HYDRO_HIGHLAND:
					e.setNewUsage(plan.getHydroHigh());
					e.setYearsToImplement(plan.getHydroHighYears());
					break;
				case TIDAL:
					e.setNewUsage(plan.getTidal());
					e.setYearsToImplement(plan.getTidalYears());
					break;
				case BARRAGE:
					e.setNewUsage(plan.getBarrage());
					e.setYearsToImplement(plan.getBarrageYears());
					break;
				case LAGOONS:
					e.setNewUsage(plan.getLagoons());
					e.setYearsToImplement(plan.getLagoonsYears());
					break;
				case WAVE:
					e.setNewUsage(plan.getWave());
					e.setYearsToImplement(plan.getWaveYears());
					break;
				case GEOTHERMAL:
					e.setNewUsage(plan.getGeothermal());
					e.setYearsToImplement(plan.getGeothermalYears());
					break;
				case CLEAN_COAL:
					e.setNewUsage(plan.getCleanCoal());
					e.setYearsToImplement(plan.getCleanCoalYears());
					break;
				case NUCLEAR:
					e.setNewUsage(plan.getNuclear());
					e.setYearsToImplement(plan.getNuclearYears());
					break;
				case SOLAR_DESERTS:
					e.setNewUsage(plan.getSolarDesert());
					e.setYearsToImplement(plan.getSolarDesertYears());
					break;
				case WASTE:
					e.setNewUsage(plan.getWaste());
					e.setYearsToImplement(plan.getWasteYears());
					break;
				case PUMPED_HEAT:
					e.setNewUsage(plan.getPumpedHeat());
					e.setYearsToImplement(plan.getPumpedHeatYears());
					break;
				case WOOD:
					e.setNewUsage(plan.getWood());
					e.setYearsToImplement(plan.getWoodYears());
					break;

				case FOSSIL_FUELS:
					e.setNewUsage(plan.getFossilFuel());
					e.setYearsToImplement(plan.getFossilFuelYears());
					break;

				}

			}
		}

		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case ELECCONV_LOSSES:
					// not sure if we need to do anything here
					e.setNewUsage(plan.getElecLosses());
					e.setYearsToImplement(plan.getElecLossesYears());
					break;
				case ELECTHINGS:
					e.setNewUsage(plan.getElecThings());
					e.setYearsToImplement(plan.getElecThingsYears());
					break;
				case HEATING_SAVINGS:
					e.setNewUsage(plan.getHeatingSavings());
					e.setYearsToImplement(plan.getHeatingSavingsYears());
					break;
				case TRANSPORT_SAVINGS:
					e.setNewUsage(plan.getTransportSavings());
					e.setYearsToImplement(plan.getTransportSavingsYears());
					break;
				}
			}
		}

		// Make sure that it knows the name and desc have not been changed
		isNameChanged = false;
		isDescChanged = false;
		updatesTempOff = false;
		// fire an event to indicate a change
		fireEvent(false, getSelectedComponent());
	}

	public EnergyProducerOrConsumer getSelectedComponent() {
		return selectedGroup.getSelectedComponent();
	}

	/**
	 * This will be called when a group is changed or when a energy source is
	 * changed the actual component can be selected from the group. There is a
	 * helper method in ere for convenience.
	 * 
	 * @param selectedGroup
	 */
	public void setSelectedGroup(EnergyProducerOrConsumerGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
		refreshTableForVisualiation();
		fireEvent(true, getSelectedComponent());
	}

	/**
	 * This method should be called when any value for the energy source
	 * changes. This could be from an overview or from the inspector or any
	 * other place necessary.
	 */
	public void setValueChanged(EnergyProducerOrConsumer e) {
		// no need to record group changing as this will be done in separate
		// method
		updateLongestYearImplementation();
		refreshTableForVisualiation();
		fireEvent(false, e);
	}

	public EnergyProducerOrConsumerGroup getSelectedGroup() {
		return selectedGroup;
	}

	public void addListener(EnergyChangeListener l) {
		listeners.add(l);
	}

	public void removeListener(EnergyChangeListener l) {
		listeners.remove(l);
	}

	/**
	 * Informs if an energy source has been changed as being manipulated
	 */
	public void fireEvent(boolean isEnergySourceChange,
			EnergyProducerOrConsumer e) {
		if (!updatesTempOff) {
			for (EnergyChangeListener l : listeners) {
				if (isEnergySourceChange) {
					l.EnergySourceChanged(e);
				} else {
					// must be value changed so ship it
					l.EnergyValueChanged(e.getCurrentUsageValue(),
							e.getKWHdp(), e);
				}
			}
		}
	}

	public DataTable getDataTableForVisualisation() {
		return dataForVis;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		isNameChanged = true;
		this.planName = planName;
		fireEvent(false, getSelectedComponent());
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		isDescChanged = true;
		this.planDescription = planDescription;
		fireEvent(false, getSelectedComponent());
	}

	public void setOptionalEmail(String s) {
		this.optionalEmail = s;
	}

	public String getOptionalEmail() {
		return optionalEmail;
	}

	/**
	 * This needs to be called when an individual Energy Source or Producer
	 * component changes and before the Energy Source or Producer sends out the
	 * update to all other user interface listeners.
	 */
	protected DataTable refreshTableForVisualiation() {
		// re construct the DataTable from the individual Energy Components and
		// then return

		return dataForVis;
	}

	/**
	 * Obtains the original data from the server. This can then populate the
	 * EnergyProducersOrConsumer instances.
	 */
	private void collectDataFromServer(String username, int planId)
			throws Exception {
		// collect it and then populate the relevant energy sources with it
		// then create a local DataTable for the VIsualization

	}

	/**
	 * Saves the data to the server that has been created.
	 */
	private void saveDataToServer() {
		// obtains the information from the collected EnergyProducersOrConsumers
		// and then spits them
		// Server will create the plan Id
		// out to the server
	}

	public double getHigherOfConsumerOrProducerKWHdp() {
		double r = getConsumerKWHdp();
		if (getConsumerKWHdp() < getProducerKWHdp(false)) {
			r = getProducerKWHdp(false);
		}
		return r;
	}

	public double getConsumerKWHdp() {
		double r = 0;
		for (EnergyProducerOrConsumerGroup g : energyConsumerGroupColl) {
			r += g.getKWHdpForGroup();
		}
		return r;
	}

	public double getProducerKWHdp(boolean ignoreFossilFuels) {
		double r = 0;
		for (EnergyProducerOrConsumerGroup g : energyProducerGroupColl) {
			r += g.getKWHdpForGroup(ignoreFossilFuels);
		}
		return r;
	}

	public double getEnergySurplasOrDefecit() {
		return getProducerKWHdp(false) - getConsumerKWHdp();
	}

	public SimplePlanData getSimplePlanFOrServer() {
		String planName = getPlanName();
		String planDesc = getPlanDescription();
		String optionalEmail = getOptionalEmail();
		SimplePlanData plan = new SimplePlanData(planName);
		plan.setDescription(planDesc);
		plan.setOptionalEmail(optionalEmail);
		for (EnergyProducerOrConsumerGroup g : getAllEnergyProducerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case SOLAR_HEATING:
					plan.setSolarHeating(e.getCurrentUsageValue());
					plan.setSolarHeatingYears(e.getYearsToImplement());
					break;
				case SOLAR_PV:
					plan.setSolarPv(e.getCurrentUsageValue());
					plan.setSolarPvYears(e.getYearsToImplement());
					break;
				case SOLAR_FARMING:
					plan.setSolarFarming(e.getCurrentUsageValue());
					plan.setSolarFarmingYears(e.getYearsToImplement());
					break;
				case SOLAR_BIOMASS:
					plan.setSolarBiomass(e.getCurrentUsageValue());
					plan.setSolarBiomassYears(e.getYearsToImplement());
					break;
				case ONSHORE_WIND:
					plan.setOnshoreWind(e.getCurrentUsageValue());
					plan.setOnshoreWindYears(e.getYearsToImplement());
					break;
				case OFFSHORE_WIND_SHALLOW:
					plan.setOffshoreWindShallow(e.getCurrentUsageValue());
					plan.setOffshoreWindShallowYears(e.getYearsToImplement());
					break;
				case OFFSHORE_WIND_DEEP:
					plan.setOffshoreWindDeep(e.getCurrentUsageValue());
					plan.setOffshoreWindDeepYears(e.getYearsToImplement());
					break;
				case HYDRO_LOWLAND:
					plan.setHydroLow(e.getCurrentUsageValue());
					plan.setHydroLowYears(e.getYearsToImplement());
					break;
				case HYDRO_HIGHLAND:
					plan.setHydroHigh(e.getCurrentUsageValue());
					plan.setHydroHighYears(e.getYearsToImplement());
					break;
				case TIDAL:
					plan.setTidal(e.getCurrentUsageValue());
					plan.setTidalYears(e.getYearsToImplement());
					break;
				case BARRAGE:
					plan.setBarrage(e.getCurrentUsageValue());
					plan.setBarrageYears(e.getYearsToImplement());
					break;
				case LAGOONS:
					plan.setLagoons(e.getCurrentUsageValue());
					plan.setLagoonsYears(e.getYearsToImplement());
					break;
				case WAVE:
					plan.setWave(e.getCurrentUsageValue());
					plan.setWaveYears(e.getYearsToImplement());
					break;
				case GEOTHERMAL:
					plan.setGeothermal(e.getCurrentUsageValue());
					plan.setGeothermalYears(e.getYearsToImplement());
					break;
				case CLEAN_COAL:
					plan.setCleanCoal(e.getCurrentUsageValue());
					plan.setCleanCoalYears(e.getYearsToImplement());
					break;
				case NUCLEAR:
					plan.setNuclear(e.getCurrentUsageValue());
					plan.setNuclearYears(e.getYearsToImplement());
					break;
				case SOLAR_DESERTS:
					plan.setSolarDesert(e.getCurrentUsageValue());
					plan.setSolarDesertYears(e.getYearsToImplement());
					break;
				case WASTE:
					plan.setWaste(e.getCurrentUsageValue());
					plan.setWasteYears(e.getYearsToImplement());
					break;
				case PUMPED_HEAT:
					plan.setPumpedHeat(e.getCurrentUsageValue());
					plan.setPumpedHeatYears(e.getYearsToImplement());
					break;
				case WOOD:
					plan.setWood(e.getCurrentUsageValue());
					plan.setWoodYears(e.getYearsToImplement());
					break;
				case FOSSIL_FUELS:
					plan.setFossilFuel(e.getCurrentUsageValue());
					plan.setFossilFuelYears(e.getYearsToImplement());
					break;

				}

			}
		}

		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				switch (e.getEnergyType()) {
				case ELECCONV_LOSSES:
					// not sure if we need to do anything here
					break;
				case ELECTHINGS:
					plan.setElecThings(e.getCurrentUsageValue());
					plan.setElecThingsYears(e.getYearsToImplement());
					break;
				case HEATING_SAVINGS:
					plan.setHeatingSavings(e.getCurrentUsageValue());
					plan.setHeatingSavingsYears(e.getYearsToImplement());
					break;
				case TRANSPORT_SAVINGS:
					plan.setTransportSavings(e.getCurrentUsageValue());
					plan.setTransportSavingsYears(e.getYearsToImplement());
					break;
				}
			}
		}

		return plan;

	}

	/**
	 * Sets the next energy or consumer, returning true if the button should be
	 * disabled
	 * 
	 * @return
	 */
	public boolean setNextEnergyOrConsumer() {
		boolean disableButton = false;
		int elementIdRequested = getSelectedComponent().getQuestionId();
		EnergyProducerOrConsumer r = findNextOrPreviousElementById(true,
				elementIdRequested);
		if (r != null) {
			r.getEnergyOrConsumerGroup().setSelectedComponent(r);
			if (elementIdRequested == (getCurrentQuestionCounter() - 2)) {
				disableButton = true;
			} else {
				disableButton = false;
			}
		} else {
			disableButton = true;
		}
		return disableButton;
	}

	/**
	 * Sets the previous energy or consumer, returning true if the button should
	 * be disabled
	 */
	public boolean setPreviousEnergyOrConsumer() {
		boolean disableButton = false;
		int elementIdRequested = getSelectedComponent().getQuestionId();
		EnergyProducerOrConsumer r = findNextOrPreviousElementById(false,
				elementIdRequested);
		if (r != null) {
			r.getEnergyOrConsumerGroup().setSelectedComponent(r);
			// if the current question is the second in the list then the first
			// one
			// will be selected and can now be disabled
			if (elementIdRequested == 0) {
				disableButton = true;
			} else {
				disableButton = false;
			}
		} else {
			disableButton = true;
		}
		return disableButton;
	}

	private EnergyProducerOrConsumer findNextOrPreviousElementById(
			boolean isNext, int elementIdRequested) {

		if (isNext) {
			elementIdRequested++;
		} else {
			elementIdRequested--;
		}
		for (EnergyProducerOrConsumerGroup g : energyConsumerGroupColl) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				if (e.getQuestionId() == elementIdRequested) {
					return e;
				}
			}
		}
		for (EnergyProducerOrConsumerGroup g : energyProducerGroupColl) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				if (e.getQuestionId() == elementIdRequested) {
					return e;
				}
			}
		}
		return null;
	}

	/**
	 * Counter will only return a count for anything that needs to be displayed
	 * in the inspector panel.
	 * 
	 * @return
	 */
	public int getCurrentIndex() {
		return eConsumOrProdCounter;
	}

	public void incrementCurrentIndex() {
		eConsumOrProdCounter++;
	}

	public int getCurrentQuestionCounter() {
		return currentQuestionCounter;
	}

	public void incrementCurrentQuestionCounter() {
		currentQuestionCounter++;
	}

	public EnergyProducerOrConsumer getEnergyProducerOrConsumer(
			ProducerOrConsumerEnum eType) {
		EnergyProducerOrConsumer r = null;
		for (EnergyProducerOrConsumerGroup g : getAllEnergyProducerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				if (e.getEnergyType() == eType) {
					// may as well return
					return e;
				}
			}
		}

		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
				if (e.getEnergyType() == eType) {
					// may as well return
					return e;
				}
			}
		}
		return r;
	}

	public boolean hasNameChanged() {
		return isNameChanged;
	}

	public boolean hasDescChanged() {
		return isDescChanged;
	}

	public String[] getColors() {
		int sizeOfArray = getAllEnergyConsumerGroups().size()
				+ getAllEnergyProducerGroups().size();
		String[] colorArray = new String[sizeOfArray];
		int i = 0;
		for (EnergyProducerOrConsumerGroup g : getAllEnergyProducerGroups()) {
			colorArray[i] = g.getColor();
			i++;
		}

		for (EnergyProducerOrConsumerGroup g : getAllEnergyConsumerGroups()) {
			colorArray[i] = g.getColor();
			i++;
		}
		return colorArray;
	}

	/**
	 * Returns the group based on the column and row passed in from the
	 * visualisation events.
	 * 
	 * @param row
	 *            number of the row in the data visualisation model
	 * @param col
	 *            number of the column in the data visualisation model
	 * @return null if there is no group that corresponds to the row or column
	 */
	public EnergyProducerOrConsumerGroup getEGroupFromModelRowAndColumn(
			int row, int col) {
		EnergyProducerOrConsumerGroup r = null;
		if (row == DataVisualisationEnergyModel.CONSUMERS_ROW) {
			for (EnergyProducerOrConsumerGroup g : energyConsumerGroupColl) {
				if (g.getGraphModelCol() == col) {
					return g;
				}
			}
		} else if (row == DataVisualisationEnergyModel.PRODUCERS_ROW) {
			for (EnergyProducerOrConsumerGroup g : energyProducerGroupColl) {
				if (g.getGraphModelCol() == col) {
					return g;
				}
			}
		}
		return r;
	}

	public String getPlainTextFromPlan() {
		String m = "";
		m += getPlanName() + "\n";
		m += "Energy Consumption:\n";
		for (EnergyProducerOrConsumerGroup g : this.energyConsumerGroupColl) {
			m += "\t" + g.getEnergyGroupName() + ": ";
			m += Utils.roundToTwoDecPlaces(g.getKWHdpForGroup()) + "\n";
		}
		m += "Total Consumption: "
				+ Utils.roundToTwoDecPlaces(getConsumerKWHdp()) + "\n\n";
		m += "Energy Production:\n";
		for (EnergyProducerOrConsumerGroup g : this.energyProducerGroupColl) {
			m += "\t" + g.getEnergyGroupName() + ": ";
			m += Utils.roundToTwoDecPlaces(g.getKWHdpForGroup()) + "\n";
		}
		m += "Total Production: "
				+ Utils.roundToTwoDecPlaces(getProducerKWHdp(false)) + "\n\n";
		return m;
	}
}
