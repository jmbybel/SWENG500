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
	public void testNumGetRunningSensors() {
		
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
	public void testJsonFromSensor() {
		ISensor sensor = new Sensor();
		sensor.setId(6);
		System.out.println(JsonHandler.jsonFromSensor(sensor));
	}
	
	@Test
	public void testAddSensorCount() {
		
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
		
		ISensor sensor4 = new Sensor();
		sensor4.setId(4);
		service.createSensor(sensor4);
		
		count = objectUnderTest.getNumberOfRunningSensors();
		System.out.println(count);
		assertEquals(count,"{\"count\":4}");
		
		service.deleteSensor(2);
		count = objectUnderTest.getNumberOfRunningSensors();
		System.out.println(count);
		assertEquals(count,"{\"count\":3}");
	}
	
	@Test
	public void testSetDestination() {
		String result = objectUnderTest.setDestinationIP("http://18.216.43.18:8081/contentListener");
		assertEquals("http://18.216.43.18:8081/contentListener", result);
		result = objectUnderTest.setDestinationIP("http://123.456.789.123:12344/contentListener");
		assertEquals("http://18.216.43.18:8081/contentListener", result);
		result = objectUnderTest.setDestinationIP("123.456.234/contentListener");
		assertEquals("http://18.216.43.18:8081/contentListener", result);
		result = objectUnderTest.setDestinationIP("http://123.123.123.123:1234");
		assertEquals("http://18.216.43.18:8081/contentListener", result);
	}
	
	@Test
	public void testGetAllSensors() {
		System.out.println(objectUnderTest.getAllSensors());
	}
	
	@Test
	public void testStartPause() {
		
		try {
			ISensor sensor = new Sensor();
			sensor.setId(100);
			System.out.println("The id of the sensor is: " + sensor.getId());
			String sensorJson =  JsonHandler.jsonFromSensor(sensor);
			System.out.println("The initial Sensor JSON is: " + sensorJson);
			System.out.println("Creating Sensor");
			objectUnderTest.createSensor(sensorJson);
			Thread.sleep(10000);
			System.out.println("Pause Sensor");
			objectUnderTest.pauseSensor("{\"_id\":100}");
			Thread.sleep(10000);
			System.out.println("Start Sensor");
			objectUnderTest.startSensor("{\"_id\":100}");
			//sensor.setMax(99);
			//sensor.setMin(97);
			//objectUnderTest.updateSensor(JsonHandler.jsonFromSensor(sensor));
			//System.out.println("The new min is: " + sensor.getMin());
			//System.out.println("The new max is: " + sensor.getMax());
			//System.out.println("The updated database entry: \n" + 
			//objectUnderTest.getSensor(JsonHandler.buildSingleInt("_id", sensor.getId())));
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		
		try {
			ISensor sensor = new Sensor();
			sensor.setId(100);
			System.out.println("The id of the sensor is: " + sensor.getId());
			String sensorJson =  JsonHandler.jsonFromSensor(sensor);
			System.out.println("The initial Sensor JSON is: " + sensorJson);
			objectUnderTest.createSensor(sensorJson);
			Thread.sleep(5000);
			objectUnderTest.pauseSensor("{\"_id\":100}");
			Thread.sleep(5000);
			objectUnderTest.startSensor("{\"_id\":100}");
			sensor.setMax(99);
			sensor.setMin(97);
			objectUnderTest.updateSensor(JsonHandler.jsonFromSensor(sensor));
			System.out.println("The new min is: " + sensor.getMin());
			System.out.println("The new max is: " + sensor.getMax());
			System.out.println("The updated database entry: \n" + 
			objectUnderTest.getSensor(JsonHandler.buildSingleInt("_id", sensor.getId())));
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSensorToJson() {
		try {
			ISensor sensor = new Sensor();
			sensor.setId(121);
			objectUnderTest.createSensor(JsonHandler.jsonFromSensor(sensor));
			Thread.sleep(3000);
			System.out.println(JsonHandler.jsonFromSensor(sensor));
			objectUnderTest.pauseSensor("{\"_id\":121}");
			System.out.println(JsonHandler.jsonFromSensor(sensor));
			objectUnderTest.startSensor("{\"_id\":121}");
			//objectUnderTest.deleteAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCreateSensor() {
		try {
			objectUnderTest.deleteAll();
			System.out.println("deleted");
			ISensor sensor = new Sensor();
			sensor.setId(123);
			objectUnderTest.createSensor(JsonHandler.jsonFromSensor(sensor));
			Thread.sleep(2500);
			String count = objectUnderTest.getNumberOfRunningSensors();
			System.out.println(count);
			assertEquals(count,"{\"count\":1}");
			System.out.println(objectUnderTest.getSensor("{\"_id\":123}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
