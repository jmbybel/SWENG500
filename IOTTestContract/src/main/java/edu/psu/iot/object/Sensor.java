package edu.psu.iot.object;

import java.util.Map;

public class Sensor {

	private Long id;

	private Long dataPushMinimumMilliseconds;
	
	private Long dataPushMaximumMilliseconds;
	
	private Map<String, String> inputFields;

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
	
	
}
