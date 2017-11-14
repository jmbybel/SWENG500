package api;

import com.google.gson.Gson;
import api.validation.JsonToObjectValidator;
import edu.psu.iot.service.IDataService;
import edu.psu.iot.service.impl.DataService;

/**
 * This is the only thing the WebServer interacts with.
 *
 */
public class APIEndpoint {
	private edu.psu.iot.service.IDataService dataService = new DataService();
	private JsonToObjectValidator validator = new JsonToObjectValidator();
	Gson gson = new Gson();

	public String getNumberOfRunningSensors() {
		String numberRunningSensors = dataService.getNumberOfRunningSensors();
		return gson.toJson(numberRunningSensors);
	}
	
	public String createSensor(String json) {
		return dataService.createSensor(json);
	}
	
	public String updateSensor(String json) {
		return dataService.updateSensor(json);
	}
	
	public String getAllSensors() {
		String allSensors = dataService.getAllSensors();
		System.out.println(allSensors);
		return gson.toJson(allSensors);
	}
	
	/**
	 *  All sensors should be pulled from the DB within the Device fetch, so this should not be needed
	 */
	public String getSensorById(String id) {
		String sensor = null;
		//TODO placeholder for start of try/catch block for db errors bubbling up
		sensor = dataService.getSensor(id);
		if (sensor == null) {
			return ApiConstants.COULD_NOT_FIND_MATCH;
		}
		return sensor;
	}

	public boolean deleteSensor(String id) {
		return dataService.deleteSensor(id);
	}
	
	public String startSensor(String id) {
		return gson.toJson(dataService.startSensor(id));
	}
	
	public String pauseSensor(String id) {
		return gson.toJson(dataService.pauseSensor(id));
	}

	public IDataService getDeviceService() {
		return dataService;
	}

	public void setDeviceService(edu.psu.iot.service.IDataService deviceService) {
		this.dataService = (IDataService) deviceService;
	}

	public JsonToObjectValidator getValidator() {
		return validator;
	}

	public void setValidator(JsonToObjectValidator validator) {
		this.validator = validator;
	}
	
}