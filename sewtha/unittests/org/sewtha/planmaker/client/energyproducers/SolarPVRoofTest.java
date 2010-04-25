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

public class SolarPVRoofTest {

	private SolarPVRoof instance = null;

	@Before
	public void setUp() throws Exception {
		instance = new SolarPVRoof(GeographicalConstants.getInstance(),
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
		double expResult = 0.0003;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);
	}

	@Test
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		double expResult = 10.0;
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
		double expResult = 2.0;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.5);
	}

	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
        double changedValue = 5.454545455;
        double expResult = 3.0;
        double result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.0;
        expResult = 0.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 5.454545455;
        expResult = 3.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 5.454545455;
        expResult = 3.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0;
        expResult = 0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 10;
        expResult = 5.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 3.636364;
        expResult = 2.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);
	}

	@Test
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
        double expResult = 3.636364;
        double result = instance.getCurrentUsageValue();
        assertEquals(expResult, result, 0.0);
	}



}
