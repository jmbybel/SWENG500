package edu.psu.iot.persistence;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;

import edu.psu.iot.database.MongoDbPersistence;
import edu.psu.iot.object.Device;
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
		JacksonDBCollection<Device, String> jackCollection = null;
		WriteResult<Device, String> mongoJackWriteResult = null;
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
	 * Insert an object into the Device collection, and then check that the single Sensor added can be retrieved from the database
	 * and that its name matches the original one...
	 * Saving a Sensor as a separate Collection requries a bit more work, but may not be necessary if we really only save Devices here.
	 */
	@Test
	public void insertDeviceAndGetSensor() {

		Device resultDevice= null;
		 try {
			 Device d = new Device();
			 Sensor s = new Sensor();
			 s.setName("sampleSensor.");
			 d.getSensors().add(s);
			 d.setName("SAMPLER");
		 
			 
			 resultDevice = objectUnderTest.createUpdateDevice(d);
			 System.out.println(resultDevice.getId());
			 if (!d.getSensors().get(0).getName().equals(resultDevice.getSensors().get(0).getName())) {//rehydrated sensor is the same name as original
				 fail();
			 }
		 } finally {//if this is connected to the database, clean up.
			 objectUnderTest.deleteDevice(resultDevice.getId());
		 }
	}
	
}
