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
	public Device getDeviceById(Long id);
	//CRUD delete
	public boolean deleteDevice(Long id);
	//copy
	public Device cloneDevice(Long id);
	
	public DeviceCluster getClusterByID(Long id);
	public boolean deleteCluster(Long id);
	public DeviceCluster cloneCluster(Long id);
	public DeviceCluster updateCluster(DeviceCluster cluster);
	
	public Sensor getSensorById(Long id);
	public boolean deleteSensor(Long id);
	public Sensor cloneSensor(Long id);
	public Sensor updateSensor(Sensor sensor);
}
