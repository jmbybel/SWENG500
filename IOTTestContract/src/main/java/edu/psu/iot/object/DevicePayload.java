package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

//TODO confirm name.
public class DevicePayload {

	private Long id;
	
	private Date createdDateTime;
	
	//TODO should this actually be a single string in JSOn format by now?
	private Map<String, String> payloadData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Map<String, String> getPayloadData() {
		return payloadData;
	}

	public void setPayloadData(Map<String, String> payloadData) {
		this.payloadData = payloadData;
	}
	

	public String toJsonString() {
		//TODO;
		return null;
	}
}
