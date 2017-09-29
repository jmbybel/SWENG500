package com.psu.group1.generator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;
/*
 * SIN - executes a sin function with min and max and a period equal to 100 times the interval
 * RAMP - increases from min to max over the duration or if not duration, 
 * goes from min to max in 100 samples then resets to min
 * RANDOM - random number between min and max
 * BINARY - switches between min and max
 */
enum SensorType {
	SIN, RAMP, RANDOM, BINARY,
}

public class Sensor implements Runnable{	//Represents a sensor based on a unique id.
	private static final Logger logger = LogManager.getLogger();
	String name;
	int id;
	double max;
	double min;
	double currentValue;
	long duration;
	long interval;
	int sinValue;
	int sinInterval; //degrees
	long minInterval;
	long maxInterval;
	boolean rampFlagUp = true;
	boolean rampFlagDown = false;
	boolean randomInterval = false;
	SensorType type;
	boolean enable = true; 			//Set to false in order to stop the task from executing.
	ScheduledExecutorService ses;   //Service currently executing this runnable.
	
	Sensor(	String name,
			int id, 				//unique integer id
			double initialValue, 	//starting value of sensor
			double max, 			//max value of sensor
			double min, 			//min value of sensor
			long duration,			//duration in milliseconds
			long interval,
			SensorType type,
			int sinInterval,
			long minInterval,
			long maxInterval, 
			boolean randomInterval)			//interval in milliseconds
	{
		logger.debug(">>sensorConstructor()");
		this.id = id;
		this.currentValue = initialValue;
		this.max = max;
		this.min = min;
		this.duration = duration;
		this.interval = interval;
		this.name = name;
		this.type = type;
		this.sinValue = sinInterval;
		this.sinInterval = sinInterval;
		this.minInterval = minInterval;
		this.maxInterval = maxInterval;
		this.randomInterval = randomInterval;
		
		min = Math.min(min,max);
		max = Math.max(min, max);
		logger.debug("<<sensorConstructor()");
	}
	
	
	public void start(ScheduledExecutorService ses)
	{
		logger.debug(">>sensorStart()");
		this.enable = true;
		ses.schedule(this, 0, TimeUnit.MILLISECONDS);
		this.ses = ses;
		logger.debug("<<sensorStart()");
	}
	
	public void run() 
	{		
		logger.debug(">>sensorRun()");
			JSONObject payload = new JSONObject();
			payload.put("name", this.name);
			payload.put("id", this.id);
			payload.put("value", currentValue);
			System.out.println(payload);
			
			if(enable == true){
				
				if(type == SensorType.RANDOM){
					currentValue = ((max-min)*Math.random()) + min;
				}	
				if(type == SensorType.BINARY){
					if(currentValue == max)
					{
						currentValue = min;
					}
					else
					{
						currentValue = max;
					}
				}
				if(type == SensorType.RAMP){
					if(currentValue == max)
					{
						rampFlagUp = false;
						rampFlagDown = true;
					}
					if(currentValue == min)
					{
						rampFlagUp = true;
						rampFlagDown = false;
					}
					if(currentValue <= max && rampFlagUp == true)
					{
						currentValue = currentValue + 1;
						rampFlagUp = true;
					}
					if(currentValue >= min && rampFlagDown == true)
					{
						currentValue = currentValue - 1;
						rampFlagDown = true;
					}
				}
				if(type == SensorType.SIN){
					currentValue = Math.sin(sinValue);
					sinValue += sinInterval; 
				}
				if(randomInterval == true){
					
					interval = Util.getRandomLong(minInterval, maxInterval);
				}
				
				//if start is called on sensor without a ses as input this gives null pointer error
				//so I added a check 
				if(this.ses!=null)
				{
					ses.schedule(this, interval, TimeUnit.MILLISECONDS);
				}
			}
			logger.debug("<<sensorRun()");
	}
	
	public void stop(){
		logger.debug(">>sensorStop()");
		this.enable = false;
		logger.debug("<<sensorStop()");
	}
	
	public void start(){
		logger.debug(">>sensorStartNoArg()");
		this.enable = true;
		run();
		logger.debug("<<sensorStartNoArg()");
	}

}