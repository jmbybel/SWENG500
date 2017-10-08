package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;

// data received from the endpoint under test.
@MongoCollection(name="responseData")
public class ResponseData extends JsonObject {

	private String id;
	
	private Map<String, String> responseData;
	
	private Date createdDateTime;
	
	private Payload requestData;

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
	
	public Payload getRequestData() {
		return requestData;
	}

	public void setRequestData(Payload requestData) {
		this.requestData = requestData;
	}
	
}
