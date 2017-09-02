package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

public class EndpointResponseData {

	private Long id;
	
	private Map<String, String> responseData;
	//TODO: will EndpointResponseData be related to a single devicePayload, single device, a cluster of devices, or a mix?
	//private MockDevice sourceDevice;
	//private DevicePayload sourcePayload;
	//private DeviceCluster sourceCluster;
	
	private Date createdDateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, String> responseData) {
		this.responseData = responseData;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	

	public String toJsonString() {
		//TODO;
		return null;
	}
	
}
