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
	public Device getDeviceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDevice(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Device cloneDevice(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceCluster getClusterByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCluster(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeviceCluster cloneCluster(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceCluster updateCluster(DeviceCluster cluster) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor getSensorById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSensor(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sensor cloneSensor(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor updateSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return null;
	}


}
