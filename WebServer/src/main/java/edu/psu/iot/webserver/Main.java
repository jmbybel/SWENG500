package edu.psu.iot.webserver;

import static spark.Spark.*;
import api.APIEndpoint;

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
        
        post("/start-sensor", (request, response) -> {
        	String id = request.params("sensorId");
        	String sensor = null;

        	if (id != null)
        		sensor = endpoint.startSensor(id);
        	
        	if (sensor != null)
        		return sensor;
        	
        	return "{}";
        });
        
        post("/pause-sensor", (request, response) -> {
        	String id = request.params("sensorId");
        	String sensor = null;

        	if (id != null)
        		sensor = endpoint.pauseSensor(id);
        	
        	if (sensor != null)
        		return sensor;
        	
        	return "{}";
        });
        
        post("/create-new-sensor", (request, response) -> {
        	return endpoint.createSensor(request.body());
        });
        
        post("/update-sensor", (request, response) -> {
        	return endpoint.updateSensor(request.body());
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