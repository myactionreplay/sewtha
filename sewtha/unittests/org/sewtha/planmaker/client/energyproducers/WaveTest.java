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

public class WaveTest {

	private Wave instance = null;

	public WaveTest() {
	}

	@Before
	public void setUp() {
		instance = new Wave(GeographicalConstants.getInstance(),
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
		double expResult = 50.0;
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
		double expResult = .3;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	@Test
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 24;
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 2.0;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 36;
		expResult = 3;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 50;
		expResult = 4.17;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 3.6;
		expResult = 0.3;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = 96;
		expResult = 8;
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
		double expResult = 3.6;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

}
