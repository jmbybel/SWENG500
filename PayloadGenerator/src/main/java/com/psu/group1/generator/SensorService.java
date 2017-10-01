package com.psu.group1.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SensorService 
{
	private static final Logger logger = LogManager.getLogger();
	Map<Integer, Sensor> sensorList = new HashMap<Integer, Sensor>();
	
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
    SensorService(){}
    public void createSensor(	//Delete this eventually
    		String name,			//name of the sensor
			int id, 				//unique integer id
			double initialValue, 	//starting value of sensor
			double max, 			//max value of sensor
			double min, 			//min value of sensor
			long duration,			//duration in milliseconds
			long interval,			//interval in milliseconds
			SensorType type,
			int sinInterval,
			long minInterval,
			long maxInterval, 
			boolean randomInterval)		//SensorType	
    {	 
    	logger.debug(">>sensorServiceConstructor()");
    	Sensor sensor = new Sensor(name,id,initialValue,max,min,duration,interval,type,sinInterval,minInterval,maxInterval,randomInterval);
    	sensor.start(this.ses);
    	sensorList.put(Integer.valueOf(id), sensor);
    	logger.info("SS SensorList: {}", sensorList);
    	logger.debug("<<sensorServiceConstructor()");
    }
    
    public void createSensor(SensorConfig config)		//SensorType	
    {	 
    	logger.debug(">>sensorServiceConstructor()");
    	Sensor sensor = new Sensor(config);
    	sensor.start(this.ses);
    	sensorList.put(Integer.valueOf(config.getId()), sensor);
    	logger.info("SS SensorList: {}", sensorList);
    	logger.debug("<<sensorServiceConstructor()");
    }
    
    public Map<Integer,Sensor> getSensorList(){
    	logger.debug(">>getSensorList()");
    	return this.sensorList;
    }
    
    public void startSensor(int id){
    	logger.debug(">>startSensor()");
    	sensorList.get(Integer.valueOf(id)).start(this.ses);
    	logger.debug("<<startSensor()");
    }
    
    public void stopSensor(int id){
    	logger.debug(">>stopSensor()");
    	sensorList.get(Integer.valueOf(id)).stop();
    	logger.debug("<<stopSensor()");
    }
    
    public void deleteSensor(int id){
    	logger.debug(">>deleteSensor()");
    	this.stopSensor(id);
    	sensorList.remove(Integer.valueOf(id));
    	logger.debug("<<deleteSensor()");
    }
}
