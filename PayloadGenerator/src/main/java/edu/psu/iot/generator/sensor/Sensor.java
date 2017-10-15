package edu.psu.iot.generator.sensor;
import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import edu.psu.iot.generator.queue.*;

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


public abstract class Sensor implements Runnable{	//Represents a sensor based on a unique id.
	protected static final Logger logger = LogManager.getLogger();
	private String name;
	private int id;
	private double max;
	private double min;
	private double currentValue;
	private long duration;
	private long interval;
	int sinValue = 10;
	private int sinInterval; //degrees
	private long minInterval;
	private long maxInterval;
	boolean rampFlagUp = true;
	boolean rampFlagDown = false;
	private boolean randomInterval = false;
	private SensorType type;
	private boolean enable = true; 			//Set to false in order to stop the task from executing.
	ScheduledExecutorService ses;   //Service currently executing this runnable.
	long startTime;
	boolean isFirstRun = true;
	long endTime;

	public Sensor(SensorConfig config)			
	{
		logger.debug(">>sensorConstructor()");
		this.setId(config.getId());
		this.setCurrentValue(config.getInitialValue());
		this.setMax(config.getMax());
		this.setMin(config.getMin());
		this.setDuration(config.getDuration());
		this.setInterval(config.getInterval());
		this.setName(config.getName());
		this.setType(config.getType());
		this.setSinInterval(config.getSinInterval());
		this.setMinInterval(config.getMinInterval());
		this.setMaxInterval(config.getMaxInterval());
		this.setRandomInterval(config.isRandomInterval());
		
		setMin(Math.min(getMin(),getMax()));
		setMax(Math.max(getMin(), getMax()));
		logger.debug("<<sensorConstructor()");
	}
	
	public void start(ScheduledExecutorService ses)
	{
		logger.debug(">>sensorStart()");
		this.setEnable(true);
		ses.schedule(this, 0, TimeUnit.MILLISECONDS);
		this.ses = ses;
		logger.debug("<<sensorStart()");
	}
	
	public void run() 
	{		
		logger.debug(">>sensorRun()");
		if(isFirstRun){
			startTime = System.currentTimeMillis();
			endTime = startTime + getDuration();
			isFirstRun = false;
		}
		
		JSONObject payload = new JSONObject();
		payload.put("name", this.getName());
		payload.put("id", this.getId());
		payload.put("value", getCurrentValue());

		logger.info("current payload: {}", payload.toString());
		//log to file, TODO:Add timestamps?
        BufferedWriter output = null;
        
        try {
            File file = new File("logFile.txt");
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(payload.toString() + "\n");
            PayloadQueue.getQueue().add(payload);	//Adds payload to list.
        } 
        catch ( IOException e ) {
            e.printStackTrace();
        }
        finally {
            if ( output != null ) {
                try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
              }
        }
	        
		System.out.println(payload);
		
		if(isEnable() == true && ((System.currentTimeMillis() < endTime) || getDuration() == 0)){

			this.setCurrentValue(this.calcValue());
		}
			
		if(isRandomInterval() == true){
			setInterval(Util.getRandomLong(getMinInterval(), getMaxInterval()));
			logger.info("RandomInterval: {}", getInterval()); 
		}
		
		
		
		
		
		//Need to call the calcValue thing here.
		//if start is called on sensor without a ses as input this gives null pointer error
		//so I added a check 
		
		if(this.ses!=null)
		{
			ses.schedule(this, getInterval(), TimeUnit.MILLISECONDS);
		}
		
		logger.debug("<<sensorRun()");
	}

	abstract public double calcValue();
	
	
	public void stop(){
		logger.debug(">>sensorStop()");
		this.setEnable(false);
		logger.debug("<<sensorStop()");
	}
	
	public void start(){
		logger.debug(">>sensorStartNoArg()");
		this.setEnable(true);
		run();
		logger.debug("<<sensorStartNoArg()");
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getSinInterval() {
		return sinInterval;
	}

	public void setSinInterval(int sinInterval) {
		this.sinInterval = sinInterval;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public long getMinInterval() {
		return minInterval;
	}

	public void setMinInterval(long minInterval) {
		this.minInterval = minInterval;
	}

	public long getMaxInterval() {
		return maxInterval;
	}

	public void setMaxInterval(long maxInterval) {
		this.maxInterval = maxInterval;
	}

	public boolean isRandomInterval() {
		return randomInterval;
	}

	public void setRandomInterval(boolean randomInterval) {
		this.randomInterval = randomInterval;
	}

}
