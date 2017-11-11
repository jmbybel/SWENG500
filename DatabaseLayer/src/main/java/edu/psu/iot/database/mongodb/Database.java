package edu.psu.iot.database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


import edu.psu.iot.database.IDatabase;
import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.util.JsonHandler;


public class Database implements IDatabase {
	
	MongoClient mongoClient = new MongoClient();
	String databaseName = "iot";
	MongoDatabase database = mongoClient.getDatabase(databaseName);
	MongoCollection<Document> sensorCollection = database.getCollection("sensors");
	MongoCollection<Document> payloadCollection = database.getCollection("payloads");
	
	public Database() {
		this.mongoClient = new MongoClient();
	}
	
	public Database(String host, int port) {
		this.mongoClient = new MongoClient(host, port);
	}
	
	public Database(String inputConnectionString) {
		 MongoClientURI connectionString = new MongoClientURI(inputConnectionString);
		 mongoClient = new MongoClient(connectionString);
	}

	@Override
	public String getAllSensors() {
		System.out.println("Entered getAllSensors");
		MongoCursor<Document> cursor = sensorCollection.find().iterator();
		String all = "[";
		StringBuilder builder = new StringBuilder(all);
		try {
		    while (cursor.hasNext()) {
		    	builder.append(cursor.next().toJson(new JsonWriterSettings(JsonMode.RELAXED)));
		    	builder.append(",");
		    }
		    int length = builder.length();
		    builder.delete(length - 1, length);
		    builder.append("]");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		    cursor.close();
		}

		return builder.toString();
	}

	@Override
	public String getSensor(String jsonId) {
		Document document = sensorCollection.find(Filters.eq("_id", JsonHandler.getIdFromJson(jsonId))).first();
		return document.toJson(new JsonWriterSettings(JsonMode.RELAXED));
	}

	@Override
	public boolean createSensor(ISensor sensor) {
		boolean success = false;
		try {
			sensorCollection.insertOne(JsonHandler.documentFromSensor(sensor));
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}	
		return success;
	}

	@Override
	public boolean updateSensor(ISensor sensor) {
		boolean success = false;
		try {
			sensorCollection.replaceOne(
					Filters.eq("_id", JsonHandler.getIdFromJson(JsonHandler.buildSingleInt("_id", sensor.getId()))), 
					JsonHandler.documentFromSensor(sensor));
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean deleteSensor(String jsonId) {
		boolean success = false;
		try {
			sensorCollection.deleteOne(Filters.eq("_id", JsonHandler.getIdFromJson(jsonId)));
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
				
		return success;
	}

	@Override
	public String batchQuery(String batchQuery) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean deleteAll() {
		boolean success = false;
		try {
			database.drop();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}