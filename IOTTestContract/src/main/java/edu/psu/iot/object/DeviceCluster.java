package edu.psu.iot.object;

import java.util.List;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;

//A collection of devices that can be named as a whole set.
// in the UI layer this will allow us to manage groups of devices at a time.
public class DeviceCluster extends JsonObject {

	private String id;
	
	private String name;
	
	private List<Device> devices;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	

	
}

