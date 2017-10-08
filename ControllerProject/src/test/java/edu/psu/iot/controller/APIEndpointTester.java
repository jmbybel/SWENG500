package edu.psu.iot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.psu.iot.constants.ApiConstants;
import edu.psu.iot.database.MongoDbPersistence;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.DeviceServiceImpl;

public class APIEndpointTester {

	private APIEndpoint objectUnderTest = new APIEndpoint();
	
	
	private DeviceServiceImpl serviceLayer = new DeviceServiceImpl();
	
	@Mock
	private MongoDbPersistence mockDbLayer;
	
	private Gson gson = new Gson();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serviceLayer.setDatabaseAccess(mockDbLayer);
		objectUnderTest.setDeviceService(serviceLayer);
	}
	
	//verify that a device JSON sent to the API gets passed to the database layer and reaches the createDevice method
	@Test
	public void createDevice() {
		Device firstDevice = new Device();
		firstDevice.setName("a sample device");
		Device deviceWithId = new Device();
		deviceWithId.setId("123");
		
		when (mockDbLayer.createDevice(any(Device.class))).thenReturn(deviceWithId);
		
		String json = gson.toJson(firstDevice);
		System.out.println(json);
		
		String resultJson = objectUnderTest.createUpdateDevice(json);
		assertTrue(resultJson.contains(deviceWithId.getId()));
		verify(mockDbLayer, times(1)).createDevice(any(Device.class));
	}
	
	//Verify that if you attempt to create a device with bad JSON, you get an error message stating that the object could not be created from the JSON.
	@Test
	public void breakCreateDevice() {
		String invalidJson= "}{{";
		String result = objectUnderTest.createUpdateDevice(invalidJson);
		assertEquals(result, ApiConstants.COULD_NOT_CONVERT_FROM_JSON);
	}
	
	@Test
	public void updateDevice() {
		Device deviceWithId = new Device();
		deviceWithId.setId("123");
		
		when (mockDbLayer.updateDevice(any(Device.class))).thenReturn(new Device());
		objectUnderTest.createUpdateDevice(gson.toJson(deviceWithId));
		verify(mockDbLayer, times(1)).updateDevice(any(Device.class));
	}
	
	@Test
	public void deleteDevice() {
		String objectId = "123";
		when (mockDbLayer.deleteDevice(objectId)).thenReturn(true);
		String isTrue = objectUnderTest.deleteDevice(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_SUCCESS);
		verify(mockDbLayer, times(1)).deleteDevice(any(String.class));
	}
	
	@Test
	public void deleteDevice_badId() {
		String objectId = "123";
		when (mockDbLayer.deleteDevice(objectId)).thenReturn(false);
		String isTrue = objectUnderTest.deleteDevice(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_FAILED);
		verify(mockDbLayer, times(1)).deleteDevice(any(String.class));
	}
	
	@Test
	public void getDevice_returnsJsonDevice() {
		Device deviceToJson = new Device();
		deviceToJson.setId("1");
		deviceToJson.setName("a sample device");
		when(serviceLayer.getDeviceById(any(String.class))).thenReturn(deviceToJson);
		String jsonObject = objectUnderTest.getDeviceById("1");
		Gson gson = new Gson();
		Device deviceFromJson =  gson.fromJson(jsonObject, Device.class);
		assertEquals(deviceToJson.getId(), deviceFromJson.getId());
	}
	@Test
	public void getDevice_badId_errorJson() {
		when(serviceLayer.getDeviceById(any(String.class))).thenReturn(null);
		String jsonObject = objectUnderTest.getDeviceById("1");
		String[] errorText = jsonObject.split("error");
		if (errorText.length == 1) {
			fail();//if there is no error object in the returning JSON, fail.
		}
	}
	
	@Test
	public void getAllDevices() {
		List<Device> myDevs = new ArrayList<>();
			Device device1 = new Device();
			device1.setName("a sample device");
			Device device2 = new Device();
			device2.setName("a different device");
			myDevs.add(device1);
			myDevs.add(device2);
		when(serviceLayer.getAllDevices()).thenReturn(myDevs);
		String resultString = objectUnderTest.getAllDevices();
		assertTrue(resultString.contains("sample"));
		assertTrue(resultString.contains("different"));
	}
	
	//verify that a sensor JSON sent to the API gets passed to the database layer and reaches the createSensor method
	@Test
	public void createSensor() {
		Sensor theSensorToTest = new Sensor();
		theSensorToTest.setName("a sample sensor");
		Sensor secondSensor = new Sensor();
		secondSensor.setId("123");
		
		when (mockDbLayer.createSensor(any(Sensor.class))).thenReturn(secondSensor);
		String resultJson = objectUnderTest.createUpdateSensor(gson.toJson(theSensorToTest));
		assertTrue(resultJson.contains(secondSensor.getId()));
		verify(mockDbLayer, times(1)).createSensor(any(Sensor.class));
	}
	
	//test that passing through the createUpdateSensor method will trigger a DB update, if the sensor has an ID already
	@Test
	public void updateSensor() {
		Sensor sensorWithId = new Sensor();
		sensorWithId.setId("123");
		
		when (mockDbLayer.updateSensor(any(Sensor.class))).thenReturn(new Sensor());
		objectUnderTest.createUpdateSensor(gson.toJson(sensorWithId));
		verify(mockDbLayer, times(1)).updateSensor(any(Sensor.class));
	}
	
	@Test
	public void deleteSensor() {
		String objectId = "123";
		when (mockDbLayer.deleteSensor(objectId)).thenReturn(true);
		String isTrue = objectUnderTest.deleteSensor(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_SUCCESS);
		verify(mockDbLayer, times(1)).deleteSensor(any(String.class));
	}
	
	@Test
	public void deleteSensor_failedAtDb() {
		String objectId = "123";
		when (mockDbLayer.deleteSensor(objectId)).thenReturn(false);
		String isTrue = objectUnderTest.deleteSensor(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_FAILED);
		verify(mockDbLayer, times(1)).deleteSensor(any(String.class));
	}
	
	
	
	//Test that our API will call to the DeviceService and fetch a Sensor, then return that as a json object to the user
	@Test
	public void getSensor_returnsJsonSensor() {
		Sensor sensorToJson = new Sensor();
		sensorToJson.setId("1");
		when(serviceLayer.getSensorById(any(String.class))).thenReturn(sensorToJson);
		String jsonObject = objectUnderTest.getSensorById("1");
		Gson gson = new Gson();
		Sensor sensorFromJson =  gson.fromJson(jsonObject, Sensor.class);
		assertEquals(sensorToJson.getId(), sensorFromJson.getId());
		
	}
	
	@Test
	public void doSomething() {
		String json = "{\"id\":\"1\",\"floor\":words,\"ceil\":words,\"interval\":0,\"expiration\":\"Sep 30, 2017 2:05:52 PM\",\"payloads\":[]}";
		System.out.println(objectUnderTest.createUpdateSensor(json));
	}

	
	
	@Test
	public void getSensor_badId_errorJson() {
		when(serviceLayer.getDeviceById(any(String.class))).thenReturn(null);
		String jsonObject = objectUnderTest.getSensorById("1");
		String[] errorText = jsonObject.split("error");
		if (errorText.length == 1) {
			fail();//if there is no error object in the returning JSON, fail.
		}
	}

	//Verify that if you attempt to create a device with bad JSON, you get an error message stating that the object could not be created from the JSON.
	@Test
	public void breakCreateSensor() {
		String invalidJson= "}{{";
		String result = objectUnderTest.createUpdateSensor(invalidJson);
		assertEquals(result, ApiConstants.COULD_NOT_CONVERT_FROM_JSON);
	}
}
