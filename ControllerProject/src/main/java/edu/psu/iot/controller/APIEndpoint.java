package edu.psu.iot.controller;

import java.util.List;

import com.google.gson.Gson;

import edu.psu.iot.constants.ApiConstants;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.DeviceServiceImpl;

/**
 * These will be the methods hit by our REST calls.
 *
 */
public class APIEndpoint {

	
	private DeviceServiceImpl deviceService = new DeviceServiceImpl();
	private ObjectFromJsonValidator validator = new ObjectFromJsonValidator();
	
	/**
	 * Receives a JSON string of a Device, and all of its child objects, and attempts to save it to the database.
	 *
	 * @param json
	 * @return the updated Device as a JSON string, or an Error json containing the top-level text of the Exception thrown.
	 */
	public String createUpdateDevice(String json) {
		String returnString;
		try {
			Device validatedDevice = validator.validateDevice(json);
			validatedDevice = deviceService.insertUpdateDevice(validatedDevice);
			return validatedDevice.toJson();
		} catch (Exception ex) {
			//TODO this is only the error for when the validator fails to create a Device object.
			returnString = ApiConstants.COULD_NOT_CONVERT_FROM_JSON;
		}
		return returnString;
	}
	
	/**
	 * Service layer & MongoDB return null if the ID does not match an object in the database.
	 * In that case, we return an error string stating the object could not be found for that ID.
	 * @param id
	 * @return
	 */
	public String getDeviceById(String id) {
		Device device = null;
		//TODO later on put the next line into a try/catch for database issues.
		device = deviceService.getDeviceById(id);
		if (device == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return device.toJson();
	}
	
	/**
	 * Database responds with operation success/fail boolean. in either case, give the UI layer a JSON object with some verbage to display.
	 * @param id
	 * @return
	 * TODO deleting a device means we should probably also delete every request/response for all sensors attached.
	 */
	public String deleteDevice(String id) {
		boolean result = deviceService.deleteDevice(id);
		if (result) {
			return ApiConstants.DELETE_SUCCESS;
		} else {
			return ApiConstants.DELETE_FAILED;
		}
	}
	
	/**
	 * Load all devices for display -- the master list.
	 * @return
	 */
	public String getAllDevices() {
		List<Device> deviceList = deviceService.getAllDevices();
		Gson gson = new Gson();
				
		return  gson.toJson(deviceList);
	}
	
	/**
	 * TODO: work in progress
	 * For a given sensor ID, find all request/response pairs that the Payload Generator has done and return those.
	 * @return
	 */
	public String getAllPayloadResponsesBySensor(String sensorId) {
		//TODO return deviceService.getAllPayloadResponsesBySensor(sensorId);
		
		return null;
	}
	
	
	/**
	 * @deprecated don't create sensors directly, instead call the insert/update Device with the new data.
	 */
	@Deprecated
	public String createUpdateSensor(String json) {
		String returnString;
		try {
			Sensor validatedSensor = validator.validateSensor(json);
			validatedSensor = deviceService.insertUpdateSensor(validatedSensor);
			return validatedSensor.toJson();
		} catch (Exception ex) {
			//TODO this is only the error for when the validator fails to create a Device object.
			returnString = ApiConstants.COULD_NOT_CONVERT_FROM_JSON;
		}
		return returnString;
	}
	
	/**
	 * @deprecated All sensors should be pulled from the DB within the Device fetch, so this should not be needed
	 */
	@Deprecated
	public String getSensorById(String id) {
		Sensor sensor = null;
		//TODO placeholder for start of try/catch block for db errors bubbling up
		sensor = deviceService.getSensorById(id);
		if (sensor == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return sensor.toJson();
	}

	/**
	 * @deprecated Also do this by performing an Update on the Device.
	 * TODO we will need to also 
	 */
	@Deprecated
	public String deleteSensor(String id) {
		boolean result = deviceService.deleteSensor(id);
		if (result) {
			return ApiConstants.DELETE_SUCCESS;
		} else {
			return ApiConstants.DELETE_FAILED;
		}
	}
	
	

	
	public DeviceServiceImpl getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceServiceImpl deviceService) {
		this.deviceService = deviceService;
	}

	public ObjectFromJsonValidator getValidator() {
		return validator;
	}

	public void setValidator(ObjectFromJsonValidator validator) {
		this.validator = validator;
	}
	
}