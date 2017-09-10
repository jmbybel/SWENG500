package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

	Device objectUnderTest = new Device();
	
	@Before
	public void setUp() {
		populateObject();
	}
	
	// verify that cloning the device creates a copy whose ID and sent pay loads are cleared out, altering the name to begin with "copy of",
	// and leaving all other fields intact
	// connects to story # 18
	@Test
	public void cloneObject_nullsOutId_clearsPayloads_addsCopyToName_otherFieldsIntact() {
		Device newTestObject = new Device(objectUnderTest);
		assertNull(newTestObject.getId());
		assertEquals(0, newTestObject.getPayloads().size());
		assertEquals("copy of a test object", newTestObject.getName());
		
	}
	
	//verify set and get name work as intended
	@Test
	public void test_setAndGetName() {
		objectUnderTest.setName("tester");
		assertEquals(objectUnderTest.getName(), "tester");
		
	}
	
	//verify set and get Id work as intended
	@Test
	public void test_setAndGetId() {
		Long test = new Long(33258L);
		objectUnderTest.setId(test.longValue());
		assertEquals(objectUnderTest.getId(), test);
	}
	
	//verify set and get Payloads works as intended
	@Test
	public void test_setAndGetPayloads() {
		DevicePayload aPayload = new DevicePayload();
		aPayload.setId(999L);
		List<DevicePayload> samplePayloads = new ArrayList<>();
		samplePayloads.add(aPayload);
		objectUnderTest.setPayloads(samplePayloads);
		assertEquals(objectUnderTest.getPayloads(), samplePayloads);
	}
	
	
	//utility method to get a sample device data up and running for tests
	private void populateObject() {
		objectUnderTest.setId(12345L);
		objectUnderTest.setName("a test object");
		DevicePayload aPayload = new DevicePayload();
			aPayload.setId(999L);
		List<DevicePayload> samplePayloads = new ArrayList<>();
			samplePayloads.add(aPayload);
		objectUnderTest.setPayloads(samplePayloads);
		
	}
}
