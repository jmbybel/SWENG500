package edu.psu.iot.service;

import java.io.File;

import edu.psu.iot.database.MongoDbPersistence;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public class DeviceServiceImpl {
	
	private MongoDbPersistence databaseAccess = new MongoDbPersistence();

	
	public File getDataGeneratorAsDownloadbleFile(Device theDeviceToConvert) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Device createDataGeneratorFromUploadedFile(File incomingFile) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updateDataGeneratorInputFields(Device theDeviceToAlter) {
		// TODO Auto-generated method stub
		
	}

	
	public Device getDeviceById(String id) {
		return databaseAccess.readDeviceById(id);
	}

	
	public boolean deleteDevice(String id) {
		return databaseAccess.deleteDevice(id);
	}

	
	public Device cloneDevice(Device original) {
		original.setId(null);
		return databaseAccess.createDevice(original);
	}

	
	public Device insertUpdateDevice(Device device) {
		if (device.getId() == null) {
			device = databaseAccess.createDevice(device);
		} else {
			device = databaseAccess.updateDevice(device);
		}
		return device;
	}
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
	
	public Sensor getSensorById(String id) {
		return databaseAccess.readSensorById(id);
	}

	
	public boolean deleteSensor(String id) {
		return databaseAccess.deleteSensor(id);
	}

	
	public Sensor cloneSensor(Sensor original) {
		original.setId(null);
		return databaseAccess.createSensor(original);
	}

	
	public Sensor insertUpdateSensor(Sensor sensor) {
		if (sensor.getId() == null) {
			sensor = databaseAccess.createSensor(sensor);
		} else {
			sensor = databaseAccess.updateSensor(sensor);
		}
		return sensor;
	}

	
	public Payload getPayloadById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean deletePayload(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Payload insertPayload(Payload sensor) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResponseData getResponseDataById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean deleteResponseData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public ResponseData insertResponseData(ResponseData sensor) {
		// TODO Auto-generated method stub
		return null;
	}


	public MongoDbPersistence getDatabaseAccess() {
		return databaseAccess;
	}


	public void setDatabaseAccess(MongoDbPersistence databaseAccess) {
		this.databaseAccess = databaseAccess;
	}
	
	
}
