package edu.psu.iot.webserver;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import api.APIEndpoint;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorService;

public class Main {
    public static void main(String[] args) {
    	configureExceptionHandling();
        enableCORS();

        APIEndpoint endpoint = new APIEndpoint();
        
        get("/get-all-sensors", (request, response) -> {
        	System.out.println(endpoint.getAllSensors());
        	
        	return endpoint.getAllSensors();
        });
        
        post("/create-new-sensor", (request, response) -> {
        	System.out.println(String.format("Creating Sensor: %s", request.body()));
        	SensorService ss = new edu.psu.iot.generator.sensor.SensorService();
        	ss.createSensor(new Sensor());

        	return endpoint.createUpdateSensor(request.body());
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