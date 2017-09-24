package edu.psu.iot.database;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.object.intf.JsonObject;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MongoDbPersistence {

	MongoClient mongoClient;
	DB aDatabase;
	
	public MongoDbPersistence() {
		mongoClient = new MongoClient();
		aDatabase = mongoClient.getDB("sweng500");
	}
	
	public Sensor createSensor(Sensor theSensor) {

		return saveObject(theSensor);
	}
	
	public Sensor updateSensor(Sensor theSensor) {
		return updateObject(theSensor);
	}
	
	public Sensor readSensorById(String id) {
		return readObjectById(Sensor.class, id);
	}
	
	public boolean deleteSensor(String id) {
		return deleteObjectById(Sensor.class, id);
	}
	
	public Device createDevice(Device theDevice) {
		return saveObject(theDevice);
	}
	
	public Device updateDevice(Device theDevice) {
		return updateObject(theDevice);
	}

	public Device readDeviceById(String id) {
		return readObjectById(Device.class, id);
	}
	
	public boolean deleteDevice(String id) {
		return deleteObjectById(Device.class, id);
	}

	public Payload createPayload(Payload thePayload) {

		return saveObject(thePayload);
	}
	
	public Payload readPayloadById(String id) {
		return readObjectById(Payload.class, id);
	}
	
	public boolean deletePayload(String id) {
		return deleteObjectById(Payload.class, id);
	}
	
	public ResponseData createResponseData(ResponseData thePayload) {

		return saveObject(thePayload);
	}
	
	public ResponseData readResponseDataById(String id) {
		return readObjectById(ResponseData.class, id);
	}
	
	public boolean deleteResponseData(String id) {
		return deleteObjectById(ResponseData.class, id);
	}
	
	//TODO logging + throwing exceptions.
	
	private <T extends JsonObject> T saveObject(T target) {//throws DatabaseActionException{
		DBCollection collection = aDatabase.getCollection(target.getClass().getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, (Class<T>)target.getClass(), String.class);
		WriteResult<T, String> mongoJackWriteResult = jackCollection.insert(target);
		return mongoJackWriteResult.getSavedObject();
	}
	
	private <T extends JsonObject> T updateObject(T target) {//throws DatabaseActionException {
		DBCollection collection = aDatabase.getCollection(target.getClass().getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, (Class<T>)target.getClass(), String.class);
		WriteResult<T, String> mongoJackWriteResult = jackCollection.updateById(target.getId(), target);
		//if (mongoJackWriteResult.isUpdateOfExisting()) {
			return target;
		/*} else {
			throw new DatabaseActionException();
		}*/
	}
	
	private <T extends JsonObject> T readObjectById(Class c, String id) {//throws DatabaseActionException{
		DBCollection collection = aDatabase.getCollection(c.getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, c, String.class);
		return jackCollection.findOneById(id);
	}
	
	private boolean deleteObjectById(Class c, String id) {//throws DatabaseActionException{ TODO
		DBCollection collection = aDatabase.getCollection(c.getSimpleName());
		JacksonDBCollection<JsonObject, String> jackCollection = JacksonDBCollection.wrap(collection, c, String.class);
		WriteResult wr = jackCollection.removeById(id);
		return wr.getWriteResult().wasAcknowledged();
	}
}
