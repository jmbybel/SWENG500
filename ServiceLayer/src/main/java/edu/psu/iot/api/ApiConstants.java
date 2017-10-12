package edu.psu.iot.api;

public interface ApiConstants {

	public String COULD_NOT_FIND_MATCH = "{\"error\": \"Could not find a match for the provided ID\"}";
	
	public String COULD_NOT_CONVERT_FROM_JSON = "{\"error\": \"Could not create the object from the JSON string passed in\"}";
	
	public String DELETE_SUCCESS = "{\"action\": \"success\"}";
	
	public String DELETE_FAILED = "{\"action\": \"failed. ID may have been incorrect\"}";
	
//don't think these are needed now.
	public final String URI_PART_CREATE = "create";
	public final String URI_PART_READ = "read";
	public final String URI_PART_UPDATE = "update";
	public final String URI_PART_DELETE = "delete";
	public final String URI_PART_COPY = "copy";
	
	public final String URI_PART_DEVICE = "device";
	public final String URI_PART_SENSOR = "sensor";
	public final String URI_PART_CLUSTER = "cluster";
	public final String URI_PART_PAYLOAD = "payload";
	public final String URI_PART_RESPONSE = "response";
}
