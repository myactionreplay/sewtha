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

public class OnshoreWindFarmsTest {
	private OnshoreWindFarms instance = null;

	public OnshoreWindFarmsTest() {
	}

	@Before
	public void setUp() {
		instance = new OnshoreWindFarms(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.WIND,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
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
		double expResult = 0.0;
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
		double expResult = 10.0;
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
		double expResult = 4.0;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 1.81;
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 4.0;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .91;
		expResult = 2;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 1.81;
		expResult = 4;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 7.25;
		expResult = 16;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 10;
		expResult = 22.05;
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
		double expResult = 1.81;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

}
