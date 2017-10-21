package edu.psu.iot.object;

import java.util.Date;
import java.util.Map;

import edu.psu.iot.object.base.MongoDatabaseObject;

// An individual set of fields sent from a device to the endpoint, timestamped with when it was created in database.
// Does not need its own ID.
// The sensorId will keep it related to the specific sensor in the Device collection that it relates to (and the payload configuration that it originated as)
// and it should be saved as a child of the Response Data.

//TODO confirm name.
public class Payload  extends MongoDatabaseObject{

	private String sensorId;
	
	private String id;
	
	private Date createdDateTime;
	
	//TODO should this actually be a single string in JSON format by now?
	private Map<String, String> payloadData;
	
	public Payload() {}
	
	public Payload(String sensorId) {
		this.sensorId = sensorId;
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

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
