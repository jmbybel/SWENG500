package edu.psu.iot.service;

import java.io.File;

import edu.psu.iot.contract.DeviceService;
import edu.psu.iot.database.MongoDbPersistence;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.DeviceCluster;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;

public class DeviceServiceImpl implements DeviceService{
	
	private MongoDbPersistence databaseAccess = new MongoDbPersistence();

	@Override
	public File getDataGeneratorAsDownloadbleFile(Device theDeviceToConvert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device createDataGeneratorFromUploadedFile(File incomingFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDataGeneratorInputFields(Device theDeviceToAlter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Device getDeviceById(String id) {
		return databaseAccess.readDeviceById(id);
	}

	@Override
	public boolean deleteDevice(String id) {
		return databaseAccess.deleteDevice(id);
	}

	@Override
	public Device cloneDevice(Device original) {
		original.setId(null);
		return databaseAccess.createDevice(original);
	}

	@Override
	public Device insertUpdateDevice(Device device) {
		if (device.getId() == null) {
			device = databaseAccess.createDevice(device);
		} else {
			device = databaseAccess.updateDevice(device);
		}
		return device;
	}
/*
	@Override
	public DeviceCluster getClusterByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCluster(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeviceCluster cloneCluster(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceCluster insertUpdateCluster(DeviceCluster cluster) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public Sensor getSensorById(String id) {
		return databaseAccess.readSensorById(id);
	}

	@Override
	public boolean deleteSensor(String id) {
		return databaseAccess.deleteSensor(id);
	}

	@Override
	public Sensor cloneSensor(Sensor original) {
		original.setId(null);
		return databaseAccess.createSensor(original);
	}

	@Override
	public Sensor insertUpdateSensor(Sensor sensor) {
		if (sensor.getId() == null) {
			sensor = databaseAccess.createSensor(sensor);
		} else {
			sensor = databaseAccess.updateSensor(sensor);
		}
		return sensor;
	}

	@Override
	public Payload getPayloadById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePayload(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Payload insertPayload(Payload sensor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData getResponseDataById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteResponseData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResponseData insertResponseData(ResponseData sensor) {
		// TODO Auto-generated method stub
		return null;
	}
}
