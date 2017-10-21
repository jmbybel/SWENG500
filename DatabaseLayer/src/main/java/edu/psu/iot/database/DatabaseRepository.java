package edu.psu.iot.database;

import java.util.List;

import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;

public interface DatabaseRepository {

	List<Sensor> getAllSensors();
	
	Sensor createSensor(Sensor theSensor);

	Sensor updateSensor(Sensor theSensor);

	Sensor readSensorById(String id);

	boolean deleteSensor(String id);

	Payload createPayload(Payload thePayload);
	
	Payload readPayloadById(String id);
	
	boolean deletePayload(String id);

	List<Payload> getAllPayloadsBySensorId(String sensorId);
}