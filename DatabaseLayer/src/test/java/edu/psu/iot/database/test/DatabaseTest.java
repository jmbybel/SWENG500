package edu.psu.iot.database.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.psu.iot.database.IDatabase;
import edu.psu.iot.database.mongodb.Database;
import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.sensor.Sensor;

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
	public void insertAndReadSensor() {
		try {
			ISensor sensor = new Sensor();
			sensor.setId(123);
			db.createSensor(sensor);
			System.out.println(db.getSensor("{\"id\":123}"));
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
