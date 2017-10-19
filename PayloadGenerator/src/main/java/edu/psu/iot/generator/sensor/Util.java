package edu.psu.iot.generator.sensor;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Util {
	private static final Logger logger = LogManager.getLogger();
	public static long getRandomLong(long min, long max) {
		logger.debug(">>getRandomLong()");
	    long leftLimit = min;
	    long rightLimit =max;
	    logger.info("leftLimit: {}", leftLimit);
	    logger.info("rightLimit: {}", rightLimit);
	    logger.debug("<<getRandomLong()");
	    return (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
	}
    public static Object postEndpoint (String url, JSONObject payload) throws Exception {
        StringEntity entity = new StringEntity(payload.toString(),
                ContentType.APPLICATION_FORM_URLENCODED);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode();
        
    }
}
