package org.sewtha.planmaker.client.energyconsumers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sewtha.planmaker.client.EnergyGroupingEnum;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;

public class ElectricGadgetsTest {
	private ElectricGadgets instance = null;

	public ElectricGadgetsTest() {
	}

	@Before
	public void setUp() {
		instance = new ElectricGadgets(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(
						EnergyGroupingEnum.HEATING_SAVINGS, EnergyPlan.getMainInstance()),1, EnergyPlan.getMainInstance());
	}

	@After
	public void tearDown() {
		instance = null;
	}

	/**
	 * Test of getDescription method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetDescription() {

		System.out.println("getDescription");
		String expResult = "";
		String result = instance.getDescription();
		assertNotNull(expResult, result);

	}

	/**
	 * Test of getMinUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetMinUsage() {
		System.out.println("getMinUsage");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 0;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of getMaxUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		double expResult = 100.0;
		double result = instance.getMaxUsage();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of getQuestionForChange method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetQuestionForChange() {
		System.out.println("getQuestionForChange");
		String expResult = "";
		String result = instance.getQuestionForChange();
		assertNotNull(expResult, result);

	}

	/**
	 * Test of getKWHdp method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetKWHdp() {
		System.out.println("getKWHdp");
		double expResult = 18;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 10;
		double expResult = 16.2;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 0;
		expResult = 18;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 15;
		expResult = 15.3;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 28;
		expResult = 12.96;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 89;
		expResult = 1.98;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.3);
		changedValue = 100;
		expResult = 0;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.3);
	}

	/**
	 * Test of getCurrentUsageValue method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 0;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}


}
