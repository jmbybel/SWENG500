package edu.psu.iot.object;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceTest {

	MockDevice objectUnderTest;
	
	// verify that cloning the device creates a copy whose ID and sent payloads are cleared out, altering the name,
	// and leaving all other fields intact
	public void cloneObject_nullsOutId_clearsPayloads_addsCopyToName_otherFieldsIntact() {
		populateObject();
		MockDevice newTestObject = objectUnderTest.clone();
		assertNull(newTestObject.getId());
		assertEquals(0, newTestObject.getPayloads().size());
		assertEquals("copy of a test object", newTestObject.getName());
		assertEquals(newTestObject.getDataPushMaximumMilliseconds(),objectUnderTest.getDataPushMaximumMilliseconds());
		assertEquals(newTestObject.getDataPushMinimumMilliseconds(),objectUnderTest.getDataPushMinimumMilliseconds());
		
	}
	
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
