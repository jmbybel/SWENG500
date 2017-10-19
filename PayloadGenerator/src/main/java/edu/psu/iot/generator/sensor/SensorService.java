package edu.psu.iot.generator.sensor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.queue.PayloadQueue;

public class SensorService implements ISensorService {
	
	private static final Logger logger = LogManager.getLogger();
	public Map<Integer, Sensor> sensorList = new HashMap<Integer, Sensor>();
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
	
    public SensorService(){
    	logger.debug(">>SensorServiceConstructor()");
    	ses.shutdown();
    	ses = Executors.newScheduledThreadPool(25);
    	this.clearQueue();
    	sensorList.clear();   	
    	logger.debug("<<SensorServiceConstructor()");
    }
    
    @Override
    public void initialize(){
    	logger.debug(">>SensorServieInitialize()");
    	ses.shutdown();
    	ses = Executors.newScheduledThreadPool(25);
    	this.clearQueue();
    	sensorList.clear();
    	logger.debug("<<SensorServiceInitialize()");
    }
    
    @Override
    public LinkedList<JSONObject> getQueue(){
    	logger.debug(">>SensorServicegetQueue()");
    	logger.debug("<<SensorServicegetQueue()");
    	return PayloadQueue.getQueue();
    }
    
    @Override
    public void clearQueue(){
    	logger.debug(">>clearQueue()");
    	PayloadQueue.getQueue().clear();
    	System.out.println("The queue size after clearing is " + PayloadQueue.getQueue().size());
    	logger.debug("<<clearQueue()");
    }
    
    @Override
    public void createSensor(SensorConfig config)	
    {	 
    	logger.debug(">>sensorServiceConstructor()");
    	
		try {
			Sensor sensor;
			sensor = SensorFactory.createSensor(config);
			sensor.start(this.ses);
	    	sensorList.put(Integer.valueOf(config.getId()), sensor);
		} catch (SensorTypeInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	logger.info("SS SensorList: {}", sensorList);
    	logger.debug("<<sensorServiceConstructor()");
    }
    
    @Override
    public Map<Integer,Sensor> getSensorList(){
    	logger.debug(">>getSensorList()");
    	logger.debug("<<getSensorList()");
    	return this.sensorList;
    }
    
    @Override
    public void startSensor(int id){
    	logger.debug(">>startSensor()");
    	sensorList.get(Integer.valueOf(id)).start(this.ses);
    	logger.debug("<<startSensor()");
    }
    
    @Override
    public void stopSensor(int id){
    	logger.debug(">>stopSensor()");
    	sensorList.get(Integer.valueOf(id)).stop();
    	logger.debug("<<stopSensor()");
    }
    
    @Override
    public void deleteSensor(int id){
    	logger.debug(">>deleteSensor()");
    	this.stopSensor(id);
    	sensorList.remove(Integer.valueOf(id));
    	logger.debug("<<deleteSensor()");
    }
}
