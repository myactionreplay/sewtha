package org.sewtha.planmaker.client;

import com.google.gwt.junit.client.GWTTestCase;

public class EnergyPlanCloneTest extends GWTTestCase {

	private EnergyPlanClone instance = null;

	public void gwtSetUp() throws Exception {
		instance = EnergyPlan.getMainInstance().clone();
		instance.setPlanDescription("Test Desc");
		instance.setPlanName("Test Name");
	}

	public void gwtTearDown() throws Exception {
		instance = null;
	}

	@Override
	public String getModuleName() {
		return "org.sewtha.planmaker.Sewtha";
	}
	
	public void testClone(){
		EnergyProducerOrConsumer tSim = instance.getEnergyProducerOrConsumer(ProducerOrConsumerEnum.TRANSPORT_SAVINGS);
		EnergyProducerOrConsumer tOrig = EnergyPlan.getMainInstance().getEnergyProducerOrConsumer(ProducerOrConsumerEnum.TRANSPORT_SAVINGS);
		
		int i = 0;
		while (i < instance.getLongestYearImplementation()){
			
			instance.rollSimulatorOnAYear();
			i++;
		}

	}

}
