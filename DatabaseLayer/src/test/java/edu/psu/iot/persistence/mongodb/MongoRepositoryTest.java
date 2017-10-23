package edu.psu.iot.persistence.mongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.database.mongodb.MongoRepository;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;

public class MongoRepositoryTest {

	DatabaseRepository objectUnderTest = new MongoRepository();
	
	@Before
	public void setUp() {
		
	}
	
	/**
	 * should find a copy of the mongo database running on localhost:27017, default port for MongoDB
	 * Fails outright if the mongoDB instance is not active.
	 */
	@Test
	public void testLocalhostDatabaseConnectivity() {
		 try {

			MongoClient mongoClient = new MongoClient();
		 } catch (MongoSocketOpenException ex) {
			 System.out.println("database not active, failing test");
			 fail();
		 } catch (Exception ex) {
			 System.out.println("unknown exception, failing test, look at the stack trace.");
			 ex.printStackTrace();
			 fail();
		 }
	}
	
	/**
	 * perform all CRUD operations for a sensor.
	 */
	@Test
	public void sensorCrud() {
			 Sensor initialObject = new Sensor();
			 initialObject.setName("sampleSensor.");
			 Sensor resultSensor = objectUnderTest.createSensor(initialObject);//create
			 resultSensor.setName("NewName");
			 objectUnderTest.updateSensor(resultSensor);//update
			 Sensor readResult = objectUnderTest.readSensorById(resultSensor.getId());//read
			 assertEquals(resultSensor.getName(), readResult.getName());
			 assertTrue(objectUnderTest.deleteSensor(resultSensor.getId()));
	}
	
	/**
	 * Payload does not have Update operations available.
	 */
	/*
	@Test
	public void payloadCrd() {
			 Payload initialObject = new Payload();
			 initialObject.setCreatedDateTime(new Date());
			 Payload resultPayload = objectUnderTest.createPayload(initialObject);//create
			 Payload readResult = objectUnderTest.readPayloadById(resultPayload.getId());//read
			 assertEquals(resultPayload.getCreatedDateTime(), readResult.getCreatedDateTime());
			 assertTrue(objectUnderTest.deletePayload(resultPayload.getId()));

	}
	*/
	
	@Test
	public void getAllSensors() {
		
		objectUnderTest.getAllSensors();
	}

	
	/**
	 * Add 3 pieces of ResponseData to the database, then query the DB for the two with a requestData sensor ID of "asdf"
	 */
	@Test
	public void getAllPayloadsResponsesBySensorId() {
		//TODO
		Payload initialObject = new Payload();
		initialObject.setSensorId("asdf");
		
		Payload success1 = objectUnderTest.createPayload(initialObject);//create
		Payload success2 = objectUnderTest.createPayload(initialObject);//create 2
		
		initialObject.setSensorId("fdsa");
		Payload success3 = objectUnderTest.createPayload(initialObject);//create 3, this one has a different payload sensorID
		
		String sensorId = "asdf";
		
		List<Payload> results = objectUnderTest.getAllPayloadsBySensorId(sensorId);
		
		//cleanup.
		objectUnderTest.deletePayload(success1.getId());
		objectUnderTest.deletePayload(success2.getId());
		objectUnderTest.deletePayload(success3.getId());
		assertEquals(results.size(), 2);
	}
	
	/**
	 * Insert a device into the database, then delete it. then try to read that device's ID again. Should come up null.
	 */
	@Test
	public void readNothingFromDatabase() {
		 Sensor initialDevice = new Sensor();
		 initialDevice = objectUnderTest.createSensor(initialDevice);//create
		 objectUnderTest.deleteSensor(initialDevice.getId());
		 Sensor readResultDevice = objectUnderTest.readSensorById(initialDevice.getId());//read
		 assertNull(readResultDevice);
	}

}