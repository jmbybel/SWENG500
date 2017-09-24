package com.psu.group1.generator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

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
	
	String name;
	int id;
	double max;
	double min;
	double currentValue;
	long duration;
	long interval;
	long minInterval;
	long maxInterval;
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
			long minInterval,
			long maxInterval, 
			boolean randomInterval)			//interval in milliseconds
	{
		
		this.id = id;
		this.currentValue = initialValue;
		this.max = max;
		this.min = min;
		this.duration = duration;
		this.interval = interval;
		this.name = name;
		this.type = type;
		this.minInterval = minInterval;
		this.maxInterval = maxInterval;
		this.randomInterval = randomInterval;
		
		min = Math.min(min,max);
		max = Math.max(min, max);
	
	}
	
	
	public void start(ScheduledExecutorService ses)
	{
		this.enable = true;
		ses.schedule(this, 0, TimeUnit.MILLISECONDS);
		this.ses = ses;
	}
	

	
	public void run() 
	{		
		
			JSONObject payload = new JSONObject();
			payload.put("name", this.name);
			payload.put("id", this.id);
			payload.put("value", currentValue);
			System.out.println(payload);
			
			if(enable == true){
				
				if(type == SensorType.RANDOM){
					currentValue = ((max-min)*Math.random()) + min;
				}		
				
				if(randomInterval == true){
					
					interval = Util.getRandomLong(minInterval, maxInterval);
				}
				ses.schedule(this, interval, TimeUnit.MILLISECONDS);
			}
	}
	
	public void stop(){
		this.enable = false;
	}
	
	public void start(){
		this.enable = true;
		run();
	}

}
