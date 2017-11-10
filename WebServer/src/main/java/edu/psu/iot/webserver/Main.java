package edu.psu.iot.webserver;

import static spark.Spark.*;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import api.APIEndpoint;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.service.IDataService;
import edu.psu.iot.service.impl.DataService;

public class Main {
	static ArrayList<SensorService> ssList = new ArrayList<SensorService>();

    public static void main(String[] args) {
    	port(4000);
    	configureExceptionHandling();
        enableCORS();
        
    	
        APIEndpoint endpoint = new APIEndpoint();
        
        get("/get-number-of-running-sensors", (request, response) -> {
        	return endpoint.getNumberOfRunningSensors();
        });
        
        get("/get-payloads", (request, response) -> {
        	return endpoint.getAllPayloads();
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
        	IDataService ds = new DataService();

        	
        	String newSensor = request.body();
        
        	return ds.createSensor(newSensor);
     
        });
    }
    
    private static void configureExceptionHandling() {
    	exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
    }
    
    public static ArrayList<SensorService> getSSList()
    {
    	return ssList;
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