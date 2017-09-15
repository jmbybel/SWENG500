package edu.psu.iot.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.psu.iot.object.intf.JsonObject;

public class Sensor extends JsonObject {

	private Long id;

	private Long dataPushMinimumMilliseconds;
	
	private Long dataPushMaximumMilliseconds;
	
	private Map<String, String> inputFields;
	
	//moved into Sensor
	private List<DevicePayload> payloads = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getInputFields() {
		return inputFields;
	}

	public void setInputFields(Map<String, String> inputFields) {
		this.inputFields = inputFields;
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
	
	public List<DevicePayload> getPayloads() {
		return payloads;
	}

	public void setPayloads(List<DevicePayload> payloads) {
		this.payloads = payloads;
	}
	
	
}
