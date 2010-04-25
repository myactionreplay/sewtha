package org.sewtha.planmaker.client.energyproducers;





import org.sewtha.planmaker.client.EnergyGroupingEnum;
import org.sewtha.planmaker.client.EnergyPlan;
import org.sewtha.planmaker.client.EnergyProducerOrConsumerGroup;
import org.sewtha.planmaker.client.GeographicalConstants;

import com.google.gwt.junit.client.GWTTestCase;

public class BarrageTest extends GWTTestCase{

	private Barrage instance = null;

	public BarrageTest() {
	}


	public void gwtSetUp() {
		
		instance = new Barrage(GeographicalConstants.getInstance(),
				new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.HYDRO_WAVE_TIDAL, EnergyPlan.getMainInstance()),1, EnergyPlan.getMainInstance());
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
		double expResult = 1.0;
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
		double expResult = 0.83;
		double result = instance.getKWHdp();
		assertEquals(expResult, result, 0.0);

	}

	/**
	 * Test of setNewUsage method, of class OnshoreWindFarms.
	 */
	public void testSetNewUsage() {
		System.out.println("setNewUsage");
		double changedValue = 1;
		double expResult = 0.83;
		double result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .5;
		expResult = .415;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .75;
		expResult = 0.6225;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .25;
		expResult = 0.2075;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.2);
		changedValue = .10;
		expResult = 0.083;
		result = instance.setNewUsage(changedValue);
		assertEquals(expResult, result, 0.3);
	}

	/**
	 * Test of getCurrentUsageValue method, of class BarrageTest.
	 */
	public void testGetCurrentUsageValue() {
		System.out.println("getCurrentUsageValue");
		// OnshoreWindFarms instance = new
		// OnshoreWindFarms(GeographicalConstants.getInstance());
		double expResult = 1;
		double result = instance.getCurrentUsageValue();
		assertEquals(expResult, result, 0.0);
	}

	@Override
	public String getModuleName() {
		return "org.sewtha.planmaker.Sewtha";
	}

}
