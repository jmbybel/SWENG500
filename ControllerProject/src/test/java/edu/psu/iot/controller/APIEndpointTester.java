package edu.psu.iot.controller;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;

import edu.psu.iot.contract.DeviceService;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;

public class APIEndpointTester {

	private APIEndpoint objectUnderTest = new APIEndpoint();
	
	@Mock
	private DeviceService mockService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest.setDeviceService(mockService);
	}
	
	//Test that our API will call to the DeviceService and fetch a Device, then return that as a json object to the user
	// furthermore, test that the JSON device is the original device rebuilt.
	@Test
	public void getDevice_returnsJsonDevice() {
		Device deviceToJson = new Device();
		deviceToJson.setId("1");
		deviceToJson.setName("a sample device");
		when(mockService.getDeviceById(any(String.class))).thenReturn(deviceToJson);
		String jsonObject = objectUnderTest.getDeviceById("1");
		Gson gson = new Gson();
		Device deviceFromJson =  gson.fromJson(jsonObject, Device.class);
		assertEquals(deviceToJson.getId(), deviceFromJson.getId());
	}

	public void getDevice_badId_errorJson() {
		when(mockService.getDeviceById(any(String.class))).thenReturn(null);
		String jsonObject = objectUnderTest.getDeviceById("1");
		String[] errorText = jsonObject.split("error");
		if (errorText.length == 1) {
			fail();//if there is no error object in the returning JSON, fail.
		}
	}
	
	//Test that our API will call to the DeviceService and fetch a Sensor, then return that as a json object to the user
	// furthermore, test that the JSON sensor is the original sensor rebuilt.
	@Test
	public void getSensor_returnsJsonSensor() {
		Sensor sensorToJson = new Sensor();
		sensorToJson.setId("1");
		when(mockService.getSensorById(any(String.class))).thenReturn(sensorToJson);
		String jsonObject = objectUnderTest.getSensorById("1");
		Gson gson = new Gson();
		Sensor sensorFromJson =  gson.fromJson(jsonObject, Sensor.class);
		assertEquals(sensorToJson.getId(), sensorFromJson.getId());
		
	}

	public void getSensor_badId_errorJson() {
		when(mockService.getDeviceById(any(String.class))).thenReturn(null);
		String jsonObject = objectUnderTest.getSensorById("1");
		String[] errorText = jsonObject.split("error");
		if (errorText.length == 1) {
			fail();//if there is no error object in the returning JSON, fail.
		}
	}
}

