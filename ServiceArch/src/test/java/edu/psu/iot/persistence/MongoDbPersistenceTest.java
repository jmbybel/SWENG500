package edu.psu.iot.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;

import edu.psu.iot.database.MongoDbPersistence;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public class MongoDbPersistenceTest {

	MongoDbPersistence objectUnderTest = new MongoDbPersistence();
	
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
	 * perform all CRUD operations for a device.
	 */
	@Test
	public void deviceCrud() {
			 Device initialDevice = new Device();
			 initialDevice.setName("SAMPLER");
			 Device resultDevice = objectUnderTest.createDevice(initialDevice);//create
			 resultDevice.setName("NewName");
			 System.out.println(resultDevice.getId());
			 objectUnderTest.updateDevice(resultDevice);//update
			 Device readResultDevice = objectUnderTest.readDeviceById(resultDevice.getId());//read
			 assertEquals(resultDevice.getName(), readResultDevice.getName());

			 assertTrue(objectUnderTest.deleteDevice(resultDevice.getId()));
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
	@Test
	public void payloadCrd() {
			 Payload initialObject = new Payload();
			 initialObject.setCreatedDateTime(new Date());
			 Payload resultPayload = objectUnderTest.createPayload(initialObject);//create
			 Payload readResult = objectUnderTest.readPayloadById(resultPayload.getId());//read
			 assertEquals(resultPayload.getCreatedDateTime(), readResult.getCreatedDateTime());
			 assertTrue(objectUnderTest.deletePayload(resultPayload.getId()));

	}
	
	/**
	 * ResponseData crud, also does not have Update operation available.
	 */
	@Test
	public void responseDataCrd() {
		ResponseData initialObject = new ResponseData();
			 initialObject.setCreatedDateTime(new Date());
			 ResponseData resultPayload = objectUnderTest.createResponseData(initialObject);//create
			 ResponseData readResult = objectUnderTest.readResponseDataById(resultPayload.getId());//read
			 assertEquals(resultPayload.getCreatedDateTime(), readResult.getCreatedDateTime());
			 assertTrue(objectUnderTest.deleteResponseData(resultPayload.getId()));

	}
	
	/**
	 * Insert a device into the database, then delete it. then try to read that device's ID again. Should come up null.
	 */
	@Test
	public void readNothingFromDatabase() {
		 Device initialDevice = new Device();
		 initialDevice = objectUnderTest.createDevice(initialDevice);//create
		 objectUnderTest.deleteDevice(initialDevice.getId());
		 Device readResultDevice = objectUnderTest.readDeviceById(initialDevice.getId());//read
		 assertNull(readResultDevice);
	}

}