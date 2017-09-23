package edu.psu.iot.service;

import java.io.File;

import edu.psu.iot.contract.DeviceService;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.DeviceCluster;
import edu.psu.iot.object.Sensor;

public class DeviceServiceImpl implements DeviceService{

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
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDevice(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Device cloneDevice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public DeviceCluster updateCluster(DeviceCluster cluster) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor getSensorById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sensor cloneSensor(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor updateSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return null;
	}


}
