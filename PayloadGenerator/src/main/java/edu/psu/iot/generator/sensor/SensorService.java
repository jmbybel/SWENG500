package edu.psu.iot.generator.sensor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.psu.iot.generator.interfaces.ISensorService;

public class SensorService implements ISensorService {
	
	private static final Logger logger = LogManager.getLogger();
	public Map<Integer, Sensor> sensorList = new HashMap<Integer, Sensor>();
	
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
    public SensorService(){}
    
    @Override
    public void createSensor(SensorConfig config)	
    {	 
    	logger.debug(">>sensorServiceConstructor()");
    	Sensor sensor = new Sensor(config);
    	sensor.start(this.ses);
    	sensorList.put(Integer.valueOf(config.getId()), sensor);
    	logger.info("SS SensorList: {}", sensorList);
    	logger.debug("<<sensorServiceConstructor()");
    }
    
    @Override
    public Map<Integer,Sensor> getSensorList(){
    	logger.debug(">>getSensorList()");
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
