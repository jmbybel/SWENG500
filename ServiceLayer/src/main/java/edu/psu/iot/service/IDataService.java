package edu.psu.iot.service;

import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;

public interface IDataService {

	List<Sensor> getAllSensors();
	
	Sensor getSensorById(String id);

	boolean deleteSensor(String id);

	Sensor cloneSensor(Sensor original);

	Sensor insertUpdateSensor(Sensor sensor);
	
	Boolean startSensor(String sensorId) ;
	
	Boolean stopSensor(String sensorId);

	Payload getPayloadById(String id);

	boolean deletePayload(String id);

	Payload insertPayload(Payload sensor);

	List<Payload> getAllPayloadsBySensor(String sensorId);

	DatabaseRepository getRepository();

}