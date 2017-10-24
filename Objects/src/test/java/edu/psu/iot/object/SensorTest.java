package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class SensorTest {

	private Sensor objectUnderTest = new Sensor();

	@Before
	public void setUp() throws ParseException {
		populateObject();
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
		String test = "32555";
		objectUnderTest.setId(test);
		assertEquals(objectUnderTest.getId(), test);
	}
	
	//verify set and get value work as intended
	@Test
	public void test_setAndGetValue() {
		Long test = new Long(80L);
		objectUnderTest.setValue(test.longValue());
		assertEquals(objectUnderTest.getValue(), test);
	}
	
	//verify set and get floor work as intended
	@Test
	public void test_setAndGetFloor() {
		Long test = new Long(20L);
		objectUnderTest.setFloor(test.longValue());
		assertEquals(objectUnderTest.getFloor(), test);
	}
	
	//verify set and get ceil work as intended
	@Test
	public void test_setAndGetCeil() {
		Long test = new Long(1002L);
		objectUnderTest.setCeil(test.longValue());
		assertEquals(objectUnderTest.getCeil(), test);
	}
	
	//verify set and get sensor type work as intended
	@Test
	public void test_setAndGetSensorType() {
		objectUnderTest.setSensorType("Random");
		assertEquals(objectUnderTest.getSensorType(), "Random");
	}
	
	//verify set and get interval work as intended
	@Test
	public void test_setAndGetInterval() {
		int test = 2;
		objectUnderTest.setInterval(test);
		assertEquals(objectUnderTest.getInterval(), test);
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
	
	//verify set and get Payloads works as intended
	@Test
	public void test_setAndGetPayloads() {
		Payload aPayload = new Payload();
		aPayload.setSensorId("928");
		objectUnderTest.setPayloadConfiguration(aPayload);
		assertEquals(objectUnderTest.getPayloadConfiguration(), aPayload);
	}
	
	private void populateObject() throws ParseException {
		objectUnderTest.setId("111");
		objectUnderTest.setName("Test Sensor");
		objectUnderTest.setValue(100L);
		objectUnderTest.setFloor(20L);
		objectUnderTest.setCeil(1000L);
		objectUnderTest.setSensorType("Sine");
		objectUnderTest.setInterval(1);
		objectUnderTest.setExpiration(new Date());
	}

	//TODO more work going to be needed for a full test!
	@Test
	public void jsonObjectFlattenAndExpand() {
		String jsonObject = objectUnderTest.toJson();
		Gson gson =new Gson();
		Sensor deviceFromJson = gson.fromJson(jsonObject, Sensor.class);
		assertEquals(deviceFromJson.getId(), objectUnderTest.getId());
	}
	
}
