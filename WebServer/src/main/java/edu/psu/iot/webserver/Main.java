package edu.psu.iot.webserver;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import api.APIEndpoint;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorService;

public class Main {
    public static void main(String[] args) {
    	port(4000);
    	configureExceptionHandling();
        enableCORS();

        APIEndpoint endpoint = new APIEndpoint();
        
        get("/get-number-of-running-sensors", (request, response) -> {
        	return endpoint.getNumberOfRunningSensors();
        });
        
        get("/get-all-sensors", (request, response) -> {
        	return endpoint.getAllSensors();
        });
        
        get("/get-sensor", (request, response) -> {
        	String id = request.params("sensorId");
        	String sensor = null;

        	if (id != null)
        		sensor = endpoint.getSensorById(id);
        	
        	if (sensor != null)
        		return sensor;
        	
        	return "{}";
        });
        
        post("/create-new-sensor", (request, response) -> {
        	System.out.println(String.format("Creating Sensor: %s", request.body()));
        	SensorService ss = new edu.psu.iot.generator.sensor.SensorService();
        	Sensor newSensor = new Sensor();
        	
        	JsonObject jobj = new Gson().fromJson(request.body(), JsonObject.class);
        	String name = ((JsonObject) jobj.get("sensor")).get("name").toString();
        	if(name != null)
        		//newSensor.setName(name.substring(2, name.length()-2));
        		newSensor.setName(name);
        	double initialValue = ((JsonObject) jobj.get("sensor")).get("initialValue").getAsDouble();
        	if(((JsonObject) jobj.get("sensor")).get("initialValue").toString() != null)
        		newSensor.setInitialValue(initialValue);
        	
        	ss.createSensor(newSensor);
        	//return sensor has a mongoID but its a String 
        	String returnSensor = endpoint.createUpdateSensor(jobj.get("sensor").toString());
        	return returnSensor;
        });
    }
    
    private static void configureExceptionHandling() {
    	exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
    }
    
    private static void enableCORS() {
    	options("/*",
	        (request, response) -> {
	
	            String accessControlRequestHeaders = request
	                    .headers("Access-Control-Request-Headers");
	            if (accessControlRequestHeaders != null) {
	                response.header("Access-Control-Allow-Headers",
	                        accessControlRequestHeaders);
	            }
	
	            String accessControlRequestMethod = request
	                    .headers("Access-Control-Request-Method");
	            if (accessControlRequestMethod != null) {
	                response.header("Access-Control-Allow-Methods",
	                        accessControlRequestMethod);
	            }
	
	            return "OK";
	        });
	
		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}