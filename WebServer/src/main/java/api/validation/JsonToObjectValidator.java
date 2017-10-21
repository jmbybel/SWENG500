package api.validation;

import com.google.gson.Gson;

import edu.psu.iot.object.Sensor;

public class JsonToObjectValidator {
	
	private Gson gsonTool = new Gson();

	public Sensor validateSensor(String json) {//TODO throws some exception when the tool fails to create a real object.
		Sensor returnObject = gsonTool.fromJson(json, Sensor.class);
		//TODO what else does it need to do?
		return returnObject;
	}
}
