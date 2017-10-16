package edu.psu.iot.service;

import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public interface IDataService {

	Device getDeviceById(String id);

	boolean deleteDevice(String id);

	Device cloneDevice(Device original);

	Device insertUpdateDevice(Device device);

	List<Device> getAllDevices();

	Sensor getSensorById(String id);

	boolean deleteSensor(String id);

	Sensor cloneSensor(Sensor original);

	Sensor insertUpdateSensor(Sensor sensor);
	
	Boolean startSensor(String sensorId) ;
	
	Boolean stopSensor(String sensorId);

	Payload getPayloadById(String id);

	boolean deletePayload(String id);

	Payload insertPayload(Payload sensor);

	ResponseData getResponseDataById(String id);

	boolean deleteResponseData(String id);

	ResponseData insertResponseData(ResponseData sensor);

	List<ResponseData> getAllPayloadResponsesBySensor(String sensorId);

	DatabaseRepository getRepository();

}