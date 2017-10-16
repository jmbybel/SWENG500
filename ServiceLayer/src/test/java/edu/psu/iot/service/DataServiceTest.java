package edu.psu.iot.service;

import static org.junit.Assert.fail;

import org.junit.Test;

import edu.psu.iot.service.impl.DataService;

public class DataServiceTest {

	private IDataService objectUnderTest = new DataService();
	
	//this should demonstrate that a data generator (Device) updates its inputFields map when an appropriate map is passed from the HTTP
	// client. the map should replace the existing one (unsure)?
	@Test
	public void updateDataGeneratorInputFields_objectIsUpdated() {
		
		fail();
	}
}
