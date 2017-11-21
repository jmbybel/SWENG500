package edu.psu.iot.database;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import edu.psu.iot.generator.interfaces.ISensor;



public interface IDatabase {

	MongoCursor<Document> getAllSensors();

	Document getSensor(String id);

	boolean createSensor(ISensor sensor);

	boolean updateSensor(ISensor sensor);

	boolean deleteSensor(String id);

	String batchQuery(String batchQuery);
	
	boolean deleteAll();  //Clears the whole database
}