/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;
import org.sewtha.planmaker.client.NaturalConstants;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

/**
 * 
 * @author BarnabyK
 */
public class OnshoreWindFarms extends EnergyProducerOrConsumer {

	private double currentUsage;
	private double calculatedKWHdp;
	private GeographicalConstants geog;
	// tech variables could change at some point
	private double windTurbineEfficiency = 0.50;
	private double windTurbineDiameterMetres = 25;

	public OnshoreWindFarms(GeographicalConstants geog,
			EnergyProducerOrConsumerGroup group, int index, EnergyPlan planHolder) {
		// this is where the country would be picked
		super(group, ProducerOrConsumerEnum.ONSHORE_WIND, index, planHolder);
		this.geog = geog;
		// set as per middle road plan we do not want to fire event yet so set
		// directly
		currentUsage = 0.0181;
		calculatedKWHdp = 4.0;
		setIsDecimal(false);
		setAnyEquivalent(true);
	}

	public String getDescription() {
		return "Onshore Wind Farms";
	}

	public double getMinUsage() {
		return 0.0;
	}
	
	public double get2006KWHdp(){
		return geog.get2006WindKWHdp();
	}

	public double get2006KWHdpMetric(){
		return geog.getOnshoreWind2006Metric();
	}
	
	public double getMaxUsage() {
		//doubled from book on advice from DM
		return 0.20;
	}

	public String getQuestionForChange() {
		return "Please choose the Percentage of all available land you wish to use" +
				" for Onshore Wind Farms.";
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
		// as per page 264 and 265 of sewtha
		// calcualte the kinetic energy
		double kineticEnergy = ((NaturalConstants.DENSITY_OF_WIND_KG_PER_METRE_CUBED * (Math
				.pow(geog.getWindspeedMetresPerSecond(), 3))) * 0.5)
				* windTurbineEfficiency;
		// Consider the wind turbine diameter
		double incDiameter = kineticEnergy * Math.PI / 4
				* Math.pow(windTurbineDiameterMetres, 2);
		double incSpacing = incDiameter
				/ Math.pow(windTurbineDiameterMetres * 5, 2);
		double kwHDP = incSpacing
				* ((getCurrentUsageValue()) * geog
						.getAvailableLandOnshoreWindMetresSquaredPerPerson())
				/ NaturalConstants.CONVERT_WATTS_TO_KWHDP;
		calculatedKWHdp = kwHDP;
		// fire event so everyone knows
		fireEventThroughContainer();
	}

	public String getUsageMetricString() {
		return " of all available land";
	}

	public String getEquivalentMetricString() {
		return " wind turbines";
	}

	public String getPlannedEquivalentValue() {
		// time current usage by equivalent ratio
		String s = getNumberFormat().format(
				(getKWHdp() / geog.getWindTurbineKWhdp()));
		return s;
	}

	public String getTodayEquivalentStr() {
		// time minimum by equivalent ratio
		// use today current KWDp from pg 111
		String s = getNumberFormat().format(
				(geog.get2006WindKWHdp() / geog.getWindTurbineKWhdp()));
		return s;
	}
	
	
}
