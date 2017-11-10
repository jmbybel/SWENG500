package edu.psu.iot.database;

import java.util.List;

import edu.psu.iot.generator.interfaces.ISensor;



public interface IDatabase {

	String getAllSensors();

	String getSensor(String id);

	boolean createSensor(ISensor sensor);

	boolean updateSensor(String id);

	boolean deleteSensor(String id);

	String batchQuery(String batchQuery);
	
	boolean deleteAll();  //Clears the whole database
}