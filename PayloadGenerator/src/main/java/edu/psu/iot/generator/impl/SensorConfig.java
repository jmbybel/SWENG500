package edu.psu.iot.generator.impl;

public class SensorConfig {
	
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

	public double getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
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

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public int getSinInterval() {
		return sinInterval;
	}

	public void setSinInterval(int sinInterval) {
		this.sinInterval = sinInterval;
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

	String name = "name";			//name of the sensor
	int id = -1; 				//unique integer id
	double initialValue = 0.0; 	//starting value of sensor
	double max = 100.0; 			//max value of sensor
	double min = 0.0; 			//min value of sensor
	long duration = 0;			//duration in milliseconds
	long interval= 1000;			//interval in milliseconds
	SensorType type = SensorType.RANDOM;
	int sinInterval = 10;
	long minInterval = 1000;
	long maxInterval = 5000; 
	boolean randomInterval = false;
		
	public SensorConfig(){}
	
}
