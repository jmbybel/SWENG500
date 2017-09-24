package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;

// An individual set of fields sent from a device to the endpoint, timestamped with when it was created.
//TODO confirm name.
@MongoCollection(name="payload")
public class Payload extends JsonObject {

	private String id;
	
	private Date createdDateTime;
	
	//TODO should this actually be a single string in JSON format by now?
	private Map<String, String> payloadData;

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
