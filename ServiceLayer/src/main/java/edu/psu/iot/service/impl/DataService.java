package edu.psu.iot.service.impl;

import java.util.Map;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCursor;

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
	public String getDestinationIP() {
		return SensorService.getUrlEndpoint();
	}

	@Override
	public String getAllSensors() {
		MongoCursor<Document> cursor = db.getAllSensors();
		String all = "[";
		StringBuilder builder = new StringBuilder(all);
		Document doc = null;
		try {
		    while (cursor.hasNext()) {
		    	doc = cursor.next();
		    	doc.append("enabled", service.isEnabled(doc.getInteger("_id")));
		    	builder.append(doc.toJson(new JsonWriterSettings(JsonMode.RELAXED)));
		    	builder.append(",");
		    }
		    int length = builder.length();
		    
		    // If no sensors exist, don't delete the opening [
		    if (length > 1) {
		    	builder.delete(length - 1, length);
		    }
		    
		    builder.append("]");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		    cursor.close();
		}

		return builder.toString();
	}

	@Override
	public String getSensor(String id) {
		Document doc = db.getSensor(id);
		doc.append("enabled", service.isEnabled(doc.getInteger("_id")));
		return doc.toJson(new JsonWriterSettings(JsonMode.RELAXED));
	}

	@Override
	public boolean startSensor(String id) {
		int sensorId = JsonHandler.getIdFromJson(id);
		service.startSensor(sensorId);
		return true;
	}

	@Override
	public boolean pauseSensor(String id) {
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
		ISensor sensor = JsonHandler.getSensorFromJson(jsonString);
		System.out.println(service.getSensorList().keySet());
		service.deleteSensor(sensor.getId());	//to update, we delete and create anything with that sensor id
		System.out.println(service.getSensorList().keySet());
		service.createSensor(sensor);
		System.out.println(service.getSensorList().keySet());
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