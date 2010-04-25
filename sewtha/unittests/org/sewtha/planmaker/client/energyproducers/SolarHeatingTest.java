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

public class SolarHeatingTest {
private SolarHeating instance = null;
	@Before
	public void setUp() throws Exception {
        instance = new SolarHeating(GeographicalConstants.getInstance(),
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
        double expResult = 0.014;
        double result = instance.getMinUsage();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxUsage method, of class SolarHeating.
     */
    @Test
    public void testGetMaxUsage() {
        System.out.println("getMaxUsage");
        double expResult = 10.0;
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
        double expResult = 1.0;
        double result = instance.getKWHdp();
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of setNewUsage method, of class SolarHeating.
     */
    @Test
    public void testSetNewUsage() {
        System.out.println("setNewUsage");
        double changedValue = 0.7272727272;
        double expResult = 1.0;
        double result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.7272727272;
        expResult = 1.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.7272727272;
        expResult = 1.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.7272727272;
        expResult = 1.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 10;
        expResult = 13.75;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.7272727272;
        expResult = 1.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 0.7272727272;
        expResult = 1.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

    }

    /**
     * Test of getCurrentUsageValue method, of class SolarHeating.
     */
    @Test
    public void testGetCurrentUsageValue() {
        System.out.println("getCurrentUsageValue");
        double expResult = 0.727273;
        double result = instance.getCurrentUsageValue();
        assertEquals(expResult, result, 0.01);
    }


}
