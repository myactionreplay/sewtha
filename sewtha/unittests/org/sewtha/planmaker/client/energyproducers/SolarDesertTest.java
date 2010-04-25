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

public class SolarDesertTest {
	private SolarDesert instance = null;
	@Before
	public void setUp() throws Exception {
        instance = new SolarDesert(GeographicalConstants.getInstance(),
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
        double expResult = 0.0;
        double result = instance.getMinUsage();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxUsage method, of class SolarHeating.
     */
    @Test
    public void testGetMaxUsage() {
        System.out.println("getMaxUsage");
        double expResult = 3200;
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
        double expResult = 16.0;
        double result = instance.getKWHdp();
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of setNewUsage method, of class SolarHeating.
     */
    @Test
    public void testSetNewUsage() {
        System.out.println("setNewUsage");
        double changedValue = 3200;
        double expResult = 20.0;
        double result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 2560;
        expResult = 16;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 1120;
        expResult = 7;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 3200;
        expResult = 20.0;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 1760;
        expResult = 11;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 1568;
        expResult = 9.8;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

        changedValue = 480;
        expResult = 3;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.5);

    }

    /**
     * Test of getCurrentUsageValue method, of class SolarHeating.
     */
    @Test
    public void testGetCurrentUsageValue() {
        System.out.println("getCurrentUsageValue");
        double expResult = 2560;
        double result = instance.getCurrentUsageValue();
        assertEquals(expResult, result, 0.0);
    }



}
