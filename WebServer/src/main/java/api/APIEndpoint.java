package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.validation.JsonToObjectValidator;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.Payload;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorService;

import edu.psu.iot.service.IDataService;
import edu.psu.iot.service.impl.DataService;
import edu.psu.iot.service.impl.DataServiceStub;
import edu.psu.iot.webserver.Main;

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
	
	/**
	 * For a given sensor ID, find all request/response pairs that the Payload Generator has done and return those.
	 * @return
	 */
	public String getAllPayloadsBySensor(String sensorId) {
		String payloadResponses = dataService.batchQuery(sensorId);
		return gson.toJson(payloadResponses);
	}
	
	
	/**
	 *  don't create sensors directly, instead call the insert/update Device with the new data.
	 */
	
	public boolean createUpdateSensor(String json) {
		return dataService.updateSensor(json);
	}
	
	public String getAllSensors() {
		String allSensors = dataService.getAllSensors();
		return gson.toJson(allSensors);
	}
	
	public String getAllPayloads()
	{
		ArrayList<SensorService> ssList = Main.getSSList();
		List<JSONObject> payloads = new ArrayList<JSONObject>();
		
		for (int i = 0; i < ssList.size(); i++) {
			Map<Integer, edu.psu.iot.generator.sensor.Payload> list = ssList.get(i).getSensorList();
			if(!list.isEmpty()) {
				for (Entry<Integer, edu.psu.iot.generator.sensor.Payload> entry : list.entrySet()) {
				    payloads.add(entry.getValue().getPayload());
				}
			}
		}
		JSONArray test = new JSONArray(payloads);

		return test.toString();
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