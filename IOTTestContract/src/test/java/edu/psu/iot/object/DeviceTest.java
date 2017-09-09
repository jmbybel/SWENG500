package edu.psu.iot.object;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DeviceTest {

	Device objectUnderTest = new Device();
	
	// verify that cloning the device creates a copy whose ID and sent payloads are cleared out, altering the name to begin with "copy of",
	// and leaving all other fields intact
	// connects to story # 18
	@Test
	public void cloneObject_nullsOutId_clearsPayloads_addsCopyToName_otherFieldsIntact() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		assertNull(newTestObject.getId());
		assertEquals(0, newTestObject.getPayloads().size());
		assertEquals("copy of a test object", newTestObject.getName());
		assertEquals(newTestObject.getDataPushMaximumMilliseconds(),objectUnderTest.getDataPushMaximumMilliseconds());
		assertEquals(newTestObject.getDataPushMinimumMilliseconds(),objectUnderTest.getDataPushMinimumMilliseconds());
		
	}
	
	//verify set and get name work as intended
	@Test
	public void test_setAndGetName() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		newTestObject.setName("tester");
		assertEquals(newTestObject.getName(), "tester");
		
	}
	
	//verify set and get Id work as intended
	@Test
	public void test_setAndGetId() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		newTestObject.setId(33258L);
		long test = new Long(33258L);
		long objTest = new Long(newTestObject.getId());
		assertEquals(objTest, test);
		
	}
	
	//verify set and get inputFields work as intended
	@Test
	public void test_setAndGetInputFields() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		Map<String, String> sampleInputData = new HashMap<>();
		sampleInputData.put("new field", "new value");
		newTestObject.setInputFields(sampleInputData);
		assertEquals(newTestObject.getInputFields(), sampleInputData);
	}
	
	//verify set and get Payloads works as intended
	@Test
	public void test_setAndGetPayloads() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		DevicePayload aPayload = new DevicePayload();
		aPayload.setId(999L);
		List<DevicePayload> samplePayloads = new ArrayList<>();
		samplePayloads.add(aPayload);
		newTestObject.setPayloads(samplePayloads);
		assertEquals(newTestObject.getPayloads(), samplePayloads);
	}
	
	//verify set and get DataPushMinimumMilliseconds works as intended
	@Test
	public void test_setAndGetDataPushMinimumMilliseconds() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		newTestObject.setDataPushMinimumMilliseconds(400L);
		long test = new Long(400L);
		long objTest = new Long(newTestObject.getDataPushMinimumMilliseconds());
		assertEquals(objTest, test);
	}
	
	//verify set and get DataPushMaximumMilliseconds works as intended
	@Test
	public void test_setAndGetDataPushMaximumMilliseconds() {
		populateObject();
		Device newTestObject = new Device(objectUnderTest);
		newTestObject.setDataPushMaximumMilliseconds(2000L);
		long test = new Long(2000L);
		long objTest = new Long(newTestObject.getDataPushMaximumMilliseconds());
		assertEquals(objTest, test);
	}
	
	//utility method to get a sample device data up and running for tests
	private void populateObject() {
		objectUnderTest.setId(12345L);
		objectUnderTest.setDataPushMaximumMilliseconds(1000L);
		objectUnderTest.setDataPushMinimumMilliseconds(1000L);
		Map<String, String> sampleInputData = new HashMap<>();
		sampleInputData.put("a field", "a value");
		objectUnderTest.setInputFields(sampleInputData);
		objectUnderTest.setName("a test object");
		DevicePayload aPayload = new DevicePayload();
			aPayload.setId(999L);
		List<DevicePayload> samplePayloads = new ArrayList<>();
			samplePayloads.add(aPayload);
		objectUnderTest.setPayloads(samplePayloads);
		
	}
}
