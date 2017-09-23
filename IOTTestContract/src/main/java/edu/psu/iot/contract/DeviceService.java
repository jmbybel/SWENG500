package edu.psu.iot.contract;

import java.io.File;

import edu.psu.iot.object.Device;
import edu.psu.iot.object.DeviceCluster;
import edu.psu.iot.object.Sensor;

//TODO don't even 
public interface DeviceService {

	// matches story 7 - "As a user, I want to be able to download my configured test data generators configurations.
	public File getDataGeneratorAsDownloadbleFile(Device theDeviceToConvert);
	
	//story 8 As a user, I want to be able to upload a configuration file to set the fields for all needed data generation values in the service.
	//
	public Device createDataGeneratorFromUploadedFile(File incomingFile);
	
	//story 9 As a user, I want to configure the mock data source values and entropy associated with those values.
	// probably takes raw maps of strings from the http request and updates the appropriate device with the values.
	public void updateDataGeneratorInputFields(Device theDeviceToAlter);
	
	//CRUD read
	public Device getDeviceById(String id);
	//CRUD delete
	public boolean deleteDevice(String id);
	//copy
	public Device cloneDevice(String id);
	
	public DeviceCluster getClusterByID(String id);
	public boolean deleteCluster(String id);
	public DeviceCluster cloneCluster(String id);
	public DeviceCluster updateCluster(DeviceCluster cluster);
	
	public Sensor getSensorById(String id);
	public boolean deleteSensor(String id);
	public Sensor cloneSensor(String id);
	public Sensor updateSensor(Sensor sensor);
}
