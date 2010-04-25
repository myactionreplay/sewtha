package org.sewtha.planmaker.client;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataVisualisationEnergyModelTest {

	private DataVisualisationEnergyModel instance = null;
	@Before
	public void setUp() throws Exception {
		instance = DataVisualisationEnergyModel.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	instance = null;
	}

	@Test
	public void testGetDataTableForVis() {
		int noOfColumns = instance.getDataTableForVis().getNumberOfColumns();
		assertEquals(noOfColumns, 10);
	}

}
