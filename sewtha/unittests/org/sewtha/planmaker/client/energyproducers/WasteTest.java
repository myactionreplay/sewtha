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

public class WasteTest {
	private Waste instance = null;

	@Before
	public void setUp() throws Exception {
		instance = new Waste(GeographicalConstants.getInstance(),
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
		double expResult = 0.3;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getMaxUsage method, of class SolarHeating.
	 */
	@Test
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		double expResult = 2.12;
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
		double expResult = 1.1;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.5);
	}

	/**
	 * Test of setNewUsage method, of class SolarHeating.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 2.12;
		double expResult = 1.1;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 0.9615;
		expResult = 0.5;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 3.846;
		expResult = 2;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 5.769;
		expResult = 3;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 1.53846;
		expResult = 0.8;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 0.3846;
		expResult = 0.2;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

		changedValue = 1.0576;
		expResult = 0.55;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.5);

	}

	/**
	 * Test of getCurrentUsageValue method, of class SolarHeating.
	 */
	@Test
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
		double expResult = 2.12;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

}
