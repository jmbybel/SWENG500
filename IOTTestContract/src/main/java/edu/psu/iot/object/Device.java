package edu.psu.iot.object;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.iot.object.intf.JsonObject;
//TODO log4j.
@MongoCollection(name="device")
public class Device extends JsonObject{

	private String id;
	
	private String name;
	
	private List<Sensor> sensors = new ArrayList<>();
	
	//TODO check default expiration?
	private Date expiration = new Date();  
	
	public Device() {
		
	}
	
	//Java clone is bad form, use a constructor taking an object of the same type in.
	public Device(Device copyFrom) {

		this.name = "copy of " + copyFrom.getName();
		this.sensors = new ArrayList<>(copyFrom.getSensors());
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

}
