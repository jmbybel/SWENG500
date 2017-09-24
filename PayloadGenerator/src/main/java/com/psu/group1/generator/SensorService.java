package com.psu.group1.generator;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SensorService 
{
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(25);
    SensorService(){}
    public void createSensor(	
    		String name,			//name of the sensor
			int id, 				//unique integer id
			double initialValue, 	//starting value of sensor
			double max, 			//max value of sensor
			double min, 			//min value of sensor
			long duration,			//duration in milliseconds
			long interval,
			SensorType type)			//interval in milliseconds{
    {	 
    	Sensor sensor = new Sensor(name,id,initialValue,max,min,duration,interval,type);
    	sensor.start(this.ses);
    }
}
