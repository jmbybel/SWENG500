package edu.psu.iot.service.impl;

import edu.psu.iot.database.IDatabase;
import edu.psu.iot.database.mongodb.Database;
import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.Payload;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.service.IDataService;
import edu.psu.iot.util.JsonHandler;

public class DataService implements IDataService {
	
	ISensorService service;
	IDatabase db;
	
	public DataService() {
		service = new SensorService();
		db = new Database();
	}
	
	public DataService(String host, int port) {
		service = new SensorService();
		db = new Database(host, port);
	}
	
	public DataService(String inputConnectionString) {
		service = new SensorService();
		db = new Database(inputConnectionString);
	}
	
	@Override
	public String getNumberOfRunningSensors() {	
		// Only uses ISensorService
		int count = 0;
		for(Payload payload: (new SensorService()).getSensorList().values()) {
			if(payload.isEnable()) count++;			
		}
		return JsonHandler.buildSingleInt("count", count);
	}

	@Override
	public boolean setDestinationIP(String destination) {
		// Only uses ISensorService
		// Example format: "http://18.216.43.18:8081/contentListener";
		boolean success = true;		
		if(destination.startsWith("http://") &&
		destination.endsWith("contentListener")){
			SensorService.setUrlEndpoint(destination);
		}
		else {
			success = false;
		}
		return success;
	}

	@Override
	public String getAllSensors() {
		// Only uses IDatabase
		return db.getAllSensors();
	}

	@Override
	public String getSensor(String id) {
		// Only uses IDatabase
		return db.getSensor(id);
	}

	@Override
	public boolean startSensor(String id) {
		// Only uses ISensorService
		int sensorId = JsonHandler.getIdFromJson(id);
		service.startSensor(sensorId);
		return true;
	}

	@Override
	public boolean pauseSensor(String id) {
		// Only uses ISensorService
		int sensorId = JsonHandler.getIdFromJson(id);
		service.stopSensor(sensorId);
		return true;
	}

	@Override
	public String createSensor(String jsonString) {
		// Uses ISensorService and IDatabase
		ISensor sensor = JsonHandler.getSensorFromJson(jsonString);
		service.createSensor(sensor);
		db.createSensor(sensor);
		return JsonHandler.jsonFromSensor(sensor);
	}

	@Override
	public String updateSensor(String jsonString) {
		// Uses ISensorService and IDatabase
		ISensor sensor = JsonHandler.getSensorFromJson(jsonString);
		service.deleteSensor(sensor.getId());	//to update, we delete and create anything with that sensor id
		service.createSensor(sensor);
		db.updateSensor(sensor);
		return JsonHandler.jsonFromSensor(sensor);
	}

	@Override
	public boolean deleteSensor(String jsonId) {
		// Uses ISensorService and IDatabase
		int id = JsonHandler.getIdFromJson(jsonId);
		service.deleteSensor(id);
		db.deleteSensor(jsonId);
		return true;
	}

	@Override
	public boolean deleteAll() {
		service.initialize();
		return db.deleteAll();	
	}

}