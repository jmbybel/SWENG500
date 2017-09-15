package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class SensorTest {

	private Sensor objectUnderTest = new Sensor();

	@Before
	public void setUp() throws ParseException {
		populateObject();
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
		aPayload.setId(999L);
		List<Payload> samplePayloads = new ArrayList<>();
		samplePayloads.add(aPayload);
		objectUnderTest.setPayloads(samplePayloads);
		assertEquals(objectUnderTest.getPayloads(), samplePayloads);
	}
	
	private void populateObject() throws ParseException {
		objectUnderTest.setId(123L);
		objectUnderTest.setName("Test Sensor");
		objectUnderTest.setValue(100L);
		objectUnderTest.setFloor(20L);
		objectUnderTest.setCeil(1000L);
		objectUnderTest.setSensorType("Sine");
		objectUnderTest.setInterval(1);
		objectUnderTest.setExpiration(new Date());
		objectUnderTest.setPayloads(new ArrayList<>());
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
