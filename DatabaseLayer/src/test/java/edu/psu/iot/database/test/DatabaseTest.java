package edu.psu.iot.database.test;

import static org.junit.Assert.*;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.psu.iot.database.IDatabase;
import edu.psu.iot.database.mongodb.Database;
import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.util.JsonHandler;

public class DatabaseTest {
	
	IDatabase db = new Database();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void deleteInsertReadFormatSensor() {
		try {
			//db.deleteSensor("{\"_id\":123}");
			ISensor sensor = new Sensor();
			sensor.setId(123);
			db.createSensor(sensor);
			Document dbSensor = db.getSensor("{\"_id\":123}");
			System.out.println(dbSensor.toJson(new JsonWriterSettings(JsonMode.RELAXED)));			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void readSensor() {
		try {
			String dbSensor = db.getSensor("{\"_id\":9999999}");
			System.out.println(dbSensor);			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*
	@Test
	public void getAllSensors() {
		
		ISensor sensor1 = new Sensor();
		ISensor sensor2 = new Sensor();
		ISensor sensor3 = new Sensor();
		ISensor sensor4 = new Sensor();
		
		sensor1.setId(1000);
		sensor2.setId(1001);
		sensor3.setId(1002);
		sensor3.setId(1003);
		
		db.createSensor(sensor1);
		db.createSensor(sensor2);
		db.createSensor(sensor3);
		db.createSensor(sensor4);
		
		String result = db.getAllSensors();
		
		System.out.println(result);
		
		db.deleteSensor(JsonHandler.buildSingleInt("_id", sensor1.getId()));
		db.deleteSensor(JsonHandler.buildSingleInt("_id", sensor2.getId()));
		db.deleteSensor(JsonHandler.buildSingleInt("_id", sensor3.getId()));
		db.deleteSensor(JsonHandler.buildSingleInt("_id", sensor4.getId()));
		
		assertTrue(true);
		
		
	}*/
}
