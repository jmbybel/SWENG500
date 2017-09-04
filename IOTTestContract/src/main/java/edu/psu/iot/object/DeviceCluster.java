package edu.psu.iot.object;

import java.util.List;

//A collection of devices that can be named as a whole set.
// in the UI layer this will allow us to manage groups of devices at a time.
public class DeviceCluster {

	private Long id;
	
	private String name;
	
	private List<MockDevice> devices;

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

	public List<MockDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<MockDevice> devices) {
		this.devices = devices;
	}
	

	
}
