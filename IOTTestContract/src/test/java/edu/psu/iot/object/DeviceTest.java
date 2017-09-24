package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

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
		//assertEquals(0, newTestObject.getPayloads().size());
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
		String test = "000";
		objectUnderTest.setId(test);
		assertEquals(objectUnderTest.getId(), test);
	}
	
	//verify set and get expiration work as intended
	@Test
	public void test_setAndGetExpiration() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String strDate = "30/09/2017 11:30:12";
		Date dateTest = dateFormat.parse(strDate);
		objectUnderTest.setExpiration(dateTest);
		assertEquals(objectUnderTest.getExpiration(), dateTest);
	}
	
	//verify set and get sensors list works as intended
	@Test
	public void test_setAndGetSensors() {
		List<Sensor> sensors = new ArrayList<>();
		Sensor testSensor = new Sensor();
		testSensor.setId("30");
		sensors.add(testSensor);
		objectUnderTest.setSensors(sensors);
		assertEquals(objectUnderTest.getSensors(), sensors);
	}
	
	//TODO more work going to be needed for a full test!
	@Test
	public void jsonObjectFlattenAndExpand() {
		String jsonObject = objectUnderTest.toJson();
		System.out.println(jsonObject);
		Gson gson =new Gson();
		Device deviceFromJson = gson.fromJson(jsonObject, Device.class);
		assertEquals(deviceFromJson.getId(), objectUnderTest.getId());
		assertEquals(deviceFromJson.getSensors().get(0).getId(), objectUnderTest.getSensors().get(0).getId());
	}
	
	
	//utility method to get a sample device data up and running for tests
	private void populateObject() {
		objectUnderTest.setId("12345L");
		objectUnderTest.setName("a test object");
				
		List<Sensor> sensors = new ArrayList<>();
			Sensor junkSensor = new Sensor();
			junkSensor.setId("999L");
			sensors.add(junkSensor);
		objectUnderTest.setSensors(sensors);
	}
}
