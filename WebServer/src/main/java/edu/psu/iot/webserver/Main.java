package edu.psu.iot.webserver;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import edu.psu.iot.api.APIEndpoint;
import edu.psu.iot.generator.sensor.SensorConfig;
import edu.psu.iot.generator.sensor.SensorService;

public class Main {
    public static void main(String[] args) {
    	configureExceptionHandling();
        enableCORS();

        APIEndpoint endpoint = new APIEndpoint();
        
        get("/get-all-devices", (request, response) -> {
        	//System.out.println(String.format("Returning: %s", deviceList.toString()));
        	
        	System.out.println(endpoint.getAllDevices());
        	return endpoint.getAllDevices();
        	
        	
        	//return deviceList.toString();
        });
        
        post("/create-new-device", (request, response) -> {
        	System.out.println(String.format("Creating Device: %s", request.body()));
        	//deviceList.add(request.body());
        	
        	SensorService ss = new edu.psu.iot.generator.sensor.SensorService();
        	ss.createSensor(new SensorConfig());
        	
        	
        	return endpoint.createUpdateDevice(request.body());
        	
        	
        	//return String.format(request.body());
        	
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