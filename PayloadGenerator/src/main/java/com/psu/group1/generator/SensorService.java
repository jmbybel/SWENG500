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

    public void createSensor(SensorConfig config)	
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
