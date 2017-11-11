package edu.psu.iot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.service.impl.DataService;
import edu.psu.iot.util.JsonHandler;

public class DataServiceTest {

	private IDataService objectUnderTest = new DataService();
	
	//this should demonstrate that a data generator (Device) updates its inputFields map when an appropriate map is passed from the HTTP
	// client. the map should replace the existing one (unsure)?
	@Test
	public void testBuildSingleInt() {
		String value = "value";
		String output = JsonHandler.buildSingleInt(value, 23);
		System.out.println(output);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(output);
			int x = node.findValue(value).asInt();
			assertTrue(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error when parsing JSON");
		}
	}
	
	@Test
	public void testGetRunningSensors() {
		
		ISensorService service = new SensorService();
		service.initialize();
		ISensor sensor = new Sensor();
		ISensor sensor2 = new Sensor();
		ISensor sensor3 = new Sensor();
		
		sensor.setId(1);
		sensor2.setId(2);
		sensor3.setId(3);
		
		service.createSensor(sensor);
		service.createSensor(sensor2);
		service.createSensor(sensor3);
		
		String count = objectUnderTest.getNumberOfRunningSensors();
		System.out.println(count);
		assertEquals(count,"{\"count\":3}");
	}
	
	@Test
	public void testSetDestination() {
		boolean result = objectUnderTest.setDestinationIP("http://18.216.43.18:8081/contentListener");
		assertTrue(result);
		result = objectUnderTest.setDestinationIP("http://123.456.789.123:12344/contentListener");
		assertTrue(result);
		result = objectUnderTest.setDestinationIP("123.456.234/contentListener");
		assertFalse(result);
		result = objectUnderTest.setDestinationIP("http://123.123.123.123:1234");
		assertFalse(result);
	}
	
	@Test
	public void testGetAllSensors() {
		System.out.println(objectUnderTest.getAllSensors());
	}
}
