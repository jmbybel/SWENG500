package edu.psu.iot.database;

import java.util.List;

import edu.psu.iot.object.Device;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public interface DatabaseRepository {

	Sensor createSensor(Sensor theSensor);

	Sensor updateSensor(Sensor theSensor);

	Sensor readSensorById(String id);

	boolean deleteSensor(String id);

	List<Device> getAllDevices();

	Device createDevice(Device theDevice);

	Device updateDevice(Device theDevice);

	Device readDeviceById(String id);

	boolean deleteDevice(String id);

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
	ResponseData createResponseData(ResponseData thePayload);

	ResponseData readResponseDataById(String id);

	boolean deleteResponseData(String id);

	List<ResponseData> getAllPayloadResponsesBySensorId(String sensorId);

}