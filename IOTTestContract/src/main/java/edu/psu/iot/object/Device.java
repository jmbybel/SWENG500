package edu.psu.iot.object;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import edu.psu.iot.object.intf.JsonObject;
//TODO log4j.
public class Device extends JsonObject{

	private Long id;
	
	private String name;
	
	private List<Sensor> sensors = new ArrayList<>();

	///TODO attach this to the sensor instead of device?
	private List<DevicePayload> payloads = new ArrayList<>();
	
	public Device() {
		
	}
	
	//Java clone is bad form, use a constructor taking an object of the same type in.
	public Device(Device copyFrom) {

		this.name = "copy of " + copyFrom.getName();
		this.sensors = new ArrayList<>(copyFrom.getSensors());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DevicePayload> getPayloads() {
		return payloads;
	}

	public void setPayloads(List<DevicePayload> payloads) {
		this.payloads = payloads;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

}
