package edu.psu.iot.object;

import java.util.List;

//A collection of devices that can be named as a whole set.
// in the UI layer this will allow us to manage groups of devices at a time.
public class DeviceCluster {

	private Long id;
	
	private String name;
	
	private List<Device> devices;

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

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	

	
}
