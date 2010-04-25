package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

public class SolarPVRoof extends SolarParent {
	// set them as per
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	private double solarPVPanelEfficiency = 0.2;
	private double minUsage = 0;

	public SolarPVRoof(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.SOLAR_PV, index, planHolder);
		this.geog = geog;
		// set them to the middle road plan
		currentUsage = 3.636364;
		calculatedKWHdp = 2.0;
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Solar Photovoltaic (roof)";
	}

	/**
	 * Could be returned to 0
	 * 
	 * @return
	 */
	public double getMinUsage() {
		return minUsage;
	}

	/**
	 * From pg 111 min usage in 2006
	 */
	public double get2006KWHdp(){
		return geog.get2006SolarPVRoofKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getSolarPVRoof2006Metric();
	}
	/**
	 * From Maximal plan
	 * 
	 * @return
	 */
	public double getMaxUsage() {
		return 10;
	}

	public String getQuestionForChange() {
		return "Please choose how many square metres "
				+ "for each person should be utilised for "
				+ "Solar Photovoltaic Electricity on roof tops.";
	}

	public double getKWHdp() {
		return calculatedKWHdp;
	}

	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double watts = getSunshirePowerWattsPerMetreSquaredSouthFacing()
				* currentUsage * solarPVPanelEfficiency;
		calculatedKWHdp = watts / NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		fireEventThroughContainer();
	}

	public double getCurrentUsageValue() {
		return currentUsage;
	}


	public String getUsageMetricString() {
		return " square metres for each person ";
	}

	public String getEquivalentMetricString() {
		return " one metre square Solar Panels";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat()
				.format(currentUsage * geog.getPopulation());
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		String s = getDecimalFormat().format(geog.getSolarPV2006Metric() * geog.getPopulation());
		return s;
	}
}
