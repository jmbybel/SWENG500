import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	configureExceptionHandling();
        enableCORS();

        // In-memory data store for POC
        List<String> deviceList = new ArrayList<String>();
        
        get("/get-all-devices", (request, response) -> {
        	System.out.println(String.format("Returning: %s", deviceList.toString()));
        	return deviceList.toString();
        });
        
        post("/create-new-device", (request, response) -> {
        	System.out.println(String.format("Creating Device: %s", request.body()));
        	deviceList.add(request.body());
        	return String.format("Success! Device Created: %s", request.body());
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