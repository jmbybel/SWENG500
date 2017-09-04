package edu.psu.iot.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;

import org.junit.Test;

import edu.psu.iot.object.Device;

public class DeviceServiceImplTest {

	private DeviceServiceImpl objectUnderTest = new DeviceServiceImpl();
	
	//the expectation of this test is that the returning object would be a File with the same data as the initial Device, 
	//ready to be sent for download by the user
	@Test
	public void getDataGeneratorAsDownloadbleFile_producesFileWithSameData() {
		Device anExistingDevice = new Device();
		File f = objectUnderTest.getDataGeneratorAsDownloadbleFile(anExistingDevice );
		
		assertTrue(f != null);
	}
	
	@Test
	//This test should demonstrate that with a given File provided to the test, a Device can be successfully created with the same properties.
	//this test File should be saved in the filesystem on git.
	public void createDataGeneratorFromUploadedFile_GeneratorBuiltSuccessfully() {
		File dummyFile = null;
		Device createdDevice = objectUnderTest.createDataGeneratorFromUploadedFile(dummyFile);
		
		assertTrue(createdDevice != null);
	}
	
	
	//this should demonstrate that a data generator (Device) updates its inputFields map when an appropriate map is passed from the HTTP
	// client. the map should replace the existing one (unsure)?
	@Test
	public void updateDataGeneratorInputFields_objectIsUpdated() {
		
		fail();
	}
}
