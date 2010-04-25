package org.sewtha.planmaker.client.energyproducers;


import org.sewtha.planmaker.client.EnergyGroupingEnum;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;

import com.google.gwt.junit.client.GWTTestCase;

public class DeepOffshoreTest extends GWTTestCase {

	private DeepOffshore instance = null;

	public DeepOffshoreTest() {
	}

	public void gwtSetUp() {
		instance = new DeepOffshore(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.WIND,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
		EnergyPlan ep = EnergyPlan.getMainInstance();
		ep.init();
	}

	public void gwtTearDown() {
		instance = null;
	}

	/**
	 * Test of getDescription method, of class OnshoreWindFarms.
	 */
	public void testGetDescription() {

		System.out.println("getDescription");
		String expResult = "";
		String result = instance.getDescription();
		assertNotNull(expResult, result);

	}

	/**
	 * Test of getMinUsage method, of class OnshoreWindFarms.
	 */
	public void testGetMinUsage() {
		System.out.println("getMinUsage");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		//double expResult = 1.81373154520678;
		double expResult = 0;
		double result = instance.getMinUsage();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of getMaxUsage method, of class OnshoreWindFarms.
	 */
	public void testGetMaxUsage() {
		System.out.println("getMaxUsage");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 0.330;
		double result = instance.getMaxUsage();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of getQuestionForChange method, of class OnshoreWindFarms.
	 */
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
	public void testGetKWHdp() {
		System.out.println("getKWHdp");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 2.0;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 0.0181373154520678;
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 2.0;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .0725492618082714;
		expResult = 8;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .00906865772603392;
		expResult = 1;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .33;
		expResult = 36.39;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .5895;
		expResult = 65;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.3);
	}

	/**
	 * Test of getCurrentUsageValue method, of class OnshoreWindFarms.
	 */
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 0.0181373154520678;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

	@Override
	public String getModuleName() {
		return "org.sewtha.planmaker.Sewtha";
	}

}
