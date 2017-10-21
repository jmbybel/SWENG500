package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;

import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.impl.DataService;

public class APIEndpointTest {

	private APIEndpoint objectUnderTest = new APIEndpoint();
	
	
	@Mock
	private DataService serviceLayer;
	
	
	private Gson gson = new Gson();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest.setDeviceService(serviceLayer);
	}
	
	
	@Test
	public void getPayloadsBySensorId() {
		List<Payload> responses = new ArrayList<>();
			responses.add(new Payload());
			Payload populatedResponse = new Payload();
			populatedResponse.setId("SOME ID");
			responses.add(populatedResponse);
		when(serviceLayer.getAllPayloadsBySensor(any(String.class))).thenReturn(responses);
		
		String resultString = objectUnderTest.getAllPayloadsBySensor("asdf");
		
		assertTrue(resultString.contains("SOME ID"));
	}
	
	//verify that a sensor JSON sent to the API gets passed to the database layer and reaches the createSensor method
	@Test
	public void createSensor() {
		Sensor theSensorToTest = new Sensor();
		theSensorToTest.setName("a sample sensor");
		Sensor secondSensor = new Sensor();
		secondSensor.setId("123");
		
		when (serviceLayer.insertUpdateSensor(any(Sensor.class))).thenReturn(secondSensor);
		String resultJson = objectUnderTest.createUpdateSensor(gson.toJson(theSensorToTest));
		assertTrue(resultJson.contains(secondSensor.getId()));
		verify(serviceLayer, times(1)).insertUpdateSensor(any(Sensor.class));
	}
	
	//test that passing through the createUpdateSensor method will trigger a DB update, if the sensor has an ID already
	@Test
	public void updateSensor() {
		Sensor sensorWithId = new Sensor();
		sensorWithId.setId("123");
		
		when (serviceLayer.insertUpdateSensor(any(Sensor.class))).thenReturn(new Sensor());
		objectUnderTest.createUpdateSensor(gson.toJson(sensorWithId));
		verify(serviceLayer, times(1)).insertUpdateSensor(any(Sensor.class));
	}
	
	@Test
	public void deleteSensor() {
		String objectId = "123";
		when (serviceLayer.deleteSensor(objectId)).thenReturn(true);
		String isTrue = objectUnderTest.deleteSensor(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_SUCCESS);
		verify(serviceLayer, times(1)).deleteSensor(any(String.class));
	}
	
	@Test
	public void deleteSensor_failedAtDb() {
		String objectId = "123";
		when (serviceLayer.deleteSensor(objectId)).thenReturn(false);
		String isTrue = objectUnderTest.deleteSensor(objectId);
		assertEquals(isTrue, ApiConstants.DELETE_FAILED);
		verify(serviceLayer, times(1)).deleteSensor(any(String.class));
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
		when(serviceLayer.getSensorById(any(String.class))).thenReturn(null);
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
