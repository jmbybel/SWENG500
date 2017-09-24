package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;

// data received from the endpoint under test.
// TODO this needs to relate to a single device, a single payload, or a cluster of devices in some meaningful way but I don't know how.
@MongoCollection(name="responseData")
public class ResponseData extends JsonObject {

	private String id;
	
	private Map<String, String> responseData;
	//TODO: will EndpointResponseData be related to a single devicePayload, single device, a cluster of devices, or a mix?
	//private MockDevice sourceDevice;
	//private DevicePayload sourcePayload;
	//private DeviceCluster sourceCluster;
	
	private Date createdDateTime;

	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	@ObjectId
	@JsonProperty("_id")
	public void setId(String id) {
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
