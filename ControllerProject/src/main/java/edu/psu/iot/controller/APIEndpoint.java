package edu.psu.iot.controller;

import com.google.gson.Gson;

import edu.psu.iot.constants.ApiConstants;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.DeviceServiceImpl;

public class APIEndpoint {

	
	private DeviceServiceImpl deviceService;
	
	public String createUpdateDevice(String json) {
		Gson gson = new Gson();
		Device device = gson.fromJson(json, Device.class);
		device = deviceService.insertUpdateDevice(device);
		return gson.toJson(device);
	}
	
	public String getDeviceById(String id) {
		Device device = deviceService.getDeviceById(id);
		if (device == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return device.toJson();
	}
	
	public String deleteDevice(String id) {
		return Boolean.toString(deviceService.deleteDevice(id));
	}
	
	public String createUpdateSensor(String json) {
		Gson gson = new Gson();
		Sensor sensor = gson.fromJson(json, Sensor.class);
		sensor = deviceService.insertUpdateSensor(sensor);
		return sensor.toJson();
	}
	
	public String getSensorById(String id) {
		Sensor sensor = deviceService.getSensorById(id);
		if (sensor == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return sensor.toJson();
	}

	public String deleteSensor(String id) {
		return Boolean.toString(deviceService.deleteSensor(id));
	}

	public DeviceServiceImpl getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceServiceImpl deviceService) {
		this.deviceService = deviceService;
	}
	
}
