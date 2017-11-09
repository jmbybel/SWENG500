package edu.psu.iot.service.impl;

import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.Payload;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.service.IDataService;
import edu.psu.iot.service.JsonHandler;

public class DataService implements IDataService {
	
	ISensorService service;
	
	public DataService() {
		service = new SensorService();
	}

	@Override
	public String getNumberOfRunningSensors() {	
		// TODO Auto-generated method stub
		int count = 0;
		for(Payload payload: (new SensorService()).getSensorList().values()) {
			if(payload.isEnable()) count++;			
		}
		return JsonHandler.buildSingleInt("count", count);
	}

	@Override
	public boolean setDestinationIP(String destination) {
		//"http://18.216.43.18:8081/contentListener";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSensor(String id) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean createSensor(String jsonString) {
		ISensor sensor = JsonHandler.getSensorFromJson(jsonString);
		service.createSensor(sensor);
		return true;
	}

	@Override
	public boolean updateSensor(String jsonString) {
		ISensor sensor = JsonHandler.getSensorFromJson(jsonString);
		service.deleteSensor(sensor.getId());	//to update, we delete and create anything with that sensor id
		service.createSensor(sensor);
		return true;
	}

	@Override
	public boolean deleteSensor(String jsonId) {
		int id = JsonHandler.getIdFromJson(jsonId);
		service.deleteSensor(id);
		return true;
	}

	@Override
	public boolean batchStart(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchStop(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String batchQuery(String batchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
}