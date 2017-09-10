package edu.psu.iot.controller;

import edu.psu.iot.contract.DeviceService;

//TODO actually a servlet. or whatever Spring gives us.

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
	
	//TODO if we're using spring add autowiring here.
	private DeviceService deviceService;
	
	/*
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		//call down to individual operations
	}
	*/

	
}
