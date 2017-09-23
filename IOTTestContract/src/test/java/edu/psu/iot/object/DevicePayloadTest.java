package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;

public class DevicePayloadTest {

	@Test
	// Should set the DevicePayload id and return the same id when calling the getter
	public void test_setAndGetId() {
		// Arrange
		Payload objectUnderTest = new Payload();
		String expectedId = "33258";
		String actualId;

		// Act
		objectUnderTest.setId(expectedId);
		actualId = objectUnderTest.getId();
		
		// Assert
		assertEquals(expectedId, actualId);
	}
	
	@Test
	// Should set the DevicePayload CreatedDateTime and return the same CreatedDateTime when calling the getter
	public void test_setAndGetCreatedDateTime() {
		// Arrange
		Payload objectUnderTest = new Payload();
		Date expectedDateTime = new Date();
		Date actualDateTime;
		
		// Act
		objectUnderTest.setCreatedDateTime(expectedDateTime);
		actualDateTime = objectUnderTest.getCreatedDateTime();
		
		// Assert
		assertEquals(expectedDateTime, actualDateTime);
	}
	
	@Test
	// Should set the DevicePayload PayloadData and return the same PayloadData when calling the getter
	public void test_setAndGetPayloadData() {
		// Arrange
		Payload objectUnderTest = new Payload();
		Map<String, String> expectedPayloadData = new HashMap<String, String>();
		expectedPayloadData.put("testKey", "testValue");
		Map<String, String> actualPayloadData;
		
		// Act
		objectUnderTest.setPayloadData(expectedPayloadData);
		actualPayloadData = objectUnderTest.getPayloadData();
		
		// Assert
		assertEquals(expectedPayloadData, actualPayloadData);
	}
}