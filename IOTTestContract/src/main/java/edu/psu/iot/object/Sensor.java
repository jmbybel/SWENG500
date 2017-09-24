package edu.psu.iot.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;

@MongoCollection(name="sensor")
public class Sensor extends JsonObject {

	private String name;
	
	private String id;
	
	private Long value;
	
	//TODO check default values for floor and ceil
	private Long floor = 0L;
	
	private Long ceil = 100L;
	
	//Will be either Sine, Ramp, Binary, or Random
	private String sensorType;
	
	private int interval;
	
	private Date expiration = new Date();
	
	private List<Payload> payloads = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	public Long getFloor() {
		return floor;
	}

	public void setFloor(Long floor) {
		this.floor = floor;
	}
	
	public Long getCeil() {
		return ceil;
	}

	public void setCeil(Long ceil) {
		this.ceil = ceil;
	}
	
	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public List<Payload> getPayloads() {
		return payloads;
	}

	public void setPayloads(List<Payload> payloads) {
		this.payloads = payloads;
	}
	
	
}