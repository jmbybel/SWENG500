package api;

import java.util.List;

import com.google.gson.Gson;

import api.validation.JsonToObjectValidator;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.IDataService;
import edu.psu.iot.service.IDataServiceStub;
import edu.psu.iot.service.impl.DataService;
import edu.psu.iot.service.impl.DataServiceStub;

/**
 * This is the only thing the WebServer interacts with.
 *
 */
public class APIEndpoint {

	
	private edu.psu.iot.service.IDataServiceStub dataService = new DataServiceStub();
	private JsonToObjectValidator validator = new JsonToObjectValidator();
	Gson gson = new Gson();
		
	
	public String getNumberOfRunningSensors() {
		String numberRunningSensors = dataService.getNumberOfRunningSensors();
		return gson.toJson(numberRunningSensors);
	}
	
	/**
	 * For a given sensor ID, find all request/response pairs that the Payload Generator has done and return those.
	 * @return
	 */
	public String getAllPayloadsBySensor(String sensorId) {
		List<Payload> payloadResponses = dataService.getAllPayloadsBySensor(sensorId);
		return gson.toJson(payloadResponses);
	}
	
	
	/**
	 *  don't create sensors directly, instead call the insert/update Device with the new data.
	 */
	
	public String createUpdateSensor(String json) {
		String returnString;
		try {
			Sensor validatedSensor = validator.validateSensor(json);
			validatedSensor = dataService.insertUpdateSensor(validatedSensor);
			return validatedSensor.toJson();
		} catch (Exception ex) {
			//TODO this is only the error for when the validator fails to create a Device object.
			returnString = ApiConstants.COULD_NOT_CONVERT_FROM_JSON;
		}
		return returnString;
	}
	
	public String getAllSensors() {
		List<Sensor> allSensors = dataService.getAllSensors();
		return gson.toJson(allSensors);
	}
	
	/**
	 *  All sensors should be pulled from the DB within the Device fetch, so this should not be needed
	 */
	
	public String getSensorById(String id) {
		Sensor sensor = null;
		//TODO placeholder for start of try/catch block for db errors bubbling up
		sensor = dataService.getSensorById(id);
		if (sensor == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return sensor.toJson();
	}

	/**
	 *  Also do this by performing an Update on the Device.
	 * TODO we will need to also 
	 */
	
	public String deleteSensor(String id) {
		boolean result = dataService.deleteSensor(id);
		if (result) {
			return ApiConstants.DELETE_SUCCESS;
		} else {
			return ApiConstants.DELETE_FAILED;
		}
	}
	
	
	public String startSensor(String id) {
		return gson.toJson(dataService.startSensor(id));
	}
	
	public String stopSensor(String id) {
		return gson.toJson(dataService.stopSensor(id));
	}

	
	public IDataService getDeviceService() {
		return dataService;
	}

	public void setDeviceService(edu.psu.iot.service.IDataService deviceService) {
		this.dataService = (IDataServiceStub) deviceService;
	}

	public JsonToObjectValidator getValidator() {
		return validator;
	}

	public void setValidator(JsonToObjectValidator validator) {
		this.validator = validator;
	}
	
}