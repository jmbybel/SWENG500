package edu.psu.iot.database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.object.base.JsonObject;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MongoRepository implements DatabaseRepository {
	private static final Logger logger = LogManager.getLogger();

	MongoClient mongoClient;
	DB aDatabase;
	
	public MongoRepository() {
		mongoClient = new MongoClient();
		aDatabase = mongoClient.getDB("sweng500");
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#createSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	public Sensor createSensor(Sensor theSensor) {

		return saveObject(theSensor);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#updateSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	public Sensor updateSensor(Sensor theSensor) {
		return updateObject(theSensor);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#readSensorById(java.lang.String)
	 */
	@Override
	public Sensor readSensorById(String id) {
		return readObjectById(Sensor.class, id);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#deleteSensor(java.lang.String)
	 */
	@Override
	public boolean deleteSensor(String id) {
		return deleteObjectById(Sensor.class, id);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#getAllDevices()
	 */
	@Override
	public List<Device> getAllDevices() {
		return findAll(Device.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#createDevice(edu.psu.iot.object.Device)
	 */
	@Override
	public Device createDevice(Device theDevice) {
		return saveObject(theDevice);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#updateDevice(edu.psu.iot.object.Device)
	 */
	@Override
	public Device updateDevice(Device theDevice) {
		return updateObject(theDevice);
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#readDeviceById(java.lang.String)
	 */
	@Override
	public Device readDeviceById(String id) {
		return readObjectById(Device.class, id);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#deleteDevice(java.lang.String)
	 */
	@Override
	public boolean deleteDevice(String id) {
		return deleteObjectById(Device.class, id);
	}
/*
	public Payload createPayload(Payload thePayload) {

		return saveObject(thePayload);
	}
	
	public Payload readPayloadById(String id) {
		return readObjectById(Payload.class, id);
	}
	
	public boolean deletePayload(String id) {
		return deleteObjectById(Payload.class, id);
	}
	*/
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#createResponseData(edu.psu.iot.object.ResponseData)
	 */
	@Override
	public ResponseData createResponseData(ResponseData thePayload) {

		return saveObject(thePayload);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#readResponseDataById(java.lang.String)
	 */
	@Override
	public ResponseData readResponseDataById(String id) {
		return readObjectById(ResponseData.class, id);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#deleteResponseData(java.lang.String)
	 */
	@Override
	public boolean deleteResponseData(String id) {
		return deleteObjectById(ResponseData.class, id);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.database.mongodb.DatabaseRepository#getAllPayloadResponsesBySensorId(java.lang.String)
	 */
	@Override
	public List<ResponseData> getAllPayloadResponsesBySensorId(String sensorId) {
		logger.debug("MongoDBPersistence.getAllPayloadResponsesBySensorId on ID: " + sensorId);
		//TODO it MAY be necessary to make this more generic, but for now, the only query we need to specify something for is the 
		// payload's sensorID

		DBCollection collection = aDatabase.getCollection(ResponseData.class.getSimpleName());
		JacksonDBCollection<ResponseData, String> jackCollection = JacksonDBCollection.wrap(collection, ResponseData.class, String.class);
		DBCursor<ResponseData> cursor = jackCollection.find(DBQuery.is("requestData.sensorId", sensorId));
		List<ResponseData> result = makeListFromDbCursorData(cursor);
		
		logger.debug("Found " + result.size() + " matches.");
		
		return result;
	}
	
	private <T extends JsonObject> T saveObject(T target) {//throws DatabaseActionException{
		logger.debug("MongoDbPersistence.saveObject on class " + target.getClass().getSimpleName());
		
		DBCollection collection = aDatabase.getCollection(target.getClass().getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, (Class<T>)target.getClass(), String.class);
		WriteResult<T, String> mongoJackWriteResult = jackCollection.insert(target);
		T result = mongoJackWriteResult.getSavedObject();
		
		logger.debug("Succeeded, object id is: " + result.getId());
		
		return result;
	}
	
	/**
	 * Update a document of a given type, returning the updated object
	 * @param target
	 * @return
	 */
	private <T extends JsonObject> T updateObject(T target) {//throws DatabaseActionException {
		logger.debug("MongoDbPersistence.updateObject on class " + target.getClass().getSimpleName() + " with ID: " + target.getId());
		
		DBCollection collection = aDatabase.getCollection(target.getClass().getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, (Class<T>)target.getClass(), String.class);
		WriteResult<T, String> mongoJackWriteResult = jackCollection.updateById(target.getId(), target);
		//if (mongoJackWriteResult.isUpdateOfExisting()) {
			return target;
		/*} else {
			throw new DatabaseActionException();
		}*/
	}
	
	/**
	 * Fetch one item of a given class, matching ID
	 * @param clazz
	 * @param id
	 * @return
	 */
	private <T extends JsonObject> T readObjectById(Class clazz, String id) {//throws DatabaseActionException{
		logger.debug("MongoDbPersistence.readObjectById on class " + clazz.getSimpleName() + " with ID: " + id);
		DBCollection collection = aDatabase.getCollection(clazz.getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, clazz, String.class);
		T result = jackCollection.findOneById(id);
		logger.debug("object found: " + result.toJson());
		return result;
	}
	
	/**
	 * Delete an object from the collection matching the class passed in here.
	 * @param clazz
	 * @param id
	 * @return
	 */
	private boolean deleteObjectById(Class clazz, String id) {//throws DatabaseActionException{ TODO
		logger.debug("MongoDbPersistence.deleteObjectById on class " + clazz.getSimpleName());
		
		DBCollection collection = aDatabase.getCollection(clazz.getSimpleName());
		JacksonDBCollection<JsonObject, String> jackCollection = JacksonDBCollection.wrap(collection, clazz, String.class);
		WriteResult wr = jackCollection.removeById(id);
		
		logger.debug("Database acknowledged delete: " + wr.getWriteResult().wasAcknowledged());
		
		return wr.getWriteResult().wasAcknowledged();
	}
	
	/**
	 * Given a class, find all elements in a MongoDB collection matching it and return as a list of Java objects of that class
	 * @param clazz the class name. The class should also be the name of the collection in the database.
	 * @return
	 */
	private <T extends JsonObject> List<T> findAll(Class clazz) {
		logger.debug("MongoDbPersistence.findAll on class " + clazz.getSimpleName());
		
		DBCollection collection = aDatabase.getCollection(clazz.getSimpleName());
		JacksonDBCollection<T, String> jackCollection = JacksonDBCollection.wrap(collection, clazz, String.class);
		DBCursor<T> cursor =  jackCollection.find();//no params = get everything.
		List<T> returnList = makeListFromDbCursorData(cursor);
		
		logger.debug("found " + returnList.size() + " matches");
		
		return returnList;
	}
	
	/*
	 * boilerplate
	 */
	private <T extends JsonObject> List<T> makeListFromDbCursorData(DBCursor<T> cursor) {
		List<T> returnList = new ArrayList<>();
		try {
			while(cursor.hasNext()) {
				returnList.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return returnList;
	}
	
}