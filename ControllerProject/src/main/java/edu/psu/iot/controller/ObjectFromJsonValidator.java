package edu.psu.iot.controller;

import com.google.gson.Gson;

import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;

public class ObjectFromJsonValidator {
	
	private Gson gsonTool = new Gson();

	public Device validateDevice(String json) {//TODO throws some exception when the tool fails to create a real object.
		Device device = gsonTool.fromJson(json, Device.class);
		//TODO what else does it need to do?
		return device;
	}
	

	public Sensor validateSensor(String json) {//TODO throws some exception when the tool fails to create a real object.
		Sensor returnObject = gsonTool.fromJson(json, Sensor.class);
		//TODO what else does it need to do?
		return returnObject;
	}
}