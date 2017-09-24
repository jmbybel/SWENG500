package com.psu.group1.generator;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SensorService 
{
	Map<Integer, Sensor> sensorList = new HashMap<Integer, Sensor>();
	
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
    SensorService(){}
    public void createSensor(	
    		String name,			//name of the sensor
			int id, 				//unique integer id
			double initialValue, 	//starting value of sensor
			double max, 			//max value of sensor
			double min, 			//min value of sensor
			long duration,			//duration in milliseconds
			long interval,			//interval in milliseconds
			SensorType type,
			long minInterval,
			long maxInterval, 
			boolean randomInterval)		//SensorType	
    {	 
    	Sensor sensor = new Sensor(name,id,initialValue,max,min,duration,interval,type,minInterval,maxInterval,randomInterval);
    	sensor.start(this.ses);
    	sensorList.put(Integer.valueOf(id), sensor);
    }
    
    public void startSensor(int id){
    	sensorList.get(Integer.valueOf(id)).start(this.ses);
    }
    
    public void stopSensor(int id){
    	sensorList.get(Integer.valueOf(id)).stop();
    }
    
    public void deleteSensor(int id){
    	this.stopSensor(id);
    	sensorList.remove(Integer.valueOf(id));
    }
}
