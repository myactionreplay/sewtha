/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client.energyproducers;

import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumer;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.ProducerOrConsumerEnum;

/**
 * 
 * @author BarnabyK
 */
public abstract class SolarParent extends EnergyProducerOrConsumer {

	public SolarParent(EnergyProducerOrConsumerGroup group,
			ProducerOrConsumerEnum eType, int index, EnergyPlan planHolder) {
		super(group, eType, index, planHolder);
	}

	protected double getSunshinePowerWattsPerMetreSquared() {
		// Normally we would calculate this based on the Geographical variant of
		// the location
		// This uses latitide, average intensity of sunshine and cloud cover to
		// work out what it would
		// be for a particular location. I currently have an FAQ as the
		// calculation I perform does not come
		// out at the correct value therefore it is hardcoded here.
		return 100;
	}

	protected double getSunshirePowerWattsPerMetreSquaredSouthFacing() {
		// as above waiting on an FAQ to work a geographically based answer.
		// Just hard coded here.
		return 110;
	}
	
	
}
