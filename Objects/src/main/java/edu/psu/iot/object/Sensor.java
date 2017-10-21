package edu.psu.iot.object;

import java.util.Date;
import java.util.UUID;

import edu.psu.iot.object.base.MongoDatabaseObject;

/**
 * This is a single sensor, a group of which will make up a given Device.
 * Since we are using MongoDB, this will exist directly in the Device collection, not its own.
 * To generate an ID for it that the payload request/response pairs can reference, we do it manually via Java's UUID before persisting.
 * @author lynyc
 *
 */
public class Sensor extends MongoDatabaseObject {

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
	
	private Payload payloadConfiguration;
	
	//Do not manually use this in live code, use the one that generates a unique ID instead, or create the Sensor via Gson.fromString or from the database.
	@Deprecated
	public Sensor() {
		
	}
	
	//This constructor is to be used by code -- if a new Sensor is made, generate a unique ID for it since it won't live in a separate mongodb collection. 
	public Sensor(boolean thisConstructorGivesMeAnId) {
		UUID uniqueId = UUID.randomUUID();
		this.setId(uniqueId.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

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

	public Payload getPayloadConfiguration() {
		return payloadConfiguration;
	}

	public void setPayloadConfiguration(Payload payloadConfiguration) {
		this.payloadConfiguration = payloadConfiguration;
	}
	
}