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

public class HydroElectricHighlandTest {

	private HydroElectricHighland instance;
	
	public HydroElectricHighlandTest(){
		
	}
    @Before
    public void setUp() {
        instance = new HydroElectricHighland(GeographicalConstants.getInstance(), 
        		new EnergyProducerOrConsumerGroup(EnergyGroupingEnum.HYDRO_WAVE_TIDAL,EnergyPlan.getMainInstance()),1,EnergyPlan.getMainInstance());
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
        double expResult = 2.30769230769231;
        double result = instance.getMinUsage();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of getMaxUsage method, of class OnshoreWindFarms.
     */
    @Test
    public void testGetMaxUsage() {
        System.out.println("getMaxUsage");
        double expResult = 20.0;
        double result = instance.getMaxUsage();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of getQuestionForChange method, of class OnshoreWindFarms.
     */
    @Test
    public void testGetQuestionForChange() {
        System.out.println("getQuestionForChange");
    //    OnshoreWindFarms instance = new OnshoreWindFarms(GeographicalConstants.getInstance());
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
//        OnshoreWindFarms instance = new OnshoreWindFarms(GeographicalConstants.getInstance());
        double expResult = 0.18;
        double result = instance.getKWHdp();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setNewUsage method, of class OnshoreWindFarms.
     */
    @Test
    public void testSetNewUsage() {
        System.out.println("setNewUsage");
        double changedValue = 2.30769230769231;
        double expResult = 0.18;
        double result = instance.setNewUsage(changedValue);
        assertEquals(result, expResult, 0.2);
        changedValue = 20;
        expResult = 1.56;
        result = instance.setNewUsage(changedValue);
        assertEquals(result, expResult, 0.2);
        changedValue = 12.82;
        expResult = 1;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.2);
        changedValue = 25.64;
        expResult = 2;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.2);
        changedValue = 64.10;
        expResult = 5;
        result = instance.setNewUsage(changedValue);
        assertEquals(expResult, result, 0.3);
    }

    /**
     * Test of getCurrentUsageValue method, of class OnshoreWindFarms.
     */
    @Test
    public void testGetCurrentUsageValue() {
        System.out.println("getCurrentUsageValue");
        double expResult = 2.30769230769231;
        double result = instance.getCurrentUsageValue();
        assertEquals(expResult, result, 0.0);
    }

}
