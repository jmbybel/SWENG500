package edu.psu.iot.controller;

import com.google.gson.Gson;

import edu.psu.iot.contract.DeviceService;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;

public class APIEndpoint {

	private static final String URI_PART_CREATE = "create";
	private static final String URI_PART_READ = "read";
	private static final String URI_PART_UPDATE = "update";
	private static final String URI_PART_DELETE = "delete";
	private static final String URI_PART_COPY = "copy";
	
	private static final String URI_PART_DEVICE = "device";
	private static final String URI_PART_SENSOR = "sensor";
	private static final String URI_PART_CLUSTER = "cluster";
	private static final String URI_PART_PAYLOAD = "payload";
	private static final String URI_PART_RESPONSE = "response";
	
	private DeviceService deviceService;
	
	/*
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		//call down to individual operations
	}
	*/

	public String createUpdateDevice(String json) {
		Gson gson = new Gson();
		Device device = gson.fromJson(json, Device.class);
		device = deviceService.insertUpdateDevice(device);
		return device.toString();
	}
	
	public String getDeviceById(String id) {
		Device device = deviceService.getDeviceById(id);
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
		return sensor.toJson();
	}

	public String deleteSensor(String id) {
		return Boolean.toString(deviceService.deleteSensor(id));
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	
}
