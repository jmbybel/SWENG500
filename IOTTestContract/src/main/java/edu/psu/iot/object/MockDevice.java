package edu.psu.iot.object;

import java.util.List;
import java.util.Map;

//TODO MockDevice is clearly wrong, its not a mock, its a testdevice or a sampleDevice.
public class MockDevice {

	private Long id;
	
	private String name;
	
	private Long dataPushMinimumMilliseconds;
	
	private Long dataPushMaximumMilliseconds;
	
	private Map<String, String> inputFields;
	
	//payloads may be the wrong name.... testRequests, or similar might be more appropriate.
	private List<DevicePayload> payloads;

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

	public Map<String, String> getInputFields() {
		return inputFields;
	}

	public void setInputFields(Map<String, String> inputFields) {
		this.inputFields = inputFields;
	}

	public List<DevicePayload> getPayloads() {
		return payloads;
	}

	public void setPayloads(List<DevicePayload> payloads) {
		this.payloads = payloads;
	}

	public Long getDataPushMinimumMilliseconds() {
		return dataPushMinimumMilliseconds;
	}

	public void setDataPushMinimumMilliseconds(Long dataPushMinimumMilliseconds) {
		this.dataPushMinimumMilliseconds = dataPushMinimumMilliseconds;
	}

	public Long getDataPushMaximumMilliseconds() {
		return dataPushMaximumMilliseconds;
	}

	public void setDataPushMaximumMilliseconds(Long dataPushMaximumMilliseconds) {
		this.dataPushMaximumMilliseconds = dataPushMaximumMilliseconds;
	}

}
