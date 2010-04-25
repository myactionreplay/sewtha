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

public class WoodTest {
	private Wood instance = null;

	@Before
	public void setUp() throws Exception {
		instance = new Wood(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.SOLAR,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	/**
	 * Test of getDescription method, of class SolarHeating.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("getDescription");

		String expResult = "";
		String result = instance.getDescription();
		assertNotNull(result);
	}

	/**
	 * Test of getMinUsage method, of class SolarHeating.
	 */
	@Test
	public void testGetMinUsage() {
		System.out.println("getMinUsage");
		double expResult = 0.07;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getMaxUsage method, of class SolarHeating.
	 */
	@Test
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		double expResult = 1000;
		double result = instance.getMaxUsage();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getQuestionForChange method, of class SolarHeating.
	 */
	@Test
	public void testGetQuestionForChange() {
		System.out.println("getQuestionForChange");
		String expResult = "";
		String result = instance.getQuestionForChange();
		assertNotNull(result);
	}

	/**
	 * Test of getKWHdp method, of class SolarHeating.
	 */
	@Test
	public void testGetKWHdp() {
		System.out.println("getKWHdp");
		double expResult = 5;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.5);
	}

	/**
	 * Test of setNewUsage method, of class SolarHeating.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 510.2;
		double expResult = 5;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 204.08;
		expResult = 2;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 102.04;
		expResult = 1;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 153.06;
		expResult = 1.5;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 408.163;
		expResult = 4;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 214.287;
		expResult = 2.1;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 346.938;
		expResult = 3.4;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

	}

	/**
	 * Test of getCurrentUsageValue method, of class SolarHeating.
	 */
	@Test
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
		double expResult = 510.2;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

}
