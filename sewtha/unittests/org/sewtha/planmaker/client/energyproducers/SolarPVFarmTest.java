package org.sewtha.planmaker.client.energyproducers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sewtha.planmaker.client.EnergyGroupingEnum;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;

public class SolarPVFarmTest {

	private SolarPVFarm instance = null;

	@Before
	public void setUp() throws Exception {
		instance = new SolarPVFarm(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.SOLAR,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescription() {
		System.out.println("getDescription");
		String expResult = "";
		String result = instance.getDescription();
		assertNotNull(result);
	}

	@Test
	public void testGetMinUsage() {
		System.out.println("getMinUsage");
		double expResult = 0.000;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);
	}

	@Test
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		double expResult = 200.0;
		double result = instance.getMaxUsage();
		assertEquals(expResult, result, 0.0);

	}

	@Test
	public void testGetQuestionForChange() {
		System.out.println("getQuestionForChange");
		String expResult = "";
		String result = instance.getQuestionForChange();
		assertNotNull(result);
	}

	@Test
	public void testGetKWHdp() {
		System.out.println("getKWHdp");
		double expResult = 0.0;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.5);
	}

	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
        double changedValue = 20.0;
        double expResult = 5.0;
        double result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 34;
        expResult = 8.5;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 616;
        expResult = 154;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 708;
        expResult = 177;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 776;
        expResult = 194;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 356;
        expResult = 89;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 200;
        expResult = 50.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);
	}

	@Test
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
        double expResult = 0.0;
        double result = instance.getCurrentUsageValue();
        assertEquals(expResult, result, 0.0);
	}
}
