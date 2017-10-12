package edu.psu.iot.service;

import java.io.File;
import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public interface IDataService {

	File getDataGeneratorAsDownloadbleFile(Device theDeviceToConvert);

	Device createDataGeneratorFromUploadedFile(File incomingFile);

	void updateDataGeneratorInputFields(Device theDeviceToAlter);

	Device getDeviceById(String id);

	boolean deleteDevice(String id);

	Device cloneDevice(Device original);

	Device insertUpdateDevice(Device device);

	List<Device> getAllDevices();
	/*
		
		public DeviceCluster getClusterByID(String id) {
			// TODO Auto-generated method stub
			return null;
		}
	
		
		public boolean deleteCluster(String id) {
			// TODO Auto-generated method stub
			return false;
		}
	
		
		public DeviceCluster cloneCluster(String id) {
			// TODO Auto-generated method stub
			return null;
		}
	
		
		public DeviceCluster insertUpdateCluster(DeviceCluster cluster) {
			// TODO Auto-generated method stub
			return null;
		}
	*/

	Sensor getSensorById(String id);

	//TODO needs a rework
	boolean deleteSensor(String id);

	//TODO needs a rework
	Sensor cloneSensor(Sensor original);

	Sensor insertUpdateSensor(Sensor sensor);

	Payload getPayloadById(String id);

	boolean deletePayload(String id);

	Payload insertPayload(Payload sensor);

	ResponseData getResponseDataById(String id);

	boolean deleteResponseData(String id);

	ResponseData insertResponseData(ResponseData sensor);

	List<ResponseData> getAllPayloadResponsesBySensor(String sensorId);

	DatabaseRepository getRepository();

}