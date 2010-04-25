/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;
import org.sewtha.planmaker.client.Utils;

/**
 * 
 * @author BarnabyK
 */
public class SolarHeating extends SolarParent {

	// set them as per
	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	private double solarHeatingPanelEfficiency = 0.5;
	private double minUsage = 0;

	public SolarHeating(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup eGroup, int index, EnergyPlan planHolder) {
		super(eGroup, ProducerOrConsumerEnum.SOLAR_HEATING, index, planHolder);
		this.geog = geog;
		// set them to the middle road plan
		currentUsage = 0.727273;
		calculatedKWHdp = 1.0;
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Solar Heating";
	}

	/**
	 * 
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
		return geog.get2006SolarHeatingKWHdp();
	}
	
	public double get2006KWHdpMetric(){
		return geog.getSolarHeating20006Metric();
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
		return "Please choose how many square metres for each person should be utilised for Solar Heating on roof tops.";
	}

	public double getKWHdp() {
		return Utils.roundToTwoDecPlaces(calculatedKWHdp);
	}

	public double setNewUsage(double changedValue) {
		currentUsage = changedValue;
		calculateNewKWHdp();
		return getKWHdp();
	}

	private void calculateNewKWHdp() {
		double watts = getSunshirePowerWattsPerMetreSquaredSouthFacing()
				* currentUsage * solarHeatingPanelEfficiency;
		calculatedKWHdp = watts / NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		fireEventThroughContainer();
	}

	public double getCurrentUsageValue() {
		return currentUsage;
	}
	public String getUsageMetricString(){
		return " square metres for each person ";
	}
	public String getEquivalentMetricString(){
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
		String s = getNumberFormat().format(geog.getSolarHeating20006Metric() * geog.getPopulation());
		return s;
	}
	
	
}
