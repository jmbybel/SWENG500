package edu.psu.iot.database;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.object.intf.JsonObject;

public class MongoDbPersistence {

	MongoClient mongoClient;
	DB aDatabase;
	
	public MongoDbPersistence() {
		mongoClient = new MongoClient();
		aDatabase = mongoClient.getDB("sweng500");
	}
	
	public Sensor createUpdateSensor(Sensor theSensor) {

		return saveObject(theSensor);
	}
	
	public Sensor readSensorById(String id) {
		return readObjectById(Sensor.class, id);
	}
	
	public boolean deleteSensor(String id) {
		return deleteObjectById(Sensor.class, id);
	}
	
	public Device createUpdateDevice(Device theDevice) {
		return saveObject(theDevice);
	}

	public Device readDeviceById(String id) {
		return readObjectById(Device.class, id);
	}
	
	public boolean deleteDevice(String id) {
		return deleteObjectById(Device.class, id);
	}
	
	
	private <T extends JsonObject> T saveObject(T target) {
		WriteResult<T, String> mongoJackWriteResult = null;

		DBCollection collection = aDatabase.getCollection(target.getClass().getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, (Class<T>)target.getClass(), String.class);
		if (target.getId() != null) {
			jackCollection.updateById(target.getId(), target);
		} else {
			mongoJackWriteResult = jackCollection.insert(target);
		}
		return mongoJackWriteResult.getSavedObject();
	}
	

	private <T extends JsonObject> T readObjectById(Class c, String id) {
		DBCollection collection = aDatabase.getCollection(c.getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, c, String.class);
		return jackCollection.findOneById(id);
	}
	
	private boolean deleteObjectById(Class c, String id) {
		DBCollection collection = aDatabase.getCollection(c.getSimpleName());
		JacksonDBCollection<JsonObject, String> jackCollection = JacksonDBCollection.wrap(collection, c, String.class);
		WriteResult wr = jackCollection.removeById(id);
		return wr.getWriteResult().wasAcknowledged();
	}
}
