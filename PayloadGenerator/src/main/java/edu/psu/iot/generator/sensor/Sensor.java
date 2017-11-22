package edu.psu.iot.generator.sensor;

import edu.psu.iot.generator.interfaces.ISensor;

public class Sensor implements ISensor {
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public double getInitialValue() {
		return initialValue;
	}

	@Override
	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}

	@Override
	public double getMax() {
		return max;
	}

	@Override
	public void setMax(double max) {
		this.max = max;
	}

	@Override
	public double getMin() {
		return min;
	}

	@Override
	public void setMin(double min) {
		this.min = min;
	}

	@Override
	public long getDuration() {
		return duration;
	}

	@Override
	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public long getInterval() {
		return interval;
	}

	@Override
	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public PayloadType getType() {
		return type;
	}

	@Override
	public void setType(PayloadType type) {
		this.type = type;
	}

	@Override
	public int getSinInterval() {
		return sinInterval;
	}

	@Override
	public void setSinInterval(int sinInterval) {
		this.sinInterval = sinInterval;
	}

	@Override
	public long getMinInterval() {
		return minInterval;
	}

	@Override
	public void setMinInterval(long minInterval) {
		this.minInterval = minInterval;
	}

	@Override
	public long getMaxInterval() {
		return maxInterval;
	}

	@Override
	public void setMaxInterval(long maxInterval) {
		this.maxInterval = maxInterval;
	}
	
	@Override
	public String getUrlEndpoint() {
		return urlEndpoint;
	}

	@Override
	public void setUrlEndpoint(String url) {
		this.urlEndpoint = url;
	}

	@Override
	public boolean isRandomInterval() {
		return randomInterval;
	}

	@Override
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
	PayloadType type = PayloadType.RANDOM;
	int sinInterval = 10;
	long minInterval = 1000;
	long maxInterval = 5000; 
	boolean randomInterval = false;
	String urlEndpoint = "http://18.216.43.18:8081/contentListener"; //Nifi default
	boolean enabled = false;
		
	public Sensor(){}
	
}
