package edu.psu.iot.controller;

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

	public String getDevice(String id) {
		Device d = deviceService.getDeviceById(id);
		return d.toJson();
	}
	
	public String getSensor(String id) {
		Sensor s = deviceService.getSensorById(id);
		return s.toJson();
	}
}
