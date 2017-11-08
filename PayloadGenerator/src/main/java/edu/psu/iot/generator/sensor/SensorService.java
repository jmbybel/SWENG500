package edu.psu.iot.generator.sensor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.queue.PayloadQueue;

public class SensorService implements ISensorService {
	
	private static final Logger logger = LogManager.getLogger();
	private static Map<Integer, Payload> sensorList = new HashMap<Integer, Payload>();
	private static ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
	
    public SensorService(){
    	logger.debug(">>SensorServiceConstructor()");
    	ses.shutdownNow();
    	ses = Executors.newScheduledThreadPool(25);
    	this.clearQueue();
    	sensorList.clear();   	
    	logger.debug("<<SensorServiceConstructor()");
    }
    
    @Override
    public void initialize(){
    	logger.debug(">>SensorServieInitialize()");
    	ses.shutdownNow();
    	ses = Executors.newScheduledThreadPool(25);
    	this.clearQueue();
    	sensorList.clear();
    	logger.debug("<<SensorServiceInitialize()");
    }
    
    @Override
    public CircularFifoQueue<JSONObject> getQueue(){
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
    
    public JSONObject getLastQueueItem() {
    	return PayloadQueue.getQueue().poll();
    }
    
    @Override
    public void createSensor(Sensor config)	
    {	 
    	logger.debug(">>sensorServiceConstructor()");
    	
		try {
			Payload sensor;
			sensor = PayloadFactory.createSensor(config);
			sensor.start(SensorService.ses);
	    	sensorList.put(Integer.valueOf(config.getId()), sensor);
		} catch (PayloadTypeInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	logger.info("SS SensorList: {}", sensorList);
    	logger.debug("<<sensorServiceConstructor()");
    }
    
    @Override
    public Map<Integer,Payload> getSensorList(){
    	logger.debug(">>getSensorList()");
    	logger.debug("<<getSensorList()");
    	return SensorService.sensorList;
    }
    
    @Override
    public void startSensor(int id){
    	logger.debug(">>startSensor()");
    	sensorList.get(Integer.valueOf(id)).start(SensorService.ses);
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
    
    public Map<Integer,Payload> getList(){
    	return sensorList;
    }
}
