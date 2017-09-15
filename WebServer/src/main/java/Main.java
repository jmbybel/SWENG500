import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
    	exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        enableCORS();
    	
        post("/create-new-device", (request, response) -> {
        	System.out.println(String.format("Creating Device: %s", request.body()));

        	return String.format("Success! Device Created: %s", request.body());
        });
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
