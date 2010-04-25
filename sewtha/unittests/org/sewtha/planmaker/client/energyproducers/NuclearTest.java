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

public class NuclearTest {
	private Nuclear instance = null;

	public NuclearTest() {
	}

	@Before
	public void setUp() {
		instance = new Nuclear(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.NUCLEAR,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
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
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
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
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 106;
		double result = instance.getMaxUsage();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of getQuestionForChange method, of class OnshoreWindFarms.
	 */
	@Test
	public void testGetQuestionForChange() {
		System.out.println("getQuestionForChange");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
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
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 16;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 0;
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 0.0;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 3;
		expResult = 1;
		//the number of power stations is rounded up so here we need to floor
		//the kwhdp
		result = Math.floor(instance.setNewUsage(changedValue));
		assertEquals(expResult, result, 0.2);
		changedValue = 39;
		expResult = 16;
		result = Math.floor(instance.setNewUsage(changedValue));
		assertEquals(expResult, result, 0.2);
		changedValue = 24;
		expResult = 10;
		result = Math.floor(instance.setNewUsage(changedValue));
		assertEquals(expResult, result, 0.2);
		changedValue = 106;
		expResult = 44;
		result = Math.floor(instance.setNewUsage(changedValue));
		assertEquals(expResult, result, 0.3);
		changedValue = 55;
		expResult = 22.92;
		result = Math.floor(instance.setNewUsage(changedValue));
		assertEquals(expResult, result, 0.3);
		changedValue = 39;
		expResult = 16;
		result = Math.floor(instance.setNewUsage(changedValue));
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
		double expResult = 39;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}


}
