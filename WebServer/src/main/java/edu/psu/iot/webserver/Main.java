package edu.psu.iot.webserver;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import api.APIEndpoint;
import edu.psu.iot.generator.sensor.SensorConfig;
import edu.psu.iot.generator.sensor.SensorService;

public class Main {
    public static void main(String[] args) {
    	configureExceptionHandling();
        enableCORS();

        APIEndpoint endpoint = new APIEndpoint();
        
        get("/get-all-sensors", (request, response) -> {
        	// TODO: Need to figure out why endpoint.getAllSensors 
        	// is throwing an exception
        	try {
        		System.out.println(endpoint.getAllSensors());
        	}
        	catch (Exception e) {
        		System.out.println(e);
        	}
        	
        	return "[]";
        });
        
        post("/create-new-sensor", (request, response) -> {
        	System.out.println(String.format("Creating Sensor: %s", request.body()));
        	SensorService ss = new edu.psu.iot.generator.sensor.SensorService();
        	
        	JsonParser parser = new JsonParser();
        	JsonObject obj = parser.parse(request.body()).getAsJsonObject();
        	
        	String sensor = obj.getAsJsonObject("sensor").getAsString();
        	System.out.println(sensor);
        	// ss.createSensor(new SensorConfig());

        	return endpoint.createUpdateSensor(sensor);
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